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
                $('#rootwizard').find('.pager .next.actionConfig').addClass('disabled');
                $('#rootwizard').find('.pager .finish').hide();
            }

        }});
    $('#rootwizard .finish').click(function () {
        alert('Finished!, Starting over!');
        $('#rootwizard').find("a[href*='tab1']").trigger('click');
    });

    $('#rootwizard').find('.pager .next').addClass('disabled');

    $("#new_event").hide();
    $('#event').change(function () {
        $('#rootwizard').find('.pager .next').addClass('disabled');
        if ($(this).find('option:selected').val() === "new") {
            $("#new_event").show();
        } else if ($(this).find('option:selected').val() === "") {
            $("#new_event").hide();
        } else {
            $('#rootwizard').find('.pager .next').removeClass('disabled');
            $("#new_event").hide();
        }
    });

    $('#new_event').change(function () {
        if ($(this).val() == "") {
            $('#rootwizard').find('.pager .next').addClass('disabled');
        } else {
            $('#rootwizard').find('.pager .next').removeClass('disabled');
        }
    });

    $(".addEventProperties").click(function () {
        var inputName = '<div class="row"><div class="col-xs-3"><label>Name</label></div><div class="col-xs-9"> <input type="text" class="form-control"  name="name" /></div></div>';
        var inputValue = '<div class="row"><div class="col-xs-3"><label>Value</label></div><div class="col-xs-9">  <input type="text" class="form-control" name="value" /></div></div>';
        var remove = '<button class="btn glyphicon glyphicon-minus removeEventProperties"></button>';
        var input = '<div class="row"><div class="col-xs-2 col-sm-1">' + remove + '</div><div class="col-xs-5 col-sm-4">' + inputName + '</div><div class="col-xs-5 col-sm-4">' + inputValue + '</div>';
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
            $('.eventPropertiesList').html('');
            $.each(properties, function (key, prop) {
                var propertiesDisplay = prop.name + " - " + prop.value;
                var input = '<div class="form-group"><label><input type="checkbox" value="' + prop.id + '" name="eventPropertiesId[]"/> ' + propertiesDisplay + '</label></div>';
                $('.eventPropertiesList').append(input);
            });
        });
    });

    $('#pointSelection').change(function () {
        $('#rootwizard').find('.pager .next').addClass('disabled');
        if ($(this).val() != "") {
            console.log('pts');
            $('#rootwizard').find('.pager .next').removeClass('disabled');
        }
    });

    $('.next.actionType').click(function () {
        console.log('asd');
        $('#rootwizard').find('.pager .next.actionConfig').addClass('disabled');
        if ($('input:radio[name="actionType"]:checked') == "point") {
            $('#pointSelection').change(function () {
                $('#rootwizard').find('.pager .next').addClass('disabled');
                if ($(this).val() != "") {
                    console.log('pts');
                    $('#rootwizard').find('.pager .next').removeClass('disabled');
                }
            });
        } else {
            $('input[name=badgeId]').change(function () {
                console.log('badges');
                $('#rootwizard').find('.pager .next').removeClass('disabled');
            });
        }
    });

    $('input:radio[name="actionType"]').change(
            function () {
                if ($(this).is(':checked')) {
                    if ($(this).val() == 'point') {
                        $('#pointSelection').parent().parent().show();
                        $('#badgeSelection').hide();
                    } else if ($(this).val() == 'badge') {
                        $('#badgeSelection').show();
                        $('#pointSelection').parent().parent().hide();
                    }
                }
            }
    );
});