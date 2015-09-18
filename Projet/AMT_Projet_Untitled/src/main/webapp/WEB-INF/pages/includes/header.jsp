<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="${pageContext.request.contextPath}/">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>AMT PROJECT</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/amt_project.css" rel="stylesheet">
    <script src="static/js/jquery.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    

  </head>
  <body>
    <div class="row">
      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">AMT PROJECT</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="pages/about">About</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Examples <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="pages/beers">Beers</a></li>
                  <li><a href="pages/ugly">Ugly servlet</a></li>
                  <li><a href="data/json">Json servlet</a></li>
                  <li><a href="data/graph">Graph servlet</a></li>
                  <li><a href="pages/ajax">AJAX page</a></li>
                  <!--
                  <li role="separator" class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  -->
                </ul>
              </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="./auth?action=logout">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>