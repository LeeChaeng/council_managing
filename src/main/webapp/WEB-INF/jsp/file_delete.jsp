<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

function fn_deleteFile(obj) {
	$(obj).parent().remove();
}
var gfv_count =0;
function fn_file_add(){
	console.log("파일 추가");
	gfv_count = gfv_count + 1;
	let str = "";
	str = "<div id='fileinfo'><input type='file' class='fileInput' id='"+gfv_count+"name='file["+gfv_count+"].gallery_img_multi><a onclick='javascript:fn_deleteFile(this)'>삭제</a></div>";
	$('#fileDiv').append(str);
	$("a[name='delete']").on("click",function(e) {
		e.preventDefault();
		fn_deleteFile($(this));
		});
}

function copy_to_clipboard(e) {
	copyToClipboard(e);
	console.log(e);
	alert("클립보드로 email값이 복사되었습니다!");
}

function copyToClipboard(val) {
	var t = document.createElement("textarea");
	  document.body.appendChild(t);
	  t.value = val;
	  t.select();
	  document.execCommand('copy');
	  document.body.removeChild(t);
}

$("document").ready(() => {
    $("#checkall").click(function() {
      //클릭되었으면
      if ($("#checkall").prop("checked")) {
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
        $("input[name=chk]").prop("checked", true);
        $(".bton").css("color", "black");
        //클릭이 안되있으면
      } else {
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
        $("input[name=chk]").prop("checked", false);
        $(".bton").css("color", "#afafaf");
      }
    });

    // name이 chk인 태그가 바뀔 때
    $("input[name=chk]").change(() => {
      //하나라도 체크되어있으면 true
      if ($("input:checkbox").is(":checked")) {
    	  $(".bton").css("color", "black");
      } else {
    	  $(".bton").css("color", "#afafaf");
          }
    });
  });

function mailS_delete () {
	var checkList = document.getElementsByName('chk');
	var mlist = '';
	for(let i=0;i<checkList.length;i++) {
		if(checkList[i].checked) {
			mlist += checkList[i].value +'-';
		}		
	}
	mlist = mlist.substring(0,mlist.length -1);
	
	$.ajax({
		url : '/mail/receiver_delete/'+mlist,
		type : 'get',
		success : function(){
			window.location.reload();
		}
		})
}

function mailR_delete () {
	var checkList = document.getElementsByName('chk');
	var mlist = '';
	for(let i=0;i<checkList.length;i++) {
		if(checkList[i].checked) {
			mlist += checkList[i].value +'-';
		}		
	}
	mlist = mlist.substring(0,mlist.length -1);


	$.ajax({
		url : '/mail/sender_delete/' +mlist,
		type : 'get',
		success : function() {
				window.location.reload();}
		})
}

</script>