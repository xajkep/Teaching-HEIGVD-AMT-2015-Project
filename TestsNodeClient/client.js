/*
 * Group: Berthouzoz, Schowing, Widmer & Zuckschwerdt
 * File: client.js
 * Objective: Test the concurrency gestion of the gamification platform.
 *
 * Details:
 * This program goal is to test the behaviour of the application with multiple parallel requests.
 * This "client" will send multiple events to the gamification platform in order to increase the
 * amount of points / badges of a client and then will ask the client how many points he has.
 *
 * If the number of points are not equals on both sides (this node application and in the gamification platfomr)
 * there is a problem (probably concurrency).
 *
 * IMPORTANT: + It's recomended to empty the database before testing. Users are randomly created but there could
 *							be colisions. It's supposed that the users you will create here are brand new ones.
 *							This will be usefull when the first events will be sent. Indeed, if two events from an unexisting
 *							user arrive at the same time, there could be concurrency problems during this creation of the user.
 *						+ The event gives ONE point to the user and is compared to the number of event sent. If you change
 *						  the event (from eventEasy to hard for instance) you will have to change the comparison in the
 *							checkValues's function.
 *
 */
var Client = require('node-rest-client').Client;
var client = new Client();

var async = require('async');

var http = require('http');

// Chance is used to generate random names
var Chance = require('chance');
var chance = new Chance();

// Mysql is used to fetch an apiKey
var mysql = require('mysql');

/* OLI (c)
 * This is a very important parameter: it defines how many sockets can be opened at the same time. In other
 * words, if it is equal to 1, then requests will be sent one by one (no concurrency on the server because
 * of this test client). The higher the number, the higher the concurrency.
 */
http.globalAgent.maxSockets = 5;
var apikey = "";
var baseURL = "http://localhost:8080/AMT_Projet_Untitled/";
var addRuleURL = "api/rules";
var addBadgeURL = "api/badges";
var NumberOfRequestsPerEndUser = 4;
var numberOfUser = 5;

// MySql Credentials IMPORTANT
var mysqlDatabase = "amt";
var mysqlUser = "amt";
var mysqlPassword = "amt";

// There is random number generation now but this can be usefull anyway so we'll keep those names a moment.
// We'll test with 10 users so we need IDs ! Here's ten 100% not random IDs:
var endUserNamesFix = ["End0_Amber", "End1_Blond", "End2_Dark", "End3_YesItsBeer", "End4_Fag", "End5_Smith", "End6_JamesBond", "End7_JamesBrown", "End8_Sacha", "End9_Olivier"];

// Table for End user events
var endUserRequests = [];

// This is used to store the request functions that will add rules and badges
var rulesAndBadgesRequests = [];


//############################# RANDOM NAMES #################################//
// Generate random usernames with Chance
var endUserNamesRandom = [];
for (var i = 0; i < numberOfUser; i++){
	endUserNamesRandom.push(chance.first());
}
console.log("Table of " + numberOfUser + " random users created: " + endUserNamesRandom);


//############################# GET API KEY ##################################//
function getApiKey(notifyApiKeyHasBeenFetched){
	// Connection to the database to fetch the apiKey of app1 (auto generated test app)
	console.log("Getting apikey...");
	var connection = mysql.createConnection(
	    {
	      host     : 'localhost',
	      user     : 'amt',
	      password : 'amt',
	      database : 'amt',
	    }
	);

	connection.connect();

	var queryString = 'SELECT apikey FROM application INNER JOIN apikey ON application.KEY_ID = apikey.ID WHERE application.name = \"app1\"';

	connection.query(queryString, function(err, rows, fields) {
	    if (err) throw err;

	 		// Normaly there is only one row
	    for (var i in rows) {
	        console.log('Got apikey: ', rows[i].apikey); //.fields[0]
					apikey = rows[i].apikey;
	    }
			notifyApiKeyHasBeenFetched(null, "API key have been fetched");
	});

	connection.end();
}


