<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <style>
        /* 기본적인 스타일 정의 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .navbar {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }
        .header, .footer {
            text-align: center;
            padding: 10px;
        }
        .cart-list {
            max-height: 400px;  /* 장바구니 항목의 최대 높이 설정 */
            overflow-y: scroll; /* 세로 스크롤 활성화 */
            border: 1px solid #ddd; /* 경계선 추가 */
            margin-bottom: 20px; /* 항목과 총 가격 사이 간격 */
        }
        .cart-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        .cart-item img {
            width: 80px;
            height: 80px;
            object-fit: cover;
        }
        .cart-info {
            flex: 1;
            padding-left: 20px;
        }
        .cart-total, .checkout-button {
            text-align: right;
            padding: 20px;
        }
        .checkout-button button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .checkout-button button:hover {
            background-color: #45a049;
        }
        .delete-button {
            color: red;
            cursor: pointer;
        }
        
        /* 스크롤바 스타일 설정 */
.cart-list::-webkit-scrollbar {
    height: 1px; /* 스크롤바 너비 */
    
}

.cart-list::-webkit-scrollbar-thumb {
    background-color: #888; /* 스크롤바의 색상 */
}

.cart-list::-webkit-scrollbar-thumb:hover {
    background-color: #555; /* 스크롤바가 호버될 때의 색상 */
}
        
       .navbar {
	background-color: #ff66b2; /* 진한 핑크 */
	color: white;
	padding: 10px;
}

.navbar ul {
	list-style-type: none;
	padding: 0;
}

.navbar li {
	display: inline;
	margin-right: 15px;
}

.navbar a {
	color: white;
	text-decoration: none;
	font-weight: bold;
}

.navbar a:hover {
	color: #ffe6f0; /* 연한 핑크로 변경 */
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
.footer {
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    padding: 20px;
    font-size: 14px;
    color: #666;
    background-color: #ffffff;
    border-top: 1px solid #e5e5e5;
} 
    </style>
</head>
<body onload="calculateTotal()">

	      <nav class="navbar">
         <ul>
          <li>
            <img src="/resources/images/mainLogo.png" alt="메인 로고" id="mainLogo">
        </li>
             <li><a href="/hypePop/popUpMain">홈으로</a></li>
            <li><a href="/goodsStore/goodsSearch">굿즈스토어</a></li>
            <li><a href="/member/myPage?userNo=2">마이페이지</a></li>
         
         </ul>
      </nav>
<!-- 장바구니 헤더 -->
<div class="container">
    <div class="header">
        <h2>장바구니</h2>
    </div>
    
      <!-- 전체 선택 체크박스 -->
    <div>
        <input type="checkbox" id="selectAll" onclick="toggleAll(this)" checked> 전체 선택/해제
    </div>

    <!-- 장바구니 목록을 감싸는 div -->
    <div class="cart-list">
        <!-- 장바구니 목록 -->
        
   
        <c:forEach var="cart" items="${cartInfo}">
            <div class="cart-item" id="cart-${cart.gno}" >
                <input type="checkbox" class="cart-checkbox" id="cart-${cart.gno}" checked onchange="calculateTotal()">
                <c:forEach var="img" items="${cart.gimg}">
                    <img src="${img.uploadPath}/${img.uuid}_${img.fileName}" alt="${cart.gname}" width="100" height="100" />
                </c:forEach>
                <div class="cart-info">
                    <h4>굿즈 이름 : ${cart.gname}</h4>
                    <p>가격: ₩<span class="price">${cart.gprice}</span></p> <!-- 가격에 class 추가 -->
                    <button onclick="changeAmount(${cart.gno}, -1)">-</button>
                    <input type="number" id="quantity-${cart.gno}" value="${cart.camount}" min="1" readonly>
                    <button onclick="changeAmount(${cart.gno}, +1)">+</button>
                    <p>상품 목록 1개의 총 가격: ₩<span class="total-price">${cart.camount * cart.gprice}</span></p> <!-- 총 가격에 class 추가 -->
                </div>
                <span class="delete-button" onclick="deleteItem(${cart.gno})">X</span>
            </div>
        </c:forEach>
    </div>
</div>

 
   <!-- 총 가격 표시 -->
   <div class="cart-total">
       총 가격: ₩<span id="grandTotal">0</span>
   </div>

    <!-- 결제하기 버튼 -->
   <!--  <div class="checkout-button">
        <button  class="goPayInfo-button" onclick="goPayInfo()">결제하기</button>
    </div> -->
	
	
	<div class="checkout-button">
    <form id="cartForm" method="get" action="getPayInfo">
        <input type="hidden" name="grandTotal" id="hiddenGrandTotal" value="0">
        <input type="hidden" name="cartData" id="hiddenCartData"> <!-- 장바구니 데이터 -->
        <input type="hidden" value="2" name="userNo" id="userNo"> 
        
        <button type="button" class="goPayInfo-button" onclick="prepareCartData()">결제하기</button>
    </form>
	</div>


<!-- Footer -->
<footer class="footer">
        <p>© 2024 hypePop.</p>
    </footer>
   <script type="text/javascript" src="/resources/purchaseJs/myCart.js"></script>

</body>
</html>
