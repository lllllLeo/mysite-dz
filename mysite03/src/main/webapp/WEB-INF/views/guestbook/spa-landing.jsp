<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

/*  
 * 과제 ex1 : 리스트 (list)
  - no 기준의 리스트를 부분적(3개씩) 가져와서 리스트 랜더링 (밑에) append
  - 참고 : frontend-dev > ch08 > Guestbook
  - no 기준으로 동적 쿼리를 레포지토리에 구현한다
  
  	과제 ex2 : 메시지 등록(add)
  - validation
  - message 기반 dialog plugin 사용법
  - form submit 막기 preventevent
  - 추가한 데이터 랜더링  prepend
  
    과제 ex3 : 
  - a 태그 기본동작 막기
  - live event -> a태그
  - form기반 비밀번호 받는 다이얼로그(validation) dialog plugin사용법
  - 응답에 대해 해당 li 삭제
  - 비밀번호가 틀린 경우 (삭제실패, no=0) 사용자한테 알려주는 UI 처리
  - 비밀번호가 맞은 경우 (no > 0), data-no = 10 인 li element를 삭제
  - 참고 ex03
 */
 var render = function(vo){
		html = "<li data-no='"+ vo.no +"'>"+
					"<strong>" + vo.name + "</strong>" +
					"<p>"+ vo.message + "<br></p>" +
					"<strong></strong>" +
					"<a href='' data-no='"+ vo.no +"'>삭제</a>" + 
				"</li>";
		return html;
	};
	
 var fetch = function(no) {
		let data = "no=" + no
		$.ajax({
			url: "${pageContext.request.contextPath }/guestbook/api/list",
			dataType: "json",	
			data: data,
			type: "get", 		
			contentType: "application/json",	 
			success: function(response){
				response.data.forEach(function(vo) {
					// 랜더링을 함수로 만들기
					render(vo);
					$("#list-guestbook").append(html);
				});
			}
		});
	};
	
var addGuestbook = function(){
	console.log("조지기");
	 
	vo = {}
	vo.name = $("#input-name").val();
	if(vo.name == "") {
		messageBox('오류', '이름이 비었습니다.');
		return;
	}
	
	vo.password = $("#input-password").val();
	if(vo.password == "") {
		messageBox('오류', '비밀번호를 입력해주세요.');
		return;
	}
	
	vo.message =  $("#tx-content").val()
	if(vo.message == "") {
		messageBox('오류', '내용이 비었습니다.');
		return;
	}
	
	$.ajax({
		url: "${pageContext.request.contextPath }/guestbook/api/add",
		dataType: "json",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(vo),
		success: function(response) {
			console.log(response);
			var vo = response.data;
			render(vo);
			$("#list-guestbook").prepend(html);
			
			$("#input-name").val("");		// 값 초기화 
			$("#input-password").val("");	
			$("#tx-content").val("");
		}
	});
};


var messageBox = function(title, message) {
	$("#dialog-message").dialog({
		title: title,
		modal: true,
		buttons: {
			"닫기": function(){
				$(this).dialog("close");
			}
		}
	});
	$("#dialog-message p").text(message);
};


$(function(){
	 $("#add-form").submit(function(event){
		 event.preventDefault();
		 addGuestbook();
	 });
	 
	 $("#btn-fetch").click(function(event){
		 // event.preventDefault();  알아보기
		 console.log('똥글가져오기') 
		 let no = $("#list-guestbook li:last-child").attr("data-no");
		 fetch(no);
	 });
	 
	 $(document).on("click", "#list-guestbook li a", function(event){
		 event.preventDefault();
		 let no = $(this).data("no");
		 $("#hidden-no").val(no);
		 console.log($("#hidden-no").val());
		 deleteDialog.dialog("open");
	 });
	 
	 const deleteDialog = $("#dialog-delete-form").dialog({
		autoOpen: false,	
		width: 330,
		height: 270,
		modal: true,
		buttons: {
			"삭제": function(){
				const no = $("#hidden-no").val();
				const password = "password=" + $("#password-delete").val();
				console.log(password);
				console.log(no);
				$.ajax({
					url: "${pageContext.request.contextPath }/guestbook/api/delete/" + no,
					dataType: "json",
					type: "post",
					data: password,
					success: function(response){
						console.log(response); // 밑에 에러처리
						if(response.data == 'false'){
							// 비밀번호가 틀린경우
							$(".validateTips.error").show();
							return;
						}
						
						$("#list-guestbook li[data-no="+ response.data +"]").remove();
						console.log(response.data);
						deleteDialog.dialog("close");
						
/* 						response.data.forEach(function(vo) {
							// 랜더링을 함수로 만들기
							render(vo);
							$("#list-guestbook").append(html);
						}); */
					}
				});
			},
			"닫기": function(){
				$(this).dialog("close");
			}
		},
		close: function(){
			// 1. password 비우기
			// 2. no 비우기
			// 3. error message 숨기기
			
			console.log('다이알로그 폼 데이터 정리');
			$("#password-delete").val("");
			$("#hidden-no").val("");
			$(".validateTips.error").hide(); // 조건문으로?
			
		}
 	 });
	 
	 fetch(0); // 초기 데이터 셋팅
 });
	 
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름"> 
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="조지기" />
				</form>



				<ul id="list-guestbook">

				</ul>

				<div style="margin: 20px 0 0 0">
					<button id="btn-fetch">똥글 더 가져오기</button>
				</div>

			</div>
			
			
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>
				<form>
					<input type="password" name="password" id="password-delete"
						value="" class="text ui-widget-content ui-corner-all"> <input
						type="hidden" id="hidden-no" value=""> <input
						type="submit" tabindex="-1"
						style="position: absolute; top: -1000px">
				</form>
			</div>
			
			
			<div id="dialog-message" title="" style="display: none">
				<p></p>
			</div>
			
			
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>