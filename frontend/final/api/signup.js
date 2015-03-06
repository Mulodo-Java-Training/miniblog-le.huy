$("#defaultForm").submit(function(event) {
		if(
			$('#firstname').val()=="" || 
			$('#lastname').val()=="" || 
			$('#username').val()==""  ||
			$('#email').val()==""    ||
            $('#password').val()==""            
        ){return;};
		var data = 
            "firstname="+$('#firstname').val()+"&"+
            "lastname="+$('#lastname').val()+"&"+
            "username="+$('#username').val()+"&"+
            "email="+$('#email').val()+"&"+
            "password="+$('#password').val();
      	
      	$.ajax({
		type: "POST",
		data: data,
		url: $apiadd+"user/adduser",
        statusCode: {
			1001: function() {
				alert( "validate error" );
				window.location.reload();
				return;
			},
			2001: function() {
				alert( "please choose another username" );
				window.location.reload();
				return;
			},
        	2002: function() {
				alert( "please choose another email" );
				window.location.reload();
				return;
			},
            2003: function() {
				alert( "error while insert user" );
				window.location.reload();
				return;
			}
            
		},
		success: function (response, status, xhr) {
			if(status!="success"){
				alert( "unknown error" );
				window.location.reload();
				return;
			}
			var ct = xhr.getResponseHeader("content-type") || "";
			if (ct.indexOf('json') > -1) {
				alert( "user create success!" );
				window.location = 'login.html';
			}
		}
		});
});
