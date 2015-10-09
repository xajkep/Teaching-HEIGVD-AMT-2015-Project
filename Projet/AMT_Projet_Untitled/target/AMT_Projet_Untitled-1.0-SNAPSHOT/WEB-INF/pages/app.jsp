<%-- 
    Document   : app
    Created on : Sep 18, 2015, 11:31:57 AM
    Author     : xajkep
--%>

<%@include file="includes/header.jsp" %>

<div id="app">
    <h2 class="pull-left">Your applications</h2><br/>
    <a class="btn btn-primary pull-right" href="pages/app?action=new">New app</a>


    <div class="container">


        <table class="table table-bordered" id="appTable">
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
                <c:forEach items="${apps}" var="app">
                <tr>
                    <td>${app.getName()}</td>
                    <td>${app.getDescription()}</td>
                    <td>${app.getKey().getApiKey()}</td>
                    <td>-1</td>
                    <td>
                        <a class="btn btn-primary" id="app_edit_button" href="pages/app?action=edit&id=${app.getId()}">Edit</a>
                        <a class="btn btn-success" id="app_state_button" href="pages/app?action=disable&id=${app.getId()}">ENABLED&nbsp;<a>
                    </td> 
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="includes/footer.jsp" %>
