# Project Report


## Administrative information

### Team
Berthouzoz Michael, mberthouzoz, Documentation & coordination

Schowing Thibault, ThibaultSchowing, Tester

Widmer Yannick, younTheory, Service & Model

Zuckschwerdt Benoit, xajkep, User interface

### Tasks realized by the different team members
Generate scaffolding of the application.
Create each view, model, controller.
Implementation of the communcation with the database
Implemenation of the webservice rest
...


## Introduction 

## User Guide

### How to execute and access the application

### How to use the application

### How to update, build and deploy the application

### How to run the automated test procedure


## Design

### System overview

### Gamification features
Explain our choice

### User interface
Image and explain

### REST API

### Design patterns
#### MVC
This design pattern separate the code used for presentation from that which works on and handles data

The controller (which is often a Java servlet) oversees the whole application, calling code in the model (often a JavaBean) to handle the internal logic and business rules and then sending the results to the presentation layer, the view (often made up of JSPs), which interacts with the user.

Model: Implements the data crunching of the application. This is the core code that does the application’s internal work. The model doesn’t know anything about the view or the controller — you just pass it data and it goes from there, returning its results. In online Java applications, the model is often implemented using JavaBeans.

View: Implements the presentation layer that interacts with the user. When the user starts interacting with an online Java application, the Web page(s) they see are part of the view. The view also takes data supplied to it (usually from the controller) and displays it. In online Java applications, the view is often implemented using JSP.

Controller: Acts as the boss of the application and is responsible for routing data to the right model and view components. The controller oversees the model and the view by reacting to the data the user sends. In online Java applications, the controller is often implemented as a servlet.

<img src="./img/MVC-Schema.png">


## Implementation

### Package structure
Project

- Web Pages
	- WEB-INF
		- pages
			- includes
				- footer.jsp
				- header.jsp
			- home.jsp
			- login.jsp
	- static
		- css
			- amt_project.css
			- bootstrap.min.css
			- signin.css
		- fonts
			- glyphicons-halflings-regular.eot
			- glyphicons-halflings-regular.svg
			- glyphicons-halflings-regular.ttf
			- glyphicons-halflings-regular.woff
			- glyphicons-halflings-regular.woff2
		- js
			- bootstrap.min.js
			- jquery.min.js
	- index.jsp
- Source Pakages
	- ch.heigvd.amt.amt_project.models
		- User.java
		- Application.java
	- ch.heigvd.amt.amt_project.services
		- UserDataStore.java
		- ApplicationDataStore.java
	- ch.heigvd.amt.amt_project.web.controllers
		- AuthenticationServlet.java
		- HomeServlet.java
	- ch.heigvd.amt.amt_project.web.flters
		- SecurityFilter.java


### Selected aspects
The Bootstrap 3 library is used for designed the views.

## Testing and validation

### Test strategy

### Tools
#### JMeter
JMeter is ...

#### Selenium
Selenum is ...

### Procedures

### Results


## Known Issues

## Conclusion

## Appending A: Auto Evaluation

