<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title>방 생성하기</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f7fc;
            display: flex; 
            justify-content: center; 
            align-items: center; 
            height:100vh;
        }

        .container {
            width: 60%;
            padding: 30px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            font-size: 25px;
            margin-right: 10px; 
        }

        input[type="text"], input[type="password"], select {
            width: 60%;
            max-width: 300px;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        
        .button-container {
        	display: flex; 
        	justify-content: center; 
        	align-items: center; 
        	margin-top: 20px; 
        }

        input[type="submit"], input[type="button"] {
        	width: 140px;
            background-color: #007bff;
            color: #fff;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size : 16px; 
            transition: background-color 0.3s; 
            margin-top: 0 10px;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #0056b3;
        }
  </style>
</head>
<body>
 <form action="/Omok/GamePage/Create.jsp" method="post">
        <label for="gamename">방 제목:</label>
        <input type="text" id="gamename" name="gamename" required><br><br>

       
        <input type="submit" value="확인">
        <input type="button" value="취소" onclick="history.back()">
    </form>
</body>
</html>