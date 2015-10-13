<%-- 
    Document   : app_new
    Created on : Sep 18, 2015, 11:32:18 AM
    Author     : mberthouzoz
--%>

<%@include file="includes/header.jsp" %>
<div id="app_new">
<h2>Register new app</h2>

<div class="container">
    <form class="form-horizontal" id="app_new_form_register" action="pages/app" method="post">
        <input type="hidden" name="action" value="new" style="display: none;"/>
        
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Name</label>
            <div class="col-sm-10">
                <input name="name" type="text" class="form-control" id="name" placeholder="Name" required pattern="${NAME_PATTERN}">
            </div>
        </div>
        
        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">Description</label>
            <div class="col-sm-10">
                <textarea name="description" class="form-control" id="description" placeholder="Description"></textarea>
            </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-2 control-label">API Key</label>
            <div class="col-sm-10">
                <span class="static-form">${apiKey}</span>
            </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-2 control-label">Users</label>
            <div class="col-sm-10">
                <span class="static-form">256'048</span>
            </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-2 control-label">State</label>
            <div class="col-sm-10">
                <button class="btn btn-success">ENABLED&nbsp;</button>
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" onclick="history.back()">Cancel</button>
                <button type="submit" class="btn btn-primary">Register</button>
            </div>
        </div>
    </form>
</div>
</div>
<%@include file="includes/footer.jsp" %>

