<%@include file="includes/header.jsp" %>
<div id="home">
<h2>Welcome to the AMT project !!!</h2>

<div class="alert alert-info" role="alert">
  You are logged in as ${principal}.
</div>

<div class="container">
    <div class="stats">
        <h2>Statistics</h2>
        <hr/>
        ${numberOfAccount} accounts created<br/>
        ${numberOfApplication} applications managed<br/>
        ${numberOfUserDuringLast30Days} users created by application during the last 30 days<br/>
    </div>
</div>
</div>
<%@include file="includes/footer.jsp" %>
