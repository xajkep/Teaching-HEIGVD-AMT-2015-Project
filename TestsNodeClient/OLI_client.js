var Client = require('node-rest-client').Client;
var client = new Client();
var async = require('async');


var http = require('http');

/*
 * This is a very important parameter: it defines how many sockets can be opened at the same time. In other
 * words, if it is equal to 1, then requests will be sent one by one (no concurrency on the server because
 * of this test client). The higher the number, the higher the concurrency.
 */
http.globalAgent.maxSockets = 1;

/*
 * This parameters are used to control the experiments and specify how much data we want to create.
 */
var numberOfAccounts = 10;
var numberOfTransactionsPerAccount = 20;

/*
 * This map keeps track of the transactions posted by the client,
 * even if they result in an error (for instance if two parallel requests try to create a new account).
 * In this case, the client is informed that the transaction has failed and it would be his responsibility
 * to retry.
 */
var submittedStats = {}

/*
 * This map keeps track of the transactions posted by the client, but only if the server has confirmed
 * their processing with a successful status code.
 * In this case, the client can assume that the transaction has been successfully processed.
 */
var processedStats = {};


function logTransaction(stats, transaction) {
	var accountStats = stats[transaction.accountId] || {
		accountId: transaction.accountId,
		numberOfTransactions: 0,
		balance: 0
	};
	accountStats.numberOfTransactions += 1;
	accountStats.balance += transaction.amount;
	stats[transaction.accountId] = accountStats;
}


/*
 * This function returns a function that we can use with the async.js library.
 */
function getTransactionPOSTRequestFunction(accountId) {

	return function(callback) {
		var requestData = {
			headers:{
				"Content-Type": "application/json"
			},
			data: { // Informations à envoyer au serveur: Quel évenement et quel endUser -> voir spec-rest-api
				'accountId': accountId,
				'amount': 0 // we will generate a random value below
			},
			requestConfig:{
				'timeout' : 5000,
				keepAlive:false
			},
			responseConfig:{
				'timeout' : 5000
			}
		};

		requestData.data.amount = Math.floor((Math.random() * 200) - 50);
		logTransaction(submittedStats, requestData.data);

		console.log("POST /transactions/ " + requestData.data.accountId + ":" + requestData.data.amount);

			var req = client.post("http://localhost:8080/ConcurrentTransactionsServer/api/transactions", requestData, function(data, response) {
			var error = null;
			var result = {
				requestData: requestData,
				data: data,
				response: response
			};
			callback(error, result);
		});

		req.on('error', function(err){
			 console.error("Network error: " + err);
		});

		req.on('requestTimeout',function(req){
		    console.log('request has expired');
		    req.abort();
		});

		req.on('responseTimeout',function(res){
		    console.log('response has expired');

		});
	}
}

/*
 * Prepare an array of HTTP request functions. We will pass these to async.parallel
 */
var requests = [];

for (var account=1; account<=numberOfAccounts; account++) {
	for (var transaction=0; transaction<numberOfTransactionsPerAccount; transaction++) {
		requests.push(
			getTransactionPOSTRequestFunction(account)
		);
	}
};

/*
 * Reset server side - this will delete all accounts
 */
function resetServerState(callback) {
	console.log("\n\n==========================================");
	console.log("POSTing RESET command.");
	console.log("------------------------------------------");
	client.post("http://localhost:8080/ConcurrentTransactionsServer/api/operations/resetOperation", function(data, response) {
		console.log("RESET response status code: " + response.statusCode);
		callback(null, "The RESET operation has been processed (status code: " + response.statusCode + ")");
	});
};

/*
 * POST transaction requests in parallel
 */
function postTransactionRequestsInParallel(callback) {
	console.log("\n\n==========================================");
	console.log("POSTing transaction requests in parallel");
	console.log("------------------------------------------");
	var numberOfUnsuccessfulResponses = 0;
	async.parallel(requests, function(err, results) {
		for (var i=0; i<results.length; i++) {
			if (results[i].response.statusCode < 200 || results[i].response.statusCode >= 300) {
				console.log("Result " + i + ": " + results[i].response.statusCode);
				numberOfUnsuccessfulResponses++;
			} else {
				logTransaction(processedStats, results[i].requestData.data);
			}
		}
		callback(null, results.length + " transaction POSTs have been sent. " + numberOfUnsuccessfulResponses + " have failed.");
	});
}

/*
 * Fetch server-side state (list of accounts). The list of accounts is passed to the callback function.
 */
function checkValues(callback) {
	console.log("\n\n==========================================");
	console.log("Comparing client-side and server-side stats");
	console.log("------------------------------------------");
	var requestData = {
		headers:{
			"Accept": "application/json"
		}
	};
	client.get("http://localhost:8080/ConcurrentTransactionsServer/api/accounts", requestData, function(data, response) {
		var numberOfErrors = 0;
		var clientSideAccounts = Object.keys(submittedStats).length;
		var serverSideAccounts = data.length;
		console.log("Number of accounts on the client side: " + clientSideAccounts);
		console.log("Number of accounts on the server side: " + serverSideAccounts);
		if (clientSideAccounts !== serverSideAccounts) {
			numberOfErrors++;
		}

		for (var i=0; i<data.length; i++) {
			var accountId = data[i].id;
			var serverSideValue = data[i].balance;
			var clientSideValue = processedStats[accountId].balance;
			if (clientSideValue !== serverSideValue) {
				numberOfErrors++;
				console.log("Account " + accountId + " --> Server/Client balance: " + serverSideValue + "/" + clientSideValue + "  X");
			} else {
				//console.log("Account " + accountId + " --> Server/Client balance: " + serverSideValue + "/" + clientSideValue);
			}
		}

		callback(null, "The client side and server side values have been compared. Number of corrupted accounts: " + numberOfErrors);
	});
}

/*
 * This is the entry point of the script. We send a RESET command to the server to remove all accounts, then send POST /transactions
 * requests in parallel and finally check the expected state with the actual state. We report results and errors.
 */
async.series([
	resetServerState,
	postTransactionRequestsInParallel,
	checkValues
], function(err, results) {
	console.log("\n\n==========================================");
	console.log("Summary");
	console.log("------------------------------------------");
	//console.log(err);
	console.log(results);
});
