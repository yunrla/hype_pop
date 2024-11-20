<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Insert title here</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .navBar {
            display: flex;
            justify-content: center;
            background-color: #333;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        .navBar a {
            color: white;
            text-decoration: none;
            padding: 14px 20px;
            text-align: center;
        }
        .navBar a:hover {
            background-color: #575757;
            transition: background-color 0.3s;
        }
    </style>
</head>
<body>

    <div class="navBar">
        <a href="/hypePop/search/noData">�˾� ����� �˻�</a>
        <a href="/goodsStore/goodsSearch">���� �˻�</a>
        <a href="/hypePop/popUpMain#MapAPI">�� �ֺ�</a>
        <a href="/hypePop/calendar">Ķ����</a>
        <a href="/member/login">�α���</a>
        <a href="#" onclick="logout()" >�α׾ƿ�</a>
        <a href="/member/myPage?userNo=2">����������</a>
        
        
    <!--     �͸� ������� ���(�α����� ���� ���� ��쵵 �ش�) 
      <sec:authorize access="isAnonymous()">
         <a href="member/login">�α���</a>
      </sec:authorize>
      ������ ����� 
      <sec:authorize access="isAuthenticated()">
         <a href="member/myPage">����������</a>
      </sec:authorize> -->
        
        
        
        
    </div>
    

</body>
<script type="text/javascript">
    function logout(){
       // localStorage���� userNo ����
       localStorage.removeItem('userNo');
       
    // ������ ���ε�
       location.reload();

    }
</script>
</html>