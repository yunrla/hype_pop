<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet">
<!-- CSS 파일 링크 -->
<style>

.container {
  position: fixed; /* 화면에 고정 */
  top: 50%; /* 화면 세로 중앙 */
  left: 50%; /* 화면 가로 중앙 */
  transform: translate(-50%, -50%); /* 정확한 중앙 정렬 */

}
body {
	font-family: Arial, sans-serif;
	background-color: #fCE4EC;
	margin: 0;
	padding: 20px;
}

.header {
	text-align: center;
	margin-bottom: 20px;
}

.header a {
	color: #007bff;
	text-decoration: none;
	font-size: 20px;
}

.container {
	max-width: 400px;
	margin: 0 auto;
	padding: 30px;
	background-color: #ffffff;
	border-radius: 5px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 20px;
}

label {
	display: block;
	margin: 10px 0 5px;
	color: #555;
}

input[type="text"], input[type="password"], input[type="email"] {
	width: calc(100% - 20px);
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
}

.checkbox-group {
	display: flex;
	align-items: center;
	margin-bottom: 15px;
}

.checkbox-group input {
	margin-right: 5px;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
	margin-bottom: 10px;
}

button:hover {
	background-color: #0056b3;
}

.link-group {
	display: flex;
	justify-content: space-around;
	padding: 10px;
}

.link-btn {
	background-color: transparent;
	border: none;
	color: #007bff;
	font-size: 16px;
	cursor: pointer;
	text-decoration: underline;
	padding: 5px 10px;
	transition: color 0.3s;
}

.link-btn:focus {
	outline: none;
}

.social-login {
	margin-top: 20px;
	text-align: center;
}

.social-login button {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	margin: 5px;
	background-color: #e0e0e0;
	color: #333;
	border: 1px solid #ccc;
	font-size: 20px;
}

.social-login button:hover {
	background-color: #d0d0d0;
}

/* 모달 기본 스타일 */
.modal {
  position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: none;
    z-index: 1000;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 90%;
    max-width: 400px; /* 모달의 최대 너비를 400px로 설정 */
    padding: 30px;
    text-align: center;
}

/* 모달 내용 */
.modal-content {
    background-color: white;
    padding: 20px;
    width: 400px; /* 가로 크기 */
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
        margin-bottom: 20px;
    
}

/* 모달 닫기 버튼 */
.close {
	position: absolute;
	top: 10px;
	right: 10px;
	font-size: 24px;
	font-weight: bold;
	color: #aaa;
	cursor: pointer;
	transition: color 0.3s ease;
}

.close:hover {
	color: #000;
}

/* 모달 내부의 폼 */
.modal-body .form-group {
	margin-bottom: 15px;
}


/* 모달의 제출 버튼 */
.modal-footer {
	text-align: center;
}

.modal-input {
	width: calc(100% - 20px);
	height: 40px;
	padding: 5px;
	font-size: 16px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.modal-footer button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.modal-footer button:hover {
	background-color: #0056b3;
}

#customAlert {
	position: fixed;
	top: 10%;
	left: 50%;
	transform: translate(-50%, -50%);
	display: none;
	z-index: 1000;
	background-color: rgba(255, 255, 255, 0.8); /* 흰색 배경에 불투명도 추가 */
	padding: 20px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 약간의 그림자 추가 */
	color: black; /* 글씨 색상 검정 */
}
/* Navbar styles */
.navbar {
	background-color: #ffffff;
	color: #333;
	padding: 15px 20px;
	border-bottom: 1px solid #e5e5e5;
	display: flex;
	justify-content: space-between;
}

.navbar ul {
	list-style: none;
	margin: 0;
	padding: 0;
	display: flex;
}

.navbar li {
	margin-right: 15px;
}

.navbar a {
	color: #333;
	text-decoration: none;
	font-weight: bold;
	font-size: 14px;
}

.navbar a:hover {
	color: #0078ff;
}

/* Header styles */
.purchase-header {
	text-align: center;
	margin: 20px 0;
	font-size: 24px;
	font-weight: bold;
	color: #333;
}

.navbar ul li a {
	line-height: 5; /* 텍스트 줄 간격을 늘려서 아래로 내려줌 */
}


