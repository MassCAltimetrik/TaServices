var emailAddress ="";
$(function () {
	
    $('.list-group.checked-list-box .list-group-item').each(function () {
        
        // Settings
        var $widget = $(this),
            $checkbox = $('<input type="checkbox" class="hidden" />'),
            color = ($widget.data('color') ? $widget.data('color') : "primary"),
            style = ($widget.data('style') == "button" ? "btn-" : "list-group-item-"),
            settings = {
                on: {
                    icon: 'glyphicon glyphicon-check'
                },
                off: {
                    icon: 'glyphicon glyphicon-unchecked'
                }
            };
            
        $widget.css('cursor', 'pointer')
        $widget.append($checkbox);

        // Event Handlers
        $widget.on('click', function () {
            $checkbox.prop('checked', !$checkbox.is(':checked'));
            $checkbox.triggerHandler('change');
            updateDisplay();
        });
        $checkbox.on('change', function () {
            updateDisplay();
        });
          

        // Actions
        function updateDisplay() {
            var isChecked = $checkbox.is(':checked');

            // Set the button's state
            $widget.data('state', (isChecked) ? "on" : "off");

            // Set the button's icon
            $widget.find('.state-icon')
                .removeClass()
                .addClass('state-icon ' + settings[$widget.data('state')].icon);

            // Update the button's color
            if (isChecked) {
                $widget.addClass(style + color + ' active');
            } else {
                $widget.removeClass(style + color + ' active');
            }
        }

        // Initialization
        function init() {
            
            if ($widget.data('checked') == true) {
                $checkbox.prop('checked', !$checkbox.is(':checked'));
            }
            
            updateDisplay();

            // Inject the icon if applicable
            if ($widget.find('.state-icon').length == 0) {
                $widget.prepend('<span class="state-icon ' + settings[$widget.data('state')].icon + '"></span>');
            }
        }
        init();
    });
    
    $('#get-checked-data').on('click', function(event) {
        event.preventDefault(); 
        var checkedItems = "";
        emailAddress ="";
        $("#check-list-box li.active").each(function(idx, li) {
            checkedItems += $(li).text()+', &nbsp;';
            emailAddress +=$(li).attr("value")+',';
        });
        $('#display-selected').html(checkedItems, null, '\t');
    });
   
});

function sendEmail(){
	 var emailBody = $("#emailBody").val();
	 var emailRequest = {
				"emailIds" : emailAddress,
				"emailBody" : emailBody,
				"emailSubject" : $("#subject").val()
			}
	 $.ajax({
			type : 'POST',
			url : 'https:///ec2-18-189-217-182.us-east-2.compute.amazonaws.com:8443/email',
			contentType : "application/json",
			data : JSON.stringify(emailRequest),
			beforeSend: function() {
				$('#img').show();
				$('#mainBody').hide();
			    },
			success : function(resultData) {
				$('#img').hide();
				$('#mainBody').show();
				$("#subject").val("");
				$("#emailBody").val("");
				$("#check-list-box li.active").each(function(idx, li) {
					$(li).removeClass().addClass('list-group-item');
					$('li span').removeClass().addClass('state-icon glyphicon glyphicon-unchecked');
		        });
				$('#display-selected').html(" ", null, '\t');
				$("#myModal").modal('show');
			}
		});
};