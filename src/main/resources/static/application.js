function uploadFiles() {
		var id = "[[${taid}]]";
		$('#submitbutton').prop('disabled', true);
		$(":submit").attr("disabled", true);
		var fileInput = document.getElementById('fileselect');
		var file = fileInput.files[0];
		var formData = new FormData();
		formData.append('file', file);

		$.ajax({
			type : 'POST',
			url : 'https://ec2-18-189-217-182.us-east-2.compute.amazonaws.com:8443/file/uploadExcelFile/' + id,
			cache : false,
			contentType : 'multipart/form-data',
			processData : false,
			data : formData,
			contentType : false,
			beforeSend: function() {
				$('#img').show();
				$('#mainBody').hide();
			    },
			success : function(resultData) {
				$('#img').hide();
				$('#mainBody').show();
				$('#fileselect').val("");
				var m = document.getElementById('messages');
				m.innerHTML = "";
				$('#submitbutton').prop('disabled', false);
				$("#myModal").modal('show');
			}
		});
	}


function handleLogin() {
	var username = document.getElementById("userId").value;
	var password = document.getElementById("pwd").value;

	var profile = {
		"email_id" : username,
		"password" : password
	}

	$
			.ajax({
				type : 'POST',
				url : "https://ec2-18-189-217-182.us-east-2.compute.amazonaws.com:8443/profile/loginAccount",
				data : JSON.stringify(profile),
				async : false,
				dataType : "json",
				contentType : "application/json",
				success : function(resultData) {
					if (resultData.response_description != 'Success') {
						document.getElementById("errorLabel").textContent = resultData.response_description;
					} else {
						$(location).attr(
								'href',
								'https://ec2-18-189-217-182.us-east-2.compute.amazonaws.com:8443/upload?taId='
										+ resultData.ta_id)
					}
				}
			});

}

function handleRegistration() {

	var data = {
		"first_name" : $("#firstName").val(),
		"last_name" : $("#lastName").val(),
		"password" : $("#password").val(),
		"mobile_number" : $("#mobileNumber").val(),
		"password" : $("#password").val(),
		"email_id" : $("#email").val(),
		"security_question": $("#securityQ").val(),
		"security_answer": $("#securityQAns").val() 
	 }
	$
			.ajax({
				type : 'POST',
				url : "https://ec2-18-189-217-182.us-east-2.compute.amazonaws.com:8443/profile/createAccount",
				data : JSON.stringify(data),
				async : false,
				dataType : "json",
				contentType : "application/json",
				success : function(resultData) {
					if (resultData.response_code != '000') {
						alert(resultData.response_description);
						$("#showAlert").show();
						$("#showAlert").text(resultData.response_description);
					} else {
						$("#showAlert").hide();
						$('#modalRegister').modal('toggle'); 
						$("#myModal").modal('show');
					}
				}
			});

}