</style>
</head>
<body>

	
<%-- 	<jsp:include page="layout/popUpHeader.jsp" />--%>   
	<nav class="navbar">
		<ul>
			<li><img src="/resources/images/mainLogo.png" alt="메인 로고"
				id="mainLogo"></li>
			<li><a href="/hypePop/popUpMain">홈으로</a></li>
			

		</ul>
	</nav>
	<div class="container">
		<h1>로그인</h1>
		<form action="login" method="post">
			<label for="username">아이디</label> <input type="text" id="userId"
				name="userId" required placeholder="아이디를 입력하세요."> <label
				for="password">비밀번호</label> <input type="password" id="userPw"
				name="userPw" required placeholder="비밀번호를 입력하세요.">



			<button type="submit" class="btn btn-sec" id="loginBtn">로그인</button>

		</form>

		<div class="link-group">
			<button id="searchIdBtn" class="link-btn">아이디 찾기</button>

			<button id="searchPwBtn" class="link-btn">비밀번호 찾기</button>
			<button onclick="location.href='join'" class="link-btn">회원가입</button>
		</div>
		<div id="customAlert">인증코드를 전송 중입니다.</div>


		

	<!-- 아이디 찾기 모달 -->
	<div id="searchIdModal" style="display: none;">
		<div class="modal-content">
			<!-- X 버튼 추가 -->
			<span class="close" onclick="closeSearchIdModal()">&times;</span>
			<form id="searchIdModal">
				<div class="modal-body">
					<!--이름 입력 -->
					<div class="form-group form-group-inline">
						<input type="text" class="modal-input" id="userName"
							placeholder="이름 입력">
					</div>

					<!-- 이메일 전송 필드 -->
					<div class="form-group form-group-inline">
						<input type="email" class="modal-input" id="userEmail"
							placeholder="이메일 입력">

						<button type="button" id="sendEmailBtn" class="btn btn-sec">이메일
							전송</button>
					</div>

					<!-- 코드 입력 및 확인 버튼 -->
					<div class="form-group form-group-inline">
						<input type="number" class="modal-input" name="verifyCode"
							id="verifyCodeInput" placeholder="코드 입력" required>
						<button type="button" id="sendEmailCode" class="btn btn-sec"
							onclick="verifyEmailCode()">코드 확인</button>
					</div>

					<!-- 아이디 확인하기 버튼 추가 -->
					<div class="form-group">
						<button type="button" id="checkMyId" class="btn btn-sec">아이디
							확인</button>
					</div>
				</div>

			</form>
		</div>
	</div>


	<!-- 비밀번호 찾기 모달 -->
	<div id="foundUserPwModal" style="display: none;">
		<div class="modal-content">
			<!-- X 버튼 추가 -->
			<span class="close" onclick="closeSearchPwModal()">&times;</span>
			<form action="/member/goPwChange" method="get"
				id="passwordChangeForm" onsubmit="return submitPwChange()">
				<div class="modal-body">
					<div class="form-group">
						<p>
							<input type="text" class="modal-input" name="userId"
								id="userIdSearchPw" placeholder="아이디 입력" required>
						</p>
						<button type="button" id="confirmId" class="btn btn-sec">아이디
							확인</button>
					</div>
					<div class="form-group form-group-inline">
						<input type="email" class="modal-input" id="userEmailPw"
							placeholder="이메일 입력">

						<button type="button" id="sendEmailBtnPw" class="btn btn-sec">이메일
							전송</button>
					</div>

					<!-- 코드 입력 및 확인 버튼 -->
					<div class="form-group form-group-inline">
						<input type="number" class="modal-input" name="verifyCodeInputPw"
							id="verifyCodeInputPw" placeholder="코드 입력" required>
						<button type="button" id="verifyCodeInputPw" class="btn btn-sec"
							onclick="verifyEmailCodePw()">코드 확인</button>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" 
						class="btn btn-sec">비밀번호
						변경하기</button>
				</div>


			</form>
		</div>
	</div>
	</div>

	<jsp:include page="layout/popUpNavBar.jsp" />

	<script type="text/javascript" src="/resources/memberJs/login.js"></script>
	<script src="https://accounts.google.com/gsi/client" async defer></script>

</body>
</html>
