<%-- 
    Document   : account
    Created on : Sep 18, 2015, 11:31:03 AM
    Author     : xajkep
--%>
<%@include file="includes/header.jsp" %>
<div id="edit_account">
    <h2>Edit your account details</h2>

    <div class="container" >
        <form class="form-horizontal" id="edit_account_form" action="pages/account" method="post">
            <input type="hidden" name="action" value="edit" style="display: none;"/>
            <input type="hidden" name="id" value="${user.getId()}" style="display: none;"/>
            
            <div class="form-group">
                <label class="col-sm-3 control-label">Email</label>
                <div class="col-sm-9">
                    <span class="static-form">{user.getEmail()}</span>
                </div>
            </div>
        
        <div class="form-group">
            <label for="firstname" class="col-sm-3 control-label">First name</label>
            <div class="col-sm-9">
                <input name="firstname" type="text" class="form-control" id="firstname" placeholder="First name" required pattern="${NAME_PATTERN}" value="${user.getFirstName()}" />
            </div>
        </div>
       
       <div class="form-group">
            <label for="lastname" class="col-sm-3 control-label">Last name</label>
            <div class="col-sm-9">
                <input name="lastname" type="text" class="form-control" id="lastname" placeholder="Last name" required pattern="${NAME_PATTERN}" value="${user.getLastName()}" />
            </div>
        </div>
       
       <div class="form-group">
            <label for="password" class="col-sm-3 control-label">Password</label>
            <div class="col-sm-9">
                <input name="password" type="password" class="form-control" id="password" placeholder="Password" required />
            </div>
        </div>
       
       <div class="form-group">
            <label for="password_confirm" class="col-sm-3 control-label">Confirm</label>
            <div class="col-sm-9">
                <input name="password_confirm" type="password" class="form-control" id="password_confirm" placeholder="Confirm password" required />

            </div>
        </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-default" id="btnCancel" onclick="history.back()">Cancel</button>
                    <button type="submit" class="btn btn-primary" id="btnConfirm">Confirm</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="includes/footer.jsp" %>

