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
    
    
    
    $("#new_event_property").hide();
    $('#eventProperties').change(function () {
        if ($(this).find('option:selected').val() === "new") {
            $("#new_event_property").show();
        } else {
            $("#new_event_property").hide();
        }
    });
    
});