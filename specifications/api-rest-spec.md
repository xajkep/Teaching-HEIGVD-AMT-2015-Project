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
    "points": Integer
}
~~~

## Get leaderboard

GET /api/leaderboards/current?numberOfUsers

~~~json
{
  "users": [{
    "id": String,
    "email": String,
    "points": Integer
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
