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

##

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
