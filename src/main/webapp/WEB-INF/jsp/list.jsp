<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<layoutTag:layout>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMMUNITY</title>
</head>
<body>
<div class="container" style="border-left:1px solid grey; border-right:1px solid grey;">
	<div class="col-xs-12" style="margin:15px auto;">
        <label style="font-size:20px;"><span class="glyphicon glyphicon-list-alt"></span>게시글 목록</label>
        <button class="btn btn-primary btn-sm" style="float:right;" onclick="location.href='/insert'">글쓰기</button>
    </div>
    
	<table class="table table-hover">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
			<c:forEach var="item" items="${list}">
				<tr onclick="location.href=`/detail/${item.bno}`">
					<td>${item.bno}</td>
					<td>${item.title}</td>
					<td>${item.writer}</td>
					<td>${item.reg_date}</td>
					<td>${item.hits}</td>
				</tr>
			</c:forEach>
	</table>
	<div class="box-footer">
		<div class="text-center">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li><a href="/list?page=${pageMaker.startPage-1}">이전</a></li>
				</c:if>
				
				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					<li <c:out value="${pageMaker.cri.page == idx ? active : '' }"/>>
						<a href="/list?page=${idx}">${idx}</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageMaker.next && pageMaker.endPage>0}">
					<li><a href="/list?page=${pageMaker.endPage+1}">다음</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>


</body>
</html>
</layoutTag:layout>