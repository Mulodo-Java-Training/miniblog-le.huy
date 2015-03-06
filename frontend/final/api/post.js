function toppost(){
        $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"post/getpost?mode=top&limit=10",
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
				$('#infoheader').html("Top posts");
				$('#pagecontent').html("");
				$.each(response['data'], function(key,value) {
                    $status="inactive";
					if(value['status']){$status="active";}
					$('#pagecontent').append("\
				<h2>\
                    <a href='#' onclick='loadpostbyid("+value['id']+");'>"+value['title']+" ("+$status+")</a>\
                </h2>\
                <p class='lead'>\
                    by <a href='#' onclick='userinfobyid("+value['userid']+")'>"+value['username']+"</a>\
                </p>\
                <p><span class='glyphicon glyphicon-time'></span> Posted on "+value['create_at']+"</p>\
                <p><span class='glyphicon glyphicon-time'></span> Modified on "+value['modified_at']+"</p>\
                <hr>");
				});
			}
		}
		});
	}
function loadpostcurrentuser(){
        $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"post/getpost?mode=current",
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
				$('#infoheader').html("Your posts");
				$('#pagecontent').html("");
				$.each(response['data'], function(key,value) {
                    $status="inactive";
					if(value['status']){$status="active";}
					$('#pagecontent').append("\
				<h2>\
                    <a href='#' onclick='loadpostbyid("+value['id']+");'>"+value['title']+" ("+$status+")</a>\
                </h2>\
                <p class='lead'>\
                    by <a href='#' onclick='userinfobyid("+value['userid']+")'>"+value['username']+"</a>\
                </p>\
                <p><span class='glyphicon glyphicon-time'></span> Posted on "+value['create_at']+"</p>\
                <p><span class='glyphicon glyphicon-time'></span> Modified on "+value['modified_at']+"</p>\
                <hr>");
				});
			}
		}
		});
	}
function loadpostbyuserid(userid){
        $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"post/getpost?mode=userid&id="+userid,
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
				$('#infoheader').html("Top posts");
				$('#pagecontent').html("");
				$.each(response['data'], function(key,value) {
                    $status="inactive";
					if(value['status']){$status="active";}
					$('#pagecontent').append("\
				<h2>\
                    <a href='#' onclick='loadpostbyid("+value['id']+");'>"+value['title']+" ("+$status+")</a>\
                </h2>\
                <p class='lead'>\
                    by <a href='#' onclick='userinfobyid("+value['userid']+")'>"+value['username']+"</a>\
                </p>\
                <p><span class='glyphicon glyphicon-time'></span> Posted on "+value['create_at']+"</p>\
                <p><span class='glyphicon glyphicon-time'></span> Modified on "+value['modified_at']+"</p>\
                <hr>");
				});
			}
		}
		});
	}

function loadpostbyid(postid){
    $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"post/getpost?mode=postid&id="+postid.toString(),
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
			3005: function() {
				alert( "invalid post id!" );
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
				$('#infoheader').html("Post Info");
                $status="inactive";
				if(response['data']['status']){$status="active";}

				$('#pagecontent').html("\
                <h2>\
                    <a>"+response['data']['title']+" ("+$status+")</a>\
                </h2>\
                <p class='lead>\
                    by <a href='#' onclick='userinfobyid("+response['data']['userid']+")'>"+response['data']['username']+"</a>\
                </p>\
                <p><span class='glyphicon glyphicon-time'></span> Posted on "+response['data']['create_At']+"</p>\
                <p><span class='glyphicon glyphicon-time'></span> Modified on "+response['data']['modified_At']+"</p>\
                <p>"+response['data']['content']+"</p>\
                <div class='actionBoxfacebook'>\
					<ul class='commentList' id='commentsofpost'></ul>\
				<div class='form-inline'>\
					<div class='form-group'>\
						<input class='form-control' type='text' placeholder='Your comments' id='newcomment'/>\
					</div>\
					<div class='form-group'>\
						<button class='btn btn-default' href='#' onclick='addcomment("+response['data']['id']+");'>Add</button>\
					</div>\
				</div>\
				</div>\
                ");
                loadcomments(response['data']['id']);
			}
		}
		});
	}


function addcomment(postid){
		if($('#newcomment').val()==""){alert("write something please ^.^!");return;}
		var data = "postid="+postid+"&comment="+$('#newcomment').val();
      	$.ajax({
		type: "POST",
		beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		data: data,
		url: $apiadd+"comment/add",
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
			3005: function() {
				alert( "invalid post id!" );
				return;
			},
			4001: function() {
				alert( "add comment fail" );
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
				alert( "comment added!" );
				loadpostbyid(postid);
				return;
			}
		}
		});
	
	}
function loadcomments(postid){
	    $.ajax({
		type: "GET",
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"comment/get?mode=postid&id="+postid.toString(),
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
			3005: function() {
				alert( "invalid post id!" );
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
				$userid=getCookie('userid');
				$('#commentsofpost').html("");
				$.each(response['data'], function(key,value) {
					$status="inactive";
					$thebody="";
					if(response['data']['status']){$status="active";}
						$thebody=$thebody+("\
							<li>\
								<div class='commenterImage'>\
									<p> "+value['username']+": </p>\
								</div><br>\
								<div class='commentText'>\
									<p ");
									if(value['userId']==$userid){$thebody=$thebody+("\
										onclick='$(this).editable({success: function(response, newValue){editcomment(newValue,"+postid+","+value['id']+");}})'");}
									$thebody=$thebody+("\
									href='#'>"+value['comment']+"</p> <span class='date sub-text'>on "+value['modified_At']+"</span>\
								</div>\
							</li>\
						");
						$('#commentsofpost').append($thebody);
				});
			}
		}
		});
	}
	
function editcomment(comment,postid,commentid){
	if(comment==""){alert("write something please ^.^!");return;}
		var data = "postid="+postid+"&id="+commentid+"&comment="+comment;
      	$.ajax({
		type: "PUT",
		beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		data: data,
		url: $apiadd+"comment/edit",
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
			4004: function() {
				alert( "you dont own this post =.=!" );
				return;
			},
			4002: function() {
				alert( "edit comment fail" );
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
				alert( "comment edited!" );
				loadpostbyid(postid);
				return;
			}
		}
		});
	}

function createpost(){
	tinyMCE.activeEditor.save();
	if($('#newpost').val()==""||$('#newpost').val().length<17){alert("write something please!!!");return;}
	if($('#newposttitle').val()==""||$('#newposttitle').val().length<10){alert("title please!!!");return;}
	var data = "title="+$('#newposttitle').val()+"&content="+$('#newpost').val();
        $.ajax({
		type: "POST",
		data: data,
        beforeSend: function (request)
            {
                request.setRequestHeader("access_token", getCookie('token'));
            },
		url: $apiadd+"post/add",
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
			3001: function() {
				alert( "post create fail!" );
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
				window.location = 'homepage.html';
				loadpostbyid(response['data']);
			}
		}
		});
	}
