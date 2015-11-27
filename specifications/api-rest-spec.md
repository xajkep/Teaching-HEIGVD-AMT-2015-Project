# API REST - Specifications

## API key

> All requests uses the apiKey in the HTTP header "Authorization" in order to authenticate the application.

> Every rules and event types are bound to an application. If a client want to delete the rule number 35, we will use the apiKey to match the application and the rule.

> If the apiKey is not specified or false, the server return an error 400 with a message (credential error for instance).


## Get user reputation

GET /api/users/id/reputation

~~~json
{
  "badges": [{
      "id": String,
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
        "id": String,
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
    "id": String,
    "picture": String,
    "description": String
  }]
}
~~~

## Get one badge

GET /api/badges/id

~~~json
{
  "id": String,
  "picture": String,
  "description": String
}
~~~

## Get all point awards

GET /api/pointawards

~~~json
{
  [{
    "id": String,
    "numberOfPoints": Integer,
    "reason": String
  }]
}
~~~

## Get one point award

GET /api/pointawards/id

~~~json
{
  "id": String,
  "numberOfPoints": Integer,
  "reason": String
}
~~~
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
