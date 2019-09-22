<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<layoutTag:layout>
<!DOCTYPE html>
<html>
<head>
<script>
</script>
<meta charset="UTF-8">
<style>
ul.inbox-nav li a {
	color:#6a6a6a;
    display: inline-block;
    line-height:45px;
    margin: 0;
    padding: 0 20px;
    width: 100%;
}

a {
	text-decoration:none;
}

ul {
	list-style-type:none;
}

.inbox-divider {
    border-bottom: 1px solid #d5d8df;
}

aside {
	display:table-cell;
	float:left;
	height:100%;
	padding:0;
	verticatl-aligh:top;
}

.inbox-body {
	padding:20px;
	float:right;
	width:950px;
	height:400px;
}

.btn {
	display:inline-block;
	padding:6px 12px;
	margin-bottom:0px;
	text-align:center;
	cursor:pointer;
	border:1px solid transparent;
}

.btn-compose {
	background:none repeat scroll 0 0 #ff6c60;
	color:#fff;
}

.table-inbox tr.unread td{
	background:none repeat scroll 0 0 #f7f7f7;
	font-weight:600;
}
</style>
<title>상담을받자!</title>
</head>
<body>
	<div class="container">
		<aside class="sm-side">
			<ul class="inbox-nav">
			<a href="#myModal" data-toggle="modal"  title="Compose" class="btn btn-compose">
                             편지 보내기
                          </a>
                          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade" style="display: none;">
                              <div class="modal-dialog">
                                  <div class="modal-content">
                                      <div class="modal-header">
                                          <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                          <h4 class="modal-title">편지 보내기</h4>
                                      </div>
                                      <div class="modal-body">
                                          <form role="form" action="/send_message" class="form-horizontal">
                                          <div class="form-group">
                                                  <label class="col-lg-2 control-label" for="email">From</label>
                                                  <div class="col-lg-10">
                                                      ${loginInfo_email}
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">To</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" name="receiver" class="form-control">
                                                  </div>
                                              </div>
                                              
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Subject</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" id="inputPassword1" name="title" class="form-control">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Message</label>
                                                  <div class="col-lg-10">
                                                      <textarea rows="10" cols="30" class="form-control" id="" name="content"></textarea>
                                                  </div>
                                              </div>

                                              <div class="form-group">
                                                  <div class="col-lg-offset-2 col-lg-10">
                                                      <span class="btn green fileinput-button">
                                                        <i class="fa fa-plus fa fa-white"></i>
                                                        
                                                      </span>
                                                      <button class="btn btn-send" type="submit">전송</button>
                                                  </div>
                                              </div>
                                          </form>
                                      </div>
                                  </div><!-- /.modal-content -->
                              </div><!-- /.modal-dialog -->
                          </div>
            	<li class="inbox-divider">
                	<a href="/want_council" style="font-weight:600;"><i class="fa fa-inbox"></i> 받은 편지함 </a>
                </li>
                <li class="inbox-divider">
                	<a href="/want_council/send"><i class="fa fa-envelope-o"></i> 보낸 편지함</a>
                </li>
            </ul>
        </aside>
        <div class="inbox-body" style="border-left:1px solid grey; border-right:1px solid grey;">
        	<div class="form-group">
				<label>제목</label>
				<p>${m_detail.title}</p>
			</div>
			<div class="form-group">
				<label>보낸 이</label>
				<p>${m_detail.sender}
			</div>
			<div class="form-group">
				<label>보낸 날짜</label>
				<p>${m_detail.send_date}
			</div>
			<div class="form-group">
				<label>내용</label>
				<p>${m_detail.content}
			</div>
			<div class="btn-group btn-group-sm" role="group" style="float:right;">
	          <button type="button" class="btn btn-default" id="box_authenticated1" onclick="location.href='/message_receive_delete/${m_detail.m_num}'">삭제</button>
	          <button type="button" class="btn btn-default" onclick="location.href='/want_council'"> 목록 </button>
	        </div>
    	</div>
	</div>
</body>
</html>
</layoutTag:layout>