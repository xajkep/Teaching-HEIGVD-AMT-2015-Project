<%-- 
    Document   : app
    Created on : Sep 18, 2015, 11:31:57 AM
    Author     : xajkep
--%>

<%@include file="includes/header.jsp" %>


<h2 class="pull-left">Your applications</h2><br/>
<a class="btn btn-primary pull-right" href="pages/app?action=new">New app</a>


<div class="container">
    
    
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Names</th>
                <th>Descriptions</th>
                <th>Api Key</th>
                <th>Users</th>
                <th></th>
            </tr>
        </thead>
        
        <tbody>
            <c:forEach items="${allApps}" var="app">
            <tr>
                <td>${app.name}</td>
                <td>${app.description}</td>
                <td>${app.api_key}</td>
                <td>${app.users}</td>
                <td>
                    <a class="btn btn-primary" href="pages/app?action=edit&id=1">Edit</a>
                    <a class="btn btn-success" href="pages/app?action=disable&id=1">ENABLED&nbsp;<a>
                </td> 
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="includes/footer.jsp" %>
