function logout() {
      	$.ajax({
		type: "POST",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"user/logout",
        statusCode: {
			1001: function() {
				alert( "validate error" );
				window.location.reload();
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
        	2005: function() {
				alert( "logout error!" );
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
				alert( "user logout success!" );
				var now = new Date();
				//document.cookie = 'token='+JSON.stringify(response['data'])+';expires='+now.toGMTString()+';';
				document.cookie = 'token=;expires='+now.toGMTString()+';';
				document.cookie = 'userid=;expires='+now.toGMTString()+';';
				window.location = 'login.html';
			}
		}
		});
};

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
    }
    return "";
}
