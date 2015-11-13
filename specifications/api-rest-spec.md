# API REST - Specifications

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
