<%-- 
    Document   : register
    Created on : Sep 18, 2015, 11:30:26 AM
    Author     : xajkep
--%>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <base href="${pageContext.request.contextPath}/">

    <title>Register Page</title>

    <!-- Bootstrap core CSS -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/signin.css" rel="stylesheet">
    
    <link href="static/css/amt_project.css" rel="stylesheet">

  </head>

  <body>

<div id="register">


<div class="container">
    <h2>Registration</h2>
   <form class="form-horizontal" action="pages/account" method="post">
       <p class="text-center bg-danger">${error}</p>
       <input type="hidden" name="action" value="new" style="display: none;"/>
       
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">Email</label>
            <div class="col-sm-9">
                <input name="email" type="email" class="form-control" id="email" placeholder="Email" value="${email}" required/>
            </div>
        </div>
        
        <div class="form-group">
            <label for="firstname" class="col-sm-3 control-label">First name</label>
            <div class="col-sm-9">
                <input name="firstname" type="text" class="form-control" id="firstname" placeholder="First name" value="${firstName}" required/>
            </div>
        </div>
       
       <div class="form-group">
            <label for="lastname" class="col-sm-3 control-label">Last name</label>
            <div class="col-sm-9">
                <input name="lastname" type="text" class="form-control" id="lastname" placeholder="Last name" value="${lastName}" required/>
            </div>
        </div>
       
       <div class="form-group">
            <label for="password" class="col-sm-3 control-label">Password</label>
            <div class="col-sm-9">
                <input name="password" type="password" class="form-control" id="password" placeholder="Password"/>
            </div>
        </div>
       
       <div class="form-group">
            <label for="password_confirm" class="col-sm-3 control-label">Confirm</label>
            <div class="col-sm-9">
                <input name="password_confirm" type="password" class="form-control" id="password_confirm" placeholder="Confirm password" />
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a type="button" class="btn btn-default" id="cancel" href="pages/home">Cancel</a>
                <button type="submit" class="btn btn-primary" id="submit">Confirm</button>
            </div>
        </div>
    </form>
</div>
</div>
  </body>
</html>


