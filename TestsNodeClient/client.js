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
 * This parameters are used to control the experiments and specify how much data we want to create.
 */
//var numberOfAccounts = 10;
//var numberOfTransactionsPerAccount = 20;

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
// For endUser 1 to 10 (it's their IDs)
for (var j = 0; j < 10; j++){
  // X requests per endUser
  for (var i = 0; i < NumberOfRequestsPerEndUser; i++) {

    var url = '/api/eventTypes';

    // The event
    var data = eventEasy;
    data.endUserId = j;
    endUserRequests.push(
      getRequestPOST(data, url);
    );
  }
}
