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
var apikey = "";
var NumberOfRequestsPerEndUser = 30;

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

// Add event type called when a user answer a question on /api/eventTypes/
var addEasyQuestionEvent = {
  "name": "answerQuestion",
  "properties": {
    "difficulty": "easy"
  }
}

var addMediumQuestionEvent = {
  "name": "answerQuestion",
  "properties": {
    "difficulty": "medium"
  }
}

var addHardQuestionEvent = {
  "name": "answerQuestion",
  "properties": {
    "difficulty": "hard"
  }
}

// add rules on /api/rules

var addRuleQuestionHard = {
  "if":{
    "type": "answerQuestion",
    "properties":{
      "difficulty": "hard"
    }
  },
  "then": {
    "action": "awardPoint",
    "nbPoints": 3
  }
}

var addRuleQuestionMedium = {
  "if":{
    "type": "answerQuestion",
    "properties":{
      "difficulty": "medium"
    }
  },
  "then": {
    "action": "awardPoint",
    "nbPoints": 2
  }
}

var addRuleQuestionEasy = {
  "if":{
    "type": "answerQuestion",
    "properties":{
      "difficulty": "easy"
    }
  },
  "then": {
    "action": "awardPoint",
    "nbPoints": 1
  }
}

// POST event according to a user

// Name correspond à l'ID
var eventEasy = {
  "type": "answerQuestion",
  "timestamp": new Date();,
  "endUserId": endUserId,
  "properties":
    {
      "tag": "java",
      "difficulty": "easy"
    }
}


// Request data

function getRequestPOST(data, url) {
  return function(callback) {
    var requestData = {
      headers:{
        "Content-Type": "application/json",
        "Authorization": apikey
      },
      data: data,
      requestConfig:{
        "timeout": 5000,
        keepAlive: false
      },
      responseConfig:{
        "timeout":5000
      }
    };

    logTransaction(submittedStats, requestData.data);
    console.log("POST " + url + requestData.data);

    var req = client.post("http://localhost:8080/AMT_Project/" + url, requestData, function(data, response) {
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
};

// Table for End user events
var endUserRequests[];
// For endUser 1 to 9 (it's their IDs)
for (var j = 0; j < 10; j++){
  // X requests per endUser that will add 1 point each 
  for (var i = 0; i < NumberOfRequestsPerEndUser; i++) {

    var url = '/api/events';

    // The event
    var data = eventEasy;
    data.endUserId = j;
    endUserRequests.push(
      getRequestPOST(data, url);
    );
  }
}

function postTransactionRequestsInParalell(callback){
  console.log("\n\n==========================================");
	console.log("POSTing transaction requests in parallel");
	console.log("------------------------------------------");
  var numberOfUnsuccessfulResponses = 0;
  async.paralell(endUserRequests, function(err, results){
    for (var i = 0; i < endUserRequests.length; i++){
      if(endUserRequests[i].response.statusCode < 200 || endUserRequests[i].response.statusCode >= 300){
        console.log("Result " + i + ": " + endUserRequests[i].response.statusCode);
        numberOfUnsuccessfulResponses++;
      } else {
        logTransaction(processedStats, endUserRequests[i].requestData.data);
      }
    }
    callback(null, endUserRequests.length + " transactions POSTs have been sent " + numberOfUnsuccessfulResponses + " have failed ");
  });
};

function checkValues(callback){
  console.log("\n\n==========================================");
	console.log("Comparing client-side and server-side stats");
	console.log("------------------------------------------");
  var requestData = {
    headers:{
      "Accept": "application/json"
    }
  };
  // Points for user 0 to 9
  var userPointsOnServer[];
  for (var i = 0; i < 10; i++){
    client.get("http://localhost:8080/AMT_Project/api/users/" + userId + "/reputation", requestData, function(data, response){
      // push in userPointsOnServer the number of points for each user
      userPointsOnServer.push(data.points);
      // The number of points is supposed to be equal to the NumberOfRequestsPerEndUser
      // The eventEasy makes the endUser to earn 1 point.
      console.log("Points for user " + i + ": " + data.points);
      if(data.points !== NumberOfRequestsPerEndUser){
        console.log("Error: The number of points is " + data.points + " and should be " + NumberOfRequestsPerEndUser);
      }
    });
  }

}

async.series([
  postTransactionRequestsInParalell,
  checkValues
], function(err, results) {
  console.log("\n\n==========================================");
	console.log("Summary");
	console.log("------------------------------------------");
	//console.log(err);
	console.log(results);
});
