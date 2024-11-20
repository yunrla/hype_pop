<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
/* �⺻ ��Ÿ�� �ʱ�ȭ */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f7fc;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	color: #333;
}

.container {
	width: 100%;
	max-width: 500px; /* �ִ� �ʺ� ���� */
	background-color: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	text-align: center;
}

h1 {
	font-size: 24px;
	margin-bottom: 20px;
	color: #2c3e50;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	font-size: 14px;
	margin-bottom: 5px;
	color: #7f8c8d;
}

.input-field {
	width: 100%;
	padding: 10px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 5px;
	outline: none;
}

.input-field:focus {
	border-color: #3498db;
}

.form-footer {
	margin-top: 20px;
}

.btn {
	width: 100%;
	padding: 12px;
	font-size: 16px;
	color: #fff;
	background-color: #3498db;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn:hover {
	background-color: #2980b9;
}

.alert {
	color: red;
	margin-top: 10px;
}
s
</style>
</head>
<body>
	<div class="container">
		<h1>��й�ȣ �����ϱ�</h1>
		<button type="button" id="goHomeBtn" class="btn" style="display: none;" onclick="location.href='/'">Ȩ���� ����</button>
		

		<form action="pwChange?userNo=2" method="post" id="passwordChangeForm"
			onsubmit="return submitPwChange()">
			<input type="hidden" value="2" name="userNo" id="userNo">

			<div class="form-group">
				<label for="oldPw"></label> <input type="password" id="oldPw"
					class="input-field" name="oldPw" placeholder="���� ��й�ȣ �Է�" required>
			</div>

			<div class="form-group">
				<label for="newPw"></label> <input type="password" id="newPw"
					class="input-field" name="newPw" placeholder="�ű� ��й�ȣ �Է�" required>
			</div>

			<div class="form-group">
				<label for="checkNewPw"></label> <input type="password"
					id="checkNewPw" class="input-field" name="checkNewPw"
					placeholder="�ű� ��й�ȣ Ȯ��" required>
			</div>

			<div class="form-footer">
				<button type="submit" class="btn">��й�ȣ ����</button>
			</div>
		</form>
	</div>

	<c:if test="${not empty msg}">
		<script>
            alert("${msg}");
        </script>
	</c:if>

	<script type="text/javascript" src="/resources/memberJs/login.js"></script>

</body>
</html>