<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
var bno ='${detail.bno}'

$('[name=commentInsertBtn]').click(function() {
	var insertData = $('[name=commentInsertForm]').serialize();
	commentInsert(insertData);
})

//댓글 목록
function commentList() {
	$.ajax({
		url : '/comment/list',
		type : 'get',
		data : {'bno' : bno} ,
		success : function(data) {
			var a = '';
			$.each(data, function(key, value) {
				a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+value.cno+'">'+'작성자 : '+value.writer + ' 등록일 : '+ value.reg_date;
                if(`${loginInfo_name}`==value.writer){
                		a += '<a onclick="commentUpdate('+value.cno+',\''+value.content+'\');" style="cursor:pointer;"> 수정 </a>';
                        a += '<a onclick="commentDelete('+value.cno+');" style="cursor:pointer;"> 삭제 </a>';
                }
                a += ' </div><div class="commentContent'+value.cno+'"> <p> 내용 : '+value.content +'</p>';
                a += '</div></div>';
                });
            $(".commentList").html(a);
		}
	});
}


//댓글 등록
function commentInsert(insertData) {
	$.ajax({
		url : '/comment/insert',
		type : 'post',
		data : insertData,
		success : function(data) {
			if(data == 1) {
				commentList();
				$('[name=content]').val('');
			}
		}
	});
}


//댓글 수정 - 댓글 입력 내용 출력을 input 폼으로 변경
function commentUpdate(cno, content) {
	var a ='';

	a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+cno+');">수정</button> </span>';
    a += '</div>';

    $('.commentContent'+cno).html(a);
}

//댓글 수정
function commentUpdateProc(cno) {
	var updateContent = $('[name=content_'+cno+']').val();

	$.ajax({
		url : '/comment/update',
		type : 'post',
		data : {'content' : updateContent, 'cno' : cno},
		success : function(data) {
			if(data == 1) commentList(bno);
		}
	});
}

//댓글 삭제
function commentDelete(cno) {
	$.ajax({
		url : '/comment/delete/'+cno,
		type : 'post',
		success : function(data){
			if(data == 1) commentList(bno); //댓글 삭제 후 목록 출력
		}
	});
}

$(document).ready(function(){
	commentList();
})
</script>