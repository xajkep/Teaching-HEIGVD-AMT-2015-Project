<%@include file="includes/header.jsp" %>

<div id="rootwizard">
    <div class="navbar">
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
    <div class="tab-content">
        <div class="tab-pane" id="tab1">
            Enter the event name you want to modify/create:
        </div>
        <div class="tab-pane" id="tab2">
            Enter the properties of the choosen/created event type:
        </div>
        <div class="tab-pane" id="tab3">
            Choose the action type for this event:
        </div>
        <div class="tab-pane" id="tab4">
            Configure action:
        </div>
        <div class="tab-pane" id="tab5">
            Confirm:
        </div>
        <ul class="pager wizard">
            <li class="previous"><a href="javascript:;">Previous</a></li>
            <li class="next"><a href="javascript:;">Next</a></li>
            <li class="next finish" style="display:none;"><a href="javascript:;">Finish</a></li>
        </ul>
    </div>
</div>

<%@include file="includes/footer.jsp" %>
