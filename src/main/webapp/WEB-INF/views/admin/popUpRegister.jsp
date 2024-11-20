<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
    font-family: Arial, sans-serif; /* 기본 글꼴 */
    background-color: #f8f9fa; /* 배경색 */
    margin: 0; /* 기본 마진 제거 */
    padding: 20px; /* 내부 여백 */
}

#AllList {
    margin-bottom: 20px; /* 하단 여백 */
}

form {
    background-color: white; /* 폼 배경색 */
    padding: 20px; /* 폼 내부 여백 */
    border-radius: 5px; /* 모서리 둥글게 */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
    margin-bottom: 20px; /* 하단 여백 */
}

div {
    margin-bottom: 15px; /* 각 요소 간 여백 */
}

input[type="text"], select {
    width: 100%; /* 전체 너비 */
    padding: 10px; /* 내부 여백 */
    border: 1px solid #ced4da; /* 테두리 */
    border-radius: 5px; /* 모서리 둥글게 */
    box-sizing: border-box; /* 패딩과 테두리 포함 */
}

input[type="date"] {
	padding: 10px; /* 내부 여백 */
    border: 1px solid #ced4da; /* 테두리 */
    border-radius: 5px; /* 모서리 둥글게 */
    box-sizing: border-box; /* 패딩과 테두리 포함 */
}

#adminSearchBox {
    width: auto; /* 자동 너비 조정 */
    margin: 20px 10px 10px; /* 위쪽 여백 추가 */
    padding: 8px; /* 입력 상자 내부 여백 */
    border: 2px solid #dc3545; /* 테두리 색상 */
    border-radius: 5px; /* 모서리 둥글게 */
    height: 36px; /* 높이 지정 */
    box-sizing: border-box; /* 패딩과 테두리 포함 */
}

#popUpimg {
    display: inline-block;
    padding: 10px 15px; /* 내부 여백 */
    background-color: #007bff; /* 버튼 색상 */
    color: white; /* 글자색 흰색 */
    border-radius: 5px; /* 모서리 둥글게 */
    cursor: pointer; /* 커서 변경 */
    text-align: center; /* 중앙 정렬 */
}

#uploadedImages {
    margin-top: 5px; /* 상단 여백 */
    min-height: 70px; /* 최소 높이 */    
    padding: 10px; /* 내부 여백 */
}

button {
    padding: 10px 15px; /* 내부 여백 */
    background-color: #dc3545; /* 버튼 색상 */
    color: white; /* 글자색 흰색 */
    border: none; /* 테두리 없음 */
    border-radius: 5px; /* 모서리 둥글게 */
    cursor: pointer; /* 커서 변경 */
}

button:hover {
    background-color: #c82333; /* 버튼 호버 색상 */
}

#pagination {
    text-align: center; /* 중앙 정렬 */
    margin-top: 20px; /* 상단 여백 */
}
</style>
</head>
<body>
	<jsp:include page="layout/adminHeader.jsp"/>
	
	<div id="AllList"></div>
	
	<form id="psRegisterForm" method="POST" action="/admin/psRegister" enctype="multipart/form-data">
	    <div id="popUpimg" style="cursor: pointer;">팝업스토어 이미지</div>
	    <input type="file" id="imageFile" name="imageFile" style="display: none;">
	    <div id="uploadedImages"></div>
	    
	    <div id="psLatitude">위도 <input type="text" name="latitude"></div>
	    <div id="psLongitude">경도 <input type="text" name="longitude"></div>
	    
	    <div id="storeName">팝업스토어 이름 <input type="text" name="psName"></div>
	    <div id="cats">팝업스토어 카테고리
          <div>
             <input type="checkbox" name="psCat.healthBeauty" value="1">헬스/뷰티
             <input type="checkbox" name="psCat.game" value="1">게임
             <input type="checkbox" name="psCat.culture" value="1">문화
             <input type="checkbox" name="psCat.shopping" value="1">쇼핑
             <input type="checkbox" name="psCat.supply" value="1">문구
             <input type="checkbox" name="psCat.kids" value="1">키즈
             <input type="checkbox" name="psCat.design" value="1">디자인
             <input type="checkbox" name="psCat.foods" value="1">식품
             <input type="checkbox" name="psCat.interior" value="1">인테리어
             <input type="checkbox" name="psCat.policy" value="1">정책
             <input type="checkbox" name="psCat.character" value="1">캐릭터
             <input type="checkbox" name="psCat.experience" value="1">체험
             <input type="checkbox" name="psCat.collaboration" value="1">콜라보
             <input type="checkbox" name="psCat.entertainment" value="1">방송
          </div>
      	</div>
	    <div id="startDate">시작일 <br><input type="date" name="psStartDate"></div>
	    <div id="endDate">종료일 <br><input type="date" name="psEndDate"></div>
	    
	    <div id="address">주소 <input type="text" name="psAddress" ></div>
	    <div id="snsAddress">SNS 주소 <input type="text" name="snsAd"></div>
	    <div id="company">주최사 정보 <input type="text" name="comInfo"></div>
	    <div id="transfer">교통편 <input type="text" name="transInfo"></div>
	    <div id="parking">주차장 정보 <input type="text" name="parkingInfo"></div>
	    <div id="storeExp">설명글 <input type="text" name="psExp"></div>
	    
        <button type="button" id="psRegisterBtn" onclick="popStoreRegister();">등록하기</button>
	</form>	
	
	<div id="pagination"></div>
	
<script type="text/javascript" src="/resources/adminJs/admin.js"></script>  
<script type="text/javascript" src="/resources/adminJs/adminPopUp.js"></script>  

</body>
</html>