<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<layoutTag:layout>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>


<div class="container" style="border-left:1px solid grey; border-right:1px solid grey;">
	<div class="col-xs-12" style="margin:15px auto;">
    	<label style="font-size:20px;"><span class="glyphicon glyphicon-list-alt"></span>게시글 상세</label>
	</div>
		<div class="form-group">
			<label>제목</label>
			<p>${detail.title}</p>
		</div>
		<div class="form-group">
			<label>작성자</label>
			<p>${detail.writer}
		</div>
		<div class="form-group">
			<label>작성 날짜</label>
			<p>${detail.reg_date}
		</div>
		<div class="form-group">
			<label>내용</label>
			<p>${detail.content}
		</div>
		<div class="btn-group btn-group-sm" role="group" style="float:right;">
          <button type="button" class="btn btn-default" id="box_authenticated1" onclick="location.href='/delete/${detail.bno}'">삭제</button>
          <button type="button" class="btn btn-default" id="box_authenticated2" onclick="location.href='/update/${detail.bno}'">수정</button>
          <button type="button" class="btn btn-default" onclick="location.href='/list'"> 목록 </button>
        </div>
</div>

<div class="container">
	<label for="content">댓글</label>
	<form name="commentInsertForm">
		<div class="input-group">
			<input type="hidden" name="bno" value="${detail.bno}"/>
			<input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요!">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" name="commentInsertBtn">등록</button>
			</span>
		</div>
	</form>
</div>

<div class="container">
	<div class="commentList"></div>
</div>

</body>
</html>
<script>
//지금 사용자와 글 작성자가 다르다면 박스 안보이기
if(`${loginInfo_name}`!=`${detail.writer}`) {
	document.getElementById("box_authenticated1").style.display="none";
	document.getElementById("box_authenticated2").style.display="none";
}

</script>
<%@ include file="commentS.jsp" %>
</layoutTag:layout>