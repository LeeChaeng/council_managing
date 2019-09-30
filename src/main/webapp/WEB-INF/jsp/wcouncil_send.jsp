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

.bton {
	background: none repeat scroll 0 0 #fcfcfc;
    border: 1px solid #e7e7e7;
    border-radius: 3px !important;
    color: #afafaf;
    display: inline-block;
    padding: 5px 10px;
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
                                                  <label class="col-lg-2 control-label">From</label>
                                                  <div class="col-lg-10">
                                                      ${loginInfo_email}
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">To</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" name="receiver" id="inputEmail1" class="form-control">
                                                  </div>
                                              </div>
                                              
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Subject</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" name="title" id="inputPassword1" class="form-control">
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
                                                        <span>첨부파일</span>
                                                        <div id="fileDiv">
                                                        	<input type="file" name="files_0">
                                                        </div>
                                                        <a id="add_file" onclick="fn_file_add();">파일 추가</a>
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
                	<a href="/want_council"><i class="fa fa-inbox"></i> 받은 편지함 </a>
                </li>
                <li class="inbox-divider">
                	<a href="/want_council/send" style="font-weight:600;"><i class="fa fa-envelope-o"></i> 보낸 편지함</a>
                </li>
            </ul>
        </aside>
        <div class="inbox-body">
        	<input type="checkbox" name="checkall" id="checkall">
	        <button class="bton" onclick="mailR_delete()">삭제</button>
	        <button class="bton">읽음</button>
        	<table class="table table-inbox table-hover">
            	<tbody>
                	<tr>
                    	<th style="width:50px;">체크</th>
                    	<th></th>
                    	<th style="width:180px;">받는 사람</th>
                        <th style="width:350px;">제목</th>
                        <th class="text-right">읽은 시간</th>
                        <th class="text-right">보낸 시간</th>
                    </tr>
                    <c:forEach var="item" items="${m_list}">
                    	<c:if test="${item.sender == loginInfo_email && item.sent_del==false}">
							<tr class=""  style="cursor:pointer">
								<td class="inbox-small-cells">
                                	<input type="checkbox" name="chk" class="mail-checkbox" value="${item.m_num}">
                                </td>
                                <td></td>
								<td onclick="javascript:copy_to_clipboard(`${item.sender}`)">${item.receiver}</td>
								<td onclick="location.href=`/message_send_detail/${item.m_num}`">${item.title}</td>
								<td>${item.read_date}</td>
								<td class="text-right">${item.send_date}</td>
							</tr>
						</c:if>
					</c:forEach>
                </tbody>
            </table>
            <div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li><a href="/want_council?page=${pageMaker.startPage-1}">이전</a></li>
							</c:if>
							
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li <c:out value="${pageMaker.cri.page == idx ? active : '' }"/>>
									<a href="/want_council?page=${idx}">${idx}</a>
								</li>
							</c:forEach>
							
							<c:if test="${pageMaker.next && pageMaker.endPage>0}">
								<li><a href="/want_council?page=${pageMaker.endPage+1}">다음</a></li>
							</c:if>
						</ul>
					</div>
				</div>
       </div>
	</div>
</body>
</html>
<%@ include file="file_delete.jsp" %>
</layoutTag:layout>