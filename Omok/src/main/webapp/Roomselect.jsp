<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title> 방 고르기 </title>
</head>
<body>
<form action="/Omok/GamePage/call.do" method="post">
	<table> 
		<tr>
			<td> 방 이름 </td>
			<td> 선택 </td>
		</tr>
		<c:forEach var = "game" items = "${gameList }">
		 	<tr> 
		 		<td> ${game.gamename }</td>
		 		<td> <input type = "submit" value = "입장하기"> </td>
		 	</tr>
		 </c:forEach>
		<a href = "/Main.jsp"> 메인 페이지로 돌아가기 </a>
	</table>
</form>
</body>
</html>