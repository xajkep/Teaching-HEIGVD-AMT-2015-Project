<%-- 
    Document   : app
    Created on : Sep 18, 2015, 11:31:57 AM
    Author     : xajkep
--%>

<%@include file="includes/header.jsp" %>

<h2>Your applications</h2>

<div class="conatiner">
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
            <tr>
                <td>Example</td>
                <td>This is AN EXAMPLE</td>
                <td>ajs09k23ds093h0983zd8aujm3</td>
                <td>999'999</td>
                <td>
                    <a class="btn btn-primary" href="pages/app?action=edit&id=1">Edit</a>
                    <a class="btn btn-success" href="pages/app?action=disable&id=1">ENABLED<a>
                </td> 
            </tr>
            <tr>
                <td>SAMPLE</td>
                <td>This is A SAMPLE</td>
                <td>456awkk2poas9jd9</td>
                <td>2'000</td>
                <td>
                    <a class="btn btn-primary" href="pages/app?action=edit&id=2">Edit</a>
                    <a class="btn btn-danger" href="pages/app?action=enable&id=2">DISABLED<a>
                </td> 
            </tr>
        </tbody>
    </table>
</div>

<%@include file="includes/footer.jsp" %>