//############################# REQUESTS DATA ################################//
// add badges on /api/badges

var addBadge1 = {
  picture: "/path/picture1",
  description: "Badge1"
}

var addBadge2 = {
  picture: "/path/picture2",
  description: "Badge2"
}

// add rules on /api/rules

var addRuleQuestionHard = {
  condition:{
    type: "answerQuestion",
    properties:{
      "difficulty": "hard"
    }
  },
  action: {
    type: "awardPoint",
    properties:{
			"nbPoints":"3"
		}
  }
}

var addRuleQuestionMedium = {
  condition:{
    type: "answerQuestion",
    properties:{
      "difficulty": "medium"
    }
  },
  action: {
    type: "awardPoint",
    properties:{
			"nbPoints":"2"
		}
  }
}


var addRuleQuestionEasy = {
  condition:{
    type: "answerQuestion",
    properties:{
      "difficulty": "easy"
    }
  },
  action: {
    type: "awardPoint",
    properties:{
			"nbPoints":"1"
		}
  }
}

// POST event according to a user

var endUserId = "";
var eventEasy = {
  type: "answerQuestion",
  timestamp: new Date().toISOString(),
  endUser: endUserId,
  properties:
    {
      tag: "java",
      difficulty: "easy"
    }
}


//######################## Build a Request Functions #########################//

