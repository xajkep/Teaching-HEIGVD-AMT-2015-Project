$(document).ready(function () {
    $('#rootwizard').bootstrapWizard({onTabShow: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;
            var $percent = ($current / $total) * 100;
            $('#rootwizard').find('.bar').css({width: $percent + '%'});

            // If it's the last tab then hide the last button and show the finish instead
            if ($current >= $total) {
                $('#rootwizard').find('.pager .next').hide();
                $('#rootwizard').find('.pager .finish').show();
                $('#rootwizard').find('.pager .finish').removeClass('disabled');
            } else {
                $('#rootwizard').find('.pager .next').show();
                $('#rootwizard').find('.pager .finish').hide();
            }

        }});
    $('#rootwizard .finish').click(function () {
        alert('Finished!, Starting over!');
        $('#rootwizard').find("a[href*='tab1']").trigger('click');
    });

    $("#new_event").hide();
    $('#event').change(function () {
        if ($(this).find('option:selected').val() === "new") {
            $("#new_event").show();
        } else {
            $("#new_event").hide();
        }
    });
    
    $(".addEventProperties").click( function () {
        var inputName = '<label>Name</label> <input type="text" class="form-control"  name="name" />';
        var inputValue = '<label>Value</label> <input type="text" class="form-control" name="value" />';
        var remove = '<button class="glyphicon glyphicon-minus removeEventProperties"></button>';
        var input = '<div class="row"><div class="col-xs-2 col-sm-1">'+remove+'</div><div class="col-xs-5 col-sm-4">'+inputName +'</div><div class="col-xs-5 col-sm-4">'+ inputValue+'</div>';
       $('.evenPropertiesAdd').append(input);
       $('.removeEventProperties').click(function () {
          $(this).parent().parent().remove();
       });
    });



    $("#new_event_property").hide();
    $('#eventProperties').change(function () {
        if ($(this).find('option:selected').val() === "new") {
            $("#new_event_property").show();
        } else {
            $("#new_event_property").hide();
        }
    });

    $(".next.eventName").click(function () {
        var eventName = $('#event').val();
        if (eventName == "new") {
            eventName = $('#new_event').val();
        }
        console.log(window.location.href + "&ajax=properties&eventName=" + eventName);
        $.get(window.location.href + "&ajax=properties&eventName=" + eventName, function (properties) {
            
            $.each(properties, function(key, prop) {
                var propertiesDisplay = prop.name + " - " + prop.value;
                var input = '<div class="form-group"><label><input type="checkbox" value="' + prop.id + '" name="eventPropertiesId"/> '+ propertiesDisplay+'</label></div>';
                $('.eventPropertiesList').append(input);
            });
            
            $('#rootwizard').find("a[href*='tab2']").trigger('click');
        })
    });

    $(".next.eventProperties").click(function () {
        $.get(window.location.href + "&ajax=XX", function () {
            $('#rootwizard').find("a[href*='tab3']").trigger('click');
        })
    });

    $(".next.actionType").click(function () {
        $.get(window.location.href + "&ajax=XX", function () {
            $('#rootwizard').find("a[href*='tab4']").trigger('click');
        })
    });

    $(".next.actionConfig").click(function () {
        $.get(window.location.href + "&ajax=XXX", function () {
            $('#rootwizard').find("a[href*='tab5']").trigger('click');
        })
    });
});