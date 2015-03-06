$firstname="";
$lastname="";
$changed=false;
$username="";
function userinfo(){
	    $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"user/user?mode=current",
        statusCode: {
			1001: function() {
				alert( "validate error" );
				return;
			},
			2008: function() {
				alert( "token is expired!" );
				var now = new Date();
				document.cookie = 'token=;expires='+now.toGMTString()+';';
				document.cookie = 'userid=;expires='+now.toGMTString()+';';
				window.location = 'login.html';
				return;
			},
        	2009: function() {
				alert( "invalid user id" );
				var now = new Date();
				document.cookie = 'token=;expires='+now.toGMTString()+';';
				document.cookie = 'userid=;expires='+now.toGMTString()+';';
				window.location = 'login.html';
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
				$firstname=response['data']['firstname'];
				$lastname=response['data']['lastname'];
				$username=response['data']['username'];
				$('#infoheader').html("userinfo");
				$('#pagecontent').html("\
				<h2>"
                    +response['data']['username']+
                "</h2>\
                <p>\
					<p>Firstname: <p  id='firstname' onclick='$(this).editable({success: function(response, newValue){$firstname=newValue;$changed=true;$(\"#updatebutton\").show();}});' href='#'>"+response['data']['firstname']+"</p></p>\
					<p>Lastname: <p  id='lastname' onclick='$(this).editable({success: function(response, newValue){$lastname=newValue;$changed=true;$(\"#updatebutton\").show();}});' href='#'>"+response['data']['lastname']+"</p></p>\
				<div id='updatebutton' style='display:none' >\
					<p>Password: <input type='password' id='password'/></p><br>\
					<a class='btn btn-primary' onclick='updateuser();' href='#'>Update<span class='glyphicon glyphicon-chevron-right'></span></a>\
                </div>\
					<a class='btn btn-primary' onclick='changepassword();' href='#'>Change password<span class='glyphicon glyphicon-chevron-right'></span></a>\
					<p>Email: <a href='mailto:"+response['data']['email']+"'>"+response['data']['email']+"</a></p>\
                    <span class='glyphicon glyphicon-time'></span> created at "+response['data']['create_At']+" <br>\
                    <span class='glyphicon glyphicon-time'></span> modified at "+response['data']['modified_At']+" <br>\
                </p>");
                    
			}
		}
		});
	}
function userinfobyid(userid){
	    $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"user/user?mode=id&id="+userid,
        statusCode: {
			1001: function() {
				alert( "validate error" );
				return;
			},
			2008: function() {
				alert( "token is expired!" );
				var now = new Date();
				document.cookie = 'token=;expires='+now.toGMTString()+';';
				document.cookie = 'userid=;expires='+now.toGMTString()+';';
				window.location = 'login.html';
				return;
			},
        	2009: function() {
				alert( "invalid user id" );
				var now = new Date();
				document.cookie = 'token=;expires='+now.toGMTString()+';';
				document.cookie = 'userid=;expires='+now.toGMTString()+';';
				window.location = 'login.html';
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
				$firstname=response['data']['firstname'];
				$lastname=response['data']['lastname'];
				$username=response['data']['username'];
				$('#infoheader').html("userinfo");
				$('#pagecontent').html("\
				<h2>"
                    +response['data']['username']+
                "</h2>\
                <p>\
					<p>Firstname: <p  id='firstname' href='#'>"+response['data']['firstname']+"</p></p>\
					<p>Lastname: <p  id='lastname' href='#'>"+response['data']['lastname']+"</p></p>\
					<p>Email: <a href='mailto:"+response['data']['email']+"'>"+response['data']['email']+"</a></p>\
                    <span class='glyphicon glyphicon-time'></span> created at "+response['data']['create_At']+" <br>\
                    <span class='glyphicon glyphicon-time'></span> modified at "+response['data']['modified_At']+" <br>\
                </p>");
                    
			}
		}
		});
	}
function updateuser(){
	if($changed==false){alert("nothing to change");return;}
	if($firstname=="" || $lastname=="" || $('#password').val()==""){alert("info and password may not be null");return;};
		
		var data = "firstname="+$firstname+"&lastname="+$lastname+"&password="+$('#password').val();
	
		$.ajax({
		type: "PUT",
		data: data,
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"user/user",
        statusCode: {
			1001: function() {
				alert( "validate error" );
				return;
			},
			2008: function() {
				alert( "token is expired!" );
				var now = new Date();
				document.cookie = 'token=;expires='+now.toGMTString()+';';
				document.cookie = 'userid=;expires='+now.toGMTString()+';';
				window.location = 'login.html';
				return;
			},
        	2006: function() {
				alert( "update user fail" );
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
				userinfo();
			}
		}
		});
	}

function changepassword(){
	$('#infoheader').html("Change password");
	$('#pagecontent').html("\
				<h2>"
                    +$username+
                "</h2>\
                <p>\
					<p>Old Password: <input type='password' id='oldpassword'/></p><br>\
					<p>New Password: <input type='password' id='newpassword'/></p><br>\
					<p>Re-Type Password: <input type='password' id='retypepassword'/></p><br>\
					<a class='btn btn-primary' onclick='chpass();' href='#'>Confirm<span class='glyphicon glyphicon-chevron-right'></span></a>\
                </p>");
	}
function chpass(){
	if($('#oldpassword').val()=="" || $('#newpassword').val()=="" || $('#retypepassword').val()==""){alert("password must not be null");return;}
	if($('#newpassword').val()!=$('#retypepassword').val()){alert("retypepassword must match");return;}
	var data = "old_password="+$('#oldpassword').val()+"&new_password="+$('#newpassword').val();
	$.ajax({
		type: "PUT",
		data: data,
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"user/pass",
        statusCode: {
			1001: function() {
				alert( "validate error" );
				return;
			},
			2008: function() {
				alert( "token is expired!" );
				var now = new Date();
				document.cookie = 'token=;expires='+now.toGMTString()+';';
				document.cookie = 'userid=;expires='+now.toGMTString()+';';
				window.location = 'login.html';
				return;
			},
        	2007: function() {
				alert( "update password fail" );
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
				alert( "updated password of current user" );
				var now = new Date();
				var time = now.getTime();
				var expireTime = time + 1000*3600*12;
				now.setTime(expireTime);
				//document.cookie = 'token='+JSON.stringify(response['data'])+';expires='+now.toGMTString()+';';
				document.cookie = 'token='+response['data']['access_token']+';expires='+now.toGMTString()+';';
				document.cookie = 'userid='+response['data']['userId']+';expires='+now.toGMTString()+';';
				userinfo();
			}
		}
		});
}