// This function return the functions that will send the requests (events)
function getRequestPOST(data, url, callback) {
	//console.log("Prepare a POST request for user " + data.endUser);
	var capturedData = JSON.parse(JSON.stringify(data));
	console.log("getRequestPOST captured data: " + JSON.stringify(capturedData));
  return function(callback) {
		// Request headers and data
    var requestData = {
      headers:{
        "Content-Type": "application/json",
        "Authorization": apikey
      },
      data: capturedData,
      requestConfig:{
        "timeout": 5000,
        keepAlive: false
      },
      responseConfig:{
        "timeout":5000
      }
    };

    console.log("POST at: " + url + "With datas: " + JSON.stringify(requestData.data));

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

//######################## Build Requests Table ##########################//

// This function build the table of functions that we will execute in parallel later
// The table is endUserRequests

function tableOfRequestsFunction(callback){

	// For endUser 0 to 9 (it's their IDs)
	for (var j = 0; j < numberOfUser; j++){
	  // X requests per endUser that will add 1 point each
	  for (var i = 0; i < NumberOfRequestsPerEndUser; i++) {

	    var url = 'api/events';

	    // The event
	    var data = eventEasy;
	    data.endUser = endUserNamesRandom[j];
			console.log("Adding request in table: " + JSON.stringify(data));
	    endUserRequests.push(
	      getRequestPOST(data, baseURL + url)
	    );
	  }
	}
	callback();
}// End of tableOfRequestsFunction


//######################## POST requests in parallel #########################//

// This function executes the functions in endUserRequests
// Eachone of those functions sent their request, all of this in parallel.
function postTransactionRequestsInparallel(callback){
  console.log("\n\n==========================================");
	console.log("POSTing transaction requests in parallel");
	console.log("------------------------------------------");
  var numberOfUnsuccessfulResponses = 0;
  async.parallel(endUserRequests, function(err, results){
    for (var i = 0; i < endUserRequests.length; i++){
      if(results[i].response.statusCode < 200 || results[i].response.statusCode >= 300){
        console.log("Result " + i + ": " + results[i].response.statusCode);
        numberOfUnsuccessfulResponses++;
      } else {
				console.log("Posting transaction requests succeed");
      }
    }
    callback(null, endUserRequests.length + " transactions POSTs have been sent " + numberOfUnsuccessfulResponses + " have failed ");
  });
};// End of postTransactionRequestsInparallel

//###################### Verify Server-Client Values #########################//


var userPointsOnServer = [];

function getUserPoints(userNameP){
var userName = userNameP; //JSON.parse(JSON.stringify(userNameP)); utile seulement pour objets
	return function(callback){
		console.log("User to check (get user points):" + baseURL + "api/users/" + userName + "/reputation");

		var requestData = {
	    headers:{
	      "Accept": "application/json",
				"Authorization": apikey
	    }
	  };

		client.get(baseURL + "api/users/" + userName + "/reputation", requestData, function(data, response){
			console.log("Points pushed in table: " + data.points + " for user: " + userName);
			userPointsOnServer.push(data.points);

			console.log(response.statusCode + " - " + response.statusMessage);
		});
		callback(null, "Get function have been done for user " + userName);
	}
}



// This function get the number of points from the server and compare them to
// the points sent. If users are new ones, values must be the same.
function checkValues(callback){
  console.log("\n\n==========================================");
	console.log("Comparing client-side and server-side stats");
	console.log("------------------------------------------");

  // Points for each users
	var getUserPointsOnServer = [];

	console.log("Apikey before check Values: " + apikey);

	// Put the functions to get the points
  for (var i = 0; i < numberOfUser; i++){
		console.log("User to check:" + baseURL + "api/users/" + endUserNamesRandom[i] + "/reputation");
		getUserPointsOnServer.push(getUserPoints(endUserNamesRandom[i]));
  }

	async.series(getUserPointsOnServer, function(err, results){
		console.log("User points got");
		console.log("Table of points: " + userPointsOnServer);
	});
}; // End of checkValues

//########################### INITIALISATION #################################//

// This function is just like getRequestPOST but with console informations.
function initialize(data, url, callback){
	console.log("\n\n==========================================");
	console.log("Adding new rule or badge: " + JSON.stringify(data) + " ...");
	console.log("------------------------------------------");
	return getRequestPOST(data, url, callback);
}
// This function send the requests that add the badges and the rules in parallel
function initialisation(notifyInitHasBeenDone){
	console.log("\n\n==========================================");
	console.log("POSTing rules and badges initialisation requests in parallel");
	console.log("------------------------------------------");

	// Ajout des fonctions POST au tableau
	rulesAndBadgesRequests.push(initialize(addRuleQuestionEasy, baseURL + addRuleURL, notifyInitHasBeenDone));
	rulesAndBadgesRequests.push(initialize(addRuleQuestionMedium, baseURL + addRuleURL, notifyInitHasBeenDone));
	rulesAndBadgesRequests.push(initialize(addRuleQuestionHard, baseURL + addRuleURL, notifyInitHasBeenDone));
	rulesAndBadgesRequests.push(initialize(addBadge1, baseURL + addBadgeURL, notifyInitHasBeenDone));
	rulesAndBadgesRequests.push(initialize(addBadge2, baseURL + addBadgeURL, notifyInitHasBeenDone));

	async.parallel(rulesAndBadgesRequests, function(err, results){
		var failed = 0;
		for (var i = 0; i < rulesAndBadgesRequests.length; i++){
      if(results[i].response.statusCode < 200 || results[i].response.statusCode >= 300){
        console.log("Result " + i + ": " + results[i].response.statusCode);
				failed++;
      } else {
				console.log("Posting rules and badges succeed");
      }
    }
    notifyInitHasBeenDone(null, rulesAndBadgesRequests.length + " transactions POSTs have been sent " + failed + " have failed ");
	});
} // End of initialisation

//############################################################################//
// TESTS
// 1) 	Fetch the api-key in the database
// 2)		Post the requests to prepare the rules and badges
// 3) 	Create the table of requests to send en parallel
// 4) 	Post the requests (events) in parallel
// 5) 	Verify the values client-server side
//############################################################################//

// Execute in series with async:
async.series([
	getApiKey,
	initialisation,
	tableOfRequestsFunction,
  postTransactionRequestsInparallel,
  checkValues
], function(err, results) {
  console.log("\n\n==========================================");
	console.log("Summary");
	console.log("------------------------------------------");
	//console.log(err);
	console.log(results);
});
//============================================================================//
