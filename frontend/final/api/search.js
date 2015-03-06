function seacrh(query){
	if(query==""){return;}
		if($searchoption==1){
		        $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"user/search/"+query,
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
				$('#infoheader').html("Search by user results");
				$('#pagecontent').html("");
				$.each(response['data'], function(key,value) {
                    $status="inactive";
					if(value['status']){$status="active";}
					$('#pagecontent').append("\
				<h2>\
                    <a href='#' onclick='userinfobyid("+value['id']+")'>"+value['username']+"</a>\
                </h2>\
                <p class='lead'>\
                    Email <a href='mailto:"+value['email']+"''>"+value['email']+"</a>\
                </p>\
                <a class='btn btn-primary' href='#' onclick='loadpostbyuserid("+value['id']+");' >User's Posts <span class='glyphicon glyphicon-chevron-right'></span></a>\
                <hr>");
				});
			}
		}
		});
		return;	
		}
		if($searchoption==2){
		       $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"post/getpost?mode=title&keysearch="+query,
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
				$('#infoheader').html("Search by title results");
				$('#pagecontent').html("");
				$.each(response['data'], function(key,value) {
                    $status="inactive";
					if(value['status']){$status="active";}
					$('#pagecontent').append("\
				<h2>\
                    <a href='#' onclick='loadpostbyid("+value['id']+");'>"+value['title']+" ("+$status+")</a>\
                </h2>\
                <p class='lead'>\
                    by <a href='#'>"+value['username']+"</a>\
                </p>\
                <p><span class='glyphicon glyphicon-time'></span> Posted on "+value['create_at']+"</p>\
                <p><span class='glyphicon glyphicon-time'></span> Modified on "+value['modified_at']+"</p>\
                <hr>");
				});
			}
		}
		});
		return;
		}
	}
