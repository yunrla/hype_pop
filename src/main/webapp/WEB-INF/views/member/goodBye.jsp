<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
 <title>ȸ�� Ż�� �Ϸ�</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Nunito', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            text-align: center;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }

        h1 {
            font-size: 36px;
            color: #333;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            color: #666;
            margin-bottom: 30px;
        }

        .button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            padding: 12px 30px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #45a049;
        }

        .button:active {
            background-color: #388e3c;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>ȸ�� Ż�� �Ϸ�</h1>
        <p>ȸ�� Ż�� ���������� ó���Ǿ����ϴ�. �� ���� �̿��� �ּż� �����մϴ�.</p>
        <a href="/" class="button">Ȩ���� ���ư���</a>
    </div>

    <script>
        // �������� �ε�Ǹ� 3�� �� Ȩ���� �̵�
        setTimeout(() => {
            window.location.href = '/';  // Ȩ �������� �̵�
        }, 3000); // 3�� ��
    </script>
    
</body>
</html>