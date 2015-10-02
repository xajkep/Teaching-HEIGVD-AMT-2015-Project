<%-- 
    Document   : userlist
    Created on : Sep 27, 2015, 1:03:52 PM
    Author     : xajkep
--%>

<%@include file="includes/header.jsp" %>

<h2>List of users for "{$app.name}"</h2>

<div class="container">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Creation date</th>
                <th></th>
            </tr>
        </thead>
        
        <tbody>
            <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>{$user.name}</td>
                <td>{$user.creation_date}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="includes/footer.jsp" %>
