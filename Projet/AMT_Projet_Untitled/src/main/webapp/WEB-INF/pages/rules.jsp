<%@include file="includes/header.jsp" %>
<h1>Create a rule</h1>

<div id="rootwizard">
    <div class="navbar" style="display: none">
        <div class="navbar-inner">
            <div class="container">
                <ul>
                    <li><a href="#tab1" data-toggle="tab">Event Name</a></li>
                    <li><a href="#tab2" data-toggle="tab">Event Properties</a></li>
                    <li><a href="#tab3" data-toggle="tab">Action Type</a></li>
                    <li><a href="#tab4" data-toggle="tab">Config action</a></li>
                    <li><a href="#tab5" data-toggle="tab">Summary</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="bar" class="progress progress-striped active">
        <div class="bar"></div>
    </div>
    <form id="rulesForm" method="post" action="" >
    <div class="tab-content">
        <div class="tab-pane" id="tab1">
            <div class="row row-margin-bottom">
                <div class="col-xs-12">
                    Choose the event name :
                </div>
            </div>
            <div class="row">
                <div class="col-sm-5 col-md-4 col-xs-12">
                    <select name="event" class="form-control" id="event">
                        <option value="">None</option>
                        <c:forEach items="${events}" var="event" varStatus="loop">
                            <option value="${event.getName()}">${event.getName()}</option>
                        </c:forEach>
                        <option value="new">Create New</option>
                    </select>
                </div>
                <div class="col-sm-5 col-md-4 col-xs-12">
                    <input id="new_event" class="form-control" name="new_event" type="text" placeholder="Create new event" />
                </div>
            </div>
            <ul class="pager wizard">
                <li class="next eventName"><a href="javascript:;">Next</a></li>
            </ul>
        </div>  
        <div class="tab-pane" id="tab2">
            <div class="row row-margin-bottom">
                <div class="col-xs-12">
                    Enter/choose the properties of the event type:
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="eventPropertiesList"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 addProperties">
                    <button type="button" class="btn glyphicon glyphicon-plus addEventProperties"></button>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="evenPropertiesAdd"></div>
                </div>
            </div>
            <ul class="pager wizard">
                <li class="previous"><a href="javascript:;">Previous</a></li>
                <li class="next eventProperties"><a href="javascript:;">Next</a></li>
            </ul>
        </div>
        <div class="tab-pane" id="tab3">
            <div class="row row-margin-bottom">
                <div class="col-xs-12">
                    Choose the action type for this event:
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <label><input type="radio" checked value="point" name="actionType"/> Award Points</label>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <label><input type="radio" value="badge" name="actionType"/> Award Badges</label>
                </div>
            </div>
            <ul class="pager wizard">
                <li class="previous"><a href="javascript:;">Previous</a></li>
                <li class="next actionType"><a href="javascript:;">Next</a></li>
            </ul>
        </div>
        <div class="tab-pane" id="tab4">
            <div class="row row-margin-bottom">
                <div class="col-xs-12">
                    Configure action property:
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-4">
                    <input id="pointSelection" type="text" class="form-control" placeholder="Number of points" name="numberOfPoints"/>
                </div>
            </div>
            <div id="badgeSelection" style="display: none;">
                <div class="row">

                    <c:forEach items="${badges}" var="badge" varStatus="loop">
                        <div class="col-sm-4 col-md-3 col-xs-12 badges">
                            <div class="row">
                                <div class="col-xs-12">
                                    ${badge.getDescription()}</div>
                            </div> 
                            <div class="row">
                                <div class="col-xs-12">
                                    <img class="img-responsive img-circle" width="100px" src="static/img/${badge.getPicture()}" />
                                </div>
                            </div> 
                            <div class="row">
                                <div class="col-xs-12">
                                    <input type="radio" name="badgeId" value="${badge.getId()}" />
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>

            <ul class="pager wizard">
                <li class="previous"><a href="javascript:;">Previous</a></li>
                <li class="next actionConfig"><a href="javascript:;">Next</a></li>
            </ul>
        </div>
        <div class="tab-pane" id="tab5">
            <div class="row row-margin-bottom">
                <div class="col-xs-12">
                    Confirm:
                    <button id="submitRulesForm" class="btn btn-success">Save</button>
                </div>
            </div>
            <ul class="pager wizard">
                <li class="previous"><a href="javascript:;">Previous</a></li>
                <li class="next confirm"><a href="javascript:;">Finish</a></li>
            </ul>
        </div>
    </div>
    </form>
</div>
<%@include file="includes/footer.jsp" %>
