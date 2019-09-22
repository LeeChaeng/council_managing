<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<layoutTag:layout>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
	body {
		text-align:center;
	}
	a{
		cursor:pointer;
	}
	
	#before_login {
		border : 1px solid;
		padding : 25px;
		left : 50%;
		top : 50%;
		margin-bottom:50px;
	}
</style>
<script>
window.onload = function() {
	log_check();
}

function log_check() {
	if(`${loginInfo_name}` != "") {
		document.getElementById("before_login").style.display = "none";
		document.getElementById("after_login").style.display = "block";
		document.getElementById("go_mail").style.display = "block";
	}
}

function al_submit() {
	alert("고민이 정상 접수 되었습니다.");
	document.getElementById("go_council").submit();
}

</script>
<title>고민상담소</title>
</head>
<body>
<div><img src="/resource/res/img/council.png"></div>
<div class="container" id="after_login" style="display:none">
	<form action="/go_council" id="go_council">
		<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요">
		</div>
		<div class="form-group">
			<label for="writer">작성자</label>
			<p>${loginInfo_email}
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" id="content" name="content" rows="3"></textarea>
		</div>
		<button onclick="al_submit()" class="btn btn-primary btn-sm" style="float:right">작성</button>
	</form>
</div>
<div class="container" id="before_login">
	로그인 하세요!
</div>
<div id="go_mail" style="display:none">
	<a href="/want_council" style="color:black; text-decoration:none"><i class="material-icons" style="font-size:48px;">touch_app</i>내 쪽지함으로</a>	
	</div>
</body>
</html>
</layoutTag:layout>