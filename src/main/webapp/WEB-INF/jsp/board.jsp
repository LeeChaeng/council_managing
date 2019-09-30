<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link type="text/css" rel="stylesheet"
	href="resource/res/css/sample.css" />
	
<script type="text/javaScript" language="javascript" defer="defer">
    </script>
</head>

<body
	style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
	<form id="listForm" name="listForm" action="/sample/" method="post">
		<input type="hidden" name="selectedId" />
		<div id="content_pop">
			<!-- 타이틀 -->
			<div id="title">
				<ul>
					<li>자유게시판</li>
				</ul>
			</div>
			<!-- // 타이틀 -->
			
			<!-- List -->
			<div id="table">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					summary="">
					<caption style="visibility: hidden"></caption>
					<colgroup>
						<col width="40" />
						<col width="200" />
						<col width="50" />
						<col width="80" />
						<col width="50" />
					</colgroup>
					<tr>
						<th align="center">No</th>
						<th align="center">제목</th>
						<th align="center">작성자</th>
						<th align="center">작성일</th>
						<th align="center">조회</th>
					</tr>

					<tr>
						<td align="center" class="listtd">1</td>
						<td align="center" class="listtd"><a href="">오늘 저녁 뭐먹지?</a></td>
						<td align="left" class="listtd">이채영&nbsp;</td>
						<td align="center" class="listtd">2019.03.18&nbsp;</td>
						<td align="center" class="listtd">0&nbsp;</td>
					</tr>

				</table>
			</div>
			<div id="sysbtn">
				<ul>
					<li><span class="btn_blue_l"> <a href="">등록</a> <img
							src="/resource/res/img/btn_bg_r.gif" style="margin-left: 6px;"
							alt="" />
					</span></li>
				</ul>
			</div>
		</div>
	</form>
</body>
</html>
