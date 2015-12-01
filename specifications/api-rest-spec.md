# API REST - Specifications

## API key

> All requests uses the apiKey in the HTTP header "Authorization" in order to authenticate the application.

> Every rules and event types are bound to an application. If a client want to delete the rule number 35, we will use the apiKey to match the application and the rule.

> If the apiKey is not specified or empty, the server return an error 401 with a message "Apikey is missing".

> If the apiKey doesn't exist, the server return an error 401 with message "This apikey doesn't exist"

## GET requests

> All GET requests should return HTTP 200 status code

## POST requests

> All POST requests should return HTTP 201 status code

## PUT requests

> All PUT requests should return HTTP 200 status code

## DELETE requests

> All DELETE requests should return HTTP 200 status code

## Get all users

GET /api/users
~~~json
{
  "users": [{
    "href": String,
    "name": String
  }]
}
~~~

## Get one user

GET /api/users/{id}
~~~json
{
  "href": String,
  "name": String
}
~~~

## Add a new user

POST /api/users/{id}
~~~json
{
  "name": String
}
~~~

## Edit an existant user

PUT /api/users/{id}
~~~json
{
  "name": String
}
~~~

## Delete an existant user

DELETE /api/users/{id}

## Get user reputation

GET /api/users/id/reputation

~~~json
{
  "badges": [{
      "href": String,
      "picture": String,
      "description": String
    }],
    "points": Long
}
~~~

## Get leaderboard

GET /api/leaderboards/current?numberOfUsers

~~~json
{
  "users": [{
    "name": String,
    "points": Integer,
    "badges": [{
        "href": String,
        "picture": String,
        "description": String
      }]
  }]
}
~~~

## Get all badges

GET /api/badges

~~~json
{
  [{
    "href": String,
    "picture": String,
    "description": String
  }]
}
~~~

## Get one badge

GET /api/badges/id

~~~json
{
  "href": String,
  "picture": String,
  "description": String
}
~~~

## Add a new badge

POST /api/badges

~~~json
{
  "picture": String,
  "description": String
}
~~~

## Edit an existant badge

PUT /api/badges/{id}

~~~json
{
  "picture": String,
  "description": String
}
~~~

## Remove an existant badge

DELETE /api/badges/{id}

## Get all point awards

GET /api/pointawards

~~~json
{
  [{
    "href": String,
    "numberOfPoints": Integer,
    "reason": String
  }]
}
~~~

## Get one point award

GET /api/pointawards/id

~~~json
{
  "href": String,
  "numberOfPoints": Integer,
  "reason": String
}
~~~

## Add a new pointAward

POST /api/pointawards

~~~json
{
  "numberOfPoints": Integer,
  "reason": String
}
~~~

## Edit an existant pointAward

PUT /api/pointawards/{id}

~~~json
{
  "numberOfPoints": Integer,
  "reason": String
}
~~~

## Remove an existant pointAward

DELETE /api/pointawards/{id}

----

# Event

## Post event
When an endUser make an action, an event is posted.
Properties depends of event type, it could even have no property.
The event's name is it's ID.

POST /api/events/
~~~json
{
  "type": String,
  "timestamp": Date,
  "endUserId": String,
  "properties":
    {
      "tag": String,
      "difficulty": String
    }
}
~~~

----

# eventTypes

## Add an event type for the application
POST /api/eventTypes/
~~~json
  {
    "name": String
  }
~~~

## Modify an event type
PUT /api/eventsType/{name}
~~~json
  {
    "name": String
  }
~~~

## Delete an event type
DELETE /api/eventsType/{name}

## Rules
POST /api/rules/
~~~json
  {
    "if":{
      "type": String,
      "properties":{
        "difficulty": String
      }
    },
    "then": {
      "action": String,
      "nbPoints": Integer
    }
  }
~~~

### Rule Example
POST /api/rules/
~~~json
{
  "if":{
    "type": "question",
    "properties":{
      "difficulty": "hard"
    }
  },
  "then": {
    "action": "awardPoint",
    "nbPoints": 3
  }
}
~~~
