<%-- 
    Document   : app_edit
    Created on : Sep 18, 2015, 11:32:18 AM
    Author     : xajkep
--%>
<%@include file="includes/header.jsp" %>
<div id="app_edit">
<h2>App details</h2>

<div class="container">
   <form class="form-horizontal" id="app_edit_form" action="pages/app" method="post">
       <input type="hidden" name="action" value="edit" style="display: none;"/>
       <input type="hidden" name="id" value="${id}" style="display: none;"/>
       
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Name</label>
            <div class="col-sm-10">
                <input name="name" type="text" class="form-control" id="name" placeholder="Name" required pattern="${NAME_PATTERN}" value="${name}">
            </div>
        </div>
        
        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">Description</label>
            <div class="col-sm-10">
                <textarea name="description" class="form-control" id="description" placeholder="Description">${description}</textarea>
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
                <span class="static-form">${endUsers}</span>
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
                <button type="button" class="btn btn-default" id="cancel" onclick="history.back()">Cancel</button>
                <button type="submit" class="btn btn-primary" id="submit">Update</button>
            </div>
        </div>
    </form>
</div>
</div>
<%@include file="includes/footer.jsp" %>

