/*
 * Group: Berthouzoz, Schowing, Widmer & Zuckschwerdt
 * File: client.js
 * Objective: Test the concurrency gestion of the gamification platform.
 *
 * Details:
 * This program goal is to test the behaviour of the application with multiple paralell requests.
 * This "client" will send multiple events to the gamification platform in order to increase the
 * amount of points / badges of a client and then will ask the client how many points he has.
 *
 * If the number of points are not equals on both sides (this node application and in the gamification platfomr)
 * there is a problem (probably concurrency).
 *
 * IMPORTANT: + Empty the database before testing. It's supposed that the users you will create are brand new ones.
 *						+ The event gives ONE point to the user and is compared to the number of event sent. If you change
 *						  the event (from eventEasy to hard for instance) you will have to change the comparison in the
 *							checkValues's function.
 *
 */
var Client = require('node-rest-client').Client;
var client = new Client();
var async = require('async');


var http = require('http');

/* OLI (c)
 * This is a very important parameter: it defines how many sockets can be opened at the same time. In other
 * words, if it is equal to 1, then requests will be sent one by one (no concurrency on the server because
 * of this test client). The higher the number, the higher the concurrency.
 */
http.globalAgent.maxSockets = 5;
var apikey = "";
var baseURL = "http://localhost:8080/AMT_Projet_Untitled/";
var addRuleURL = "/api/rules";
var NumberOfRequestsPerEndUser = 30;

// We'll test with 10 users so we need IDs ! Here's ten 100% random IDs:
var endUserNames["End0_Amber", "End1_Blond", "End2_Dark", "End3_YesItsBeer", "End4_Fag", "End5_Smith", "End6_JamesBond", "End7_JamesBrown", "End8_Sacha", "End9_Olivier"];

/* OLI (c)
 * This map keeps track of the transactions posted by the client,
 * even if they result in an error (for instance if two parallel requests try to create a new account).
 * In this case, the client is informed that the transaction has failed and it would be his responsibility
 * to retry.
 */
var submittedStats = {}

/* OLI (c)
 * This map keeps track of the transactions posted by the client, but only if the server has confirmed
 * their processing with a successful status code.
 * In this case, the client can assume that the transaction has been successfully processed.
 */
var processedStats = {};


function logTransaction(stats, transaction) {
	var accountStats = stats[transaction.endUserId] || {
		EndUserId: transaction.endUserId,
		numberOfTransactions: 0
	};
	accountStats.numberOfTransactions += 1;
	stats[transaction.endUserId] = accountStats;
}


//DEPRECATED TO DELETE ------------------
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
// --------------------------------

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
  "timestamp": new Date().toISOString();,
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

		// Request headers and data
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

    var req = client.post(baseURL + url, requestData, function(data, response) {
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
}; // End of getRequestPOST


// Table for End user events
var endUserRequests[];
// For endUser 0 to 9 (it's their IDs)
for (var j = 0; j < 10; j++){
  // X requests per endUser that will add 1 point each
  for (var i = 0; i < NumberOfRequestsPerEndUser; i++) {

    var url = '/api/events';

    // The event
    var data = eventEasy;
    data.endUserId = endUserNames[j];
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
    client.get(baseURL + "api/users/" + userId + "/reputation", requestData, function(data, response){
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
} // End of checkValues

// This function has to create the rules as we suppose that we start with a brand new application.
// Data will be "addRuleQuestionEasy" for instance.
function initialize(data, url){
	console.log("\n\n==========================================");
	console.log("Adding new rule: "+ data.if.type + " ...");
	console.log("------------------------------------------");
	getRequestPOST(data, url);
}
initialize(addRuleQuestionEasy, baseURL + addRuleURL);
initialize(addRuleQuestionMedium, baseURL + addRuleURL);
initialize(addRuleQuestionHard, baseURL + addRuleURL);

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
