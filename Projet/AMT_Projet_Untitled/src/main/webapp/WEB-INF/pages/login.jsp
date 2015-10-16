<%-- 
    Document   : login
    Created on : Sep 9, 2015, 11:37:49 AM
    Author     : Olivier Liechti (olivier.liechti@heig-vd.ch)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <base href="${pageContext.request.contextPath}/">

    <title>Login Page</title>

    <!-- Bootstrap core CSS -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/signin.css" rel="stylesheet">
    
    <link href="static/css/amt_project.css" rel="stylesheet">

  </head>

  <body>

    <div class="container" id="login">
        
      <form method="POST" action="auth" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <p class="text-center bg-danger">${error}</p>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="hidden" name="action" value="login">
        <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name ="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="btnConfirm">Sign in</button>
        <a href="pages/account?action=new" class="btn btn-lg btn-success btn-block">Sign up</a>
      </form>
        
        <div class="stats">
            <h1>Statistics</h1>
            <hr/>
            ${numberOfAccount} accounts created<br/>
            ${numberOfApplication} applications managed<br/>
            ${numberOfUserDuringLast30Days} users created by application during the last 30 days<br/>
        </div>

    </div> <!-- /container -->

  </body>
</html>

