# API REST - Specifications

## API key
All requests uses the apiKey in the HTTP header "Authorization" in order to authenticate the application

## Get user badges

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
    "id": String,
    "firstname": String,
    "lastname": String,
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

## Post event
Properties depends of event type, it could even have no property.

POST /api/events/
~~~json
{
  "name": String,
  "timestamp": Date,
  "endUserId": String,
  "properties":
    {
      "tag": String,
      "difficulty": String
    }
}
~~~

## Add an event type for the application
POST /api/eventsManager/
~~~json
  {
    "name": String,
    "properties": {
      "propertyA": String,
      "propertyB": String
    }
  }
~~~

## Modify an event type
PUT /api/eventsManager/{name}
~~~json
  {
    "name": String,
    "properties": {
      "propertyA": String,
      "propertyB": String
    }
  }
~~~

## Delete an event type
DELETE /api/eventsManager/{name}
