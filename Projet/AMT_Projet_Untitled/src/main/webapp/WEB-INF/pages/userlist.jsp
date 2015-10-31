<%-- 
    Document   : userlist
    Created on : Sep 27, 2015, 1:03:52 PM
    Author     : xajkep
--%>

<%@include file="includes/header.jsp" %>
<div id="userlist">
    <h2>List of users for <b>${app.getName()}</b></h2>
    <br/>

<div class="container">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Creation date</th>
            </tr>
        </thead>
        
        <tbody>
            <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.getName()}</td>
                <td>${user.getDate()}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <div style="text-align: center;">
        <a href="pages/app?action=userlist&id=${user.getId()}&page=1">&lt;&lt;</a> <a href="pages/app?action=userlist&id=${user.getId()}&page=${prev_page}">&lt;</a> ${current_page}/${total_page} <a href="pages/app?action=userlist&id=${user.getId()}&page=${next_page}">&gt;</a> <a href="pages/app?action=userlist&id=${user.getId()}&page=${total_page}">&gt;&gt;</a>
    </div>
</div>
</div>
<%@include file="includes/footer.jsp" %>
