<%-- 
    Document   : register
    Created on : Sep 18, 2015, 11:30:26 AM
    Author     : xajkep
--%>
<%@include file="includes/header.jsp" %>
<div id="register">
<h2>Registration</h2>

<div class="container">
   <form class="form-horizontal" action="pages/account" method="post">
       <input type="hidden" name="action" value="new" style="display: none;"/>
       
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">Email</label>
            <div class="col-sm-9">
                <input name="email" type="email" class="form-control" id="email" placeholder="Email" />
            </div>
        </div>
        
        <div class="form-group">
            <label for="firstname" class="col-sm-3 control-label">First name</label>
            <div class="col-sm-9">
                <input name="firstname" type="text" class="form-control" id="firstname" placeholder="First name" />
            </div>
        </div>
       
       <div class="form-group">
            <label for="lastname" class="col-sm-3 control-label">Last name</label>
            <div class="col-sm-9">
                <input name="lastname" type="text" class="form-control" id="lastname" placeholder="Last name" />
            </div>
        </div>
       
       <div class="form-group">
            <label for="password" class="col-sm-3 control-label">Password</label>
            <div class="col-sm-9">
                <input name="password" type="password" class="form-control" id="password" placeholder="Password" />
            </div>
        </div>
       
       <div class="form-group">
            <label for="password_confirm" class="col-sm-3 control-label">Confirm</label>
            <div class="col-sm-9">
                <input name="password_confirm" type="password" class="form-control" id="password_confirm" placeholder="Confirm password" />
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" id="cancel" onclick="history.back()">Cancel</button>
                <button type="submit" class="btn btn-primary" id="submit">Confirm</button>
            </div>
        </div>
    </form>
</div>
</div>
<%@include file="includes/footer.jsp" %>


