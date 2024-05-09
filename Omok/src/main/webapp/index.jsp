<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Socket Example</title>
</head>
<body>
	<form>
		<input id="user" type="text" value="annonymous">
		<input id="textMessage" type="text">
		<input onclick="sendMessage()" value="Send" type="button">
		<input onclick="disconnect()" value="Disconnect" type="button">
	</form>
	<br>
	<textarea id="messageTextArea" rows="10" cols="50"></textarea>
	<script type="text/javascript">
		var webSocket = new WebSocket("ws://localhost:8090/Omok/broadsocket");
		
		var messageTextArea = document.getElementById("messageTextArea");
		
		webSocket.onopen = function(message) {
			messageTextArea.value += "Server connect \n";
		};
		
		webSocket.onclose = function(message) {
			messageTextArea.value += "Server disconnect \n";
		};
		
		webSocket.onerror = function(message) {
			messageTextArea.value += "error ! \n";
		};
		
		webSocket.onmessage = function(message) {
			messageTextArea.value += message.data + "\n";
		};
		
		function sendMessage() {
			var user = document.getElementById("user");
			var message = document.getElementById("textMessage");
			messageTextArea.value += user.value + "(me) => " + message.value + "\n";
			webSocket.send("{{" + user.value + "}}" + message.value);
			message.value = "";
		}
		
		function disconnect() {
			webSocket.close();
		}
		
		function getCookie(name) {
	        var value = "; " + document.cookie;
	        var parts = value.split("; " + name + "=");
	        if (parts.length == 2) return parts.pop().split(";").shift();
	    }

	    window.onload = function() {
	        // 쿠키에서 사용자 이름 읽기
	        var userName = getCookie("user");
	        if(userName) {
	            document.getElementById("user").value = decodeURIComponent(userName);
	        }
	    };
	</script>
</body>
</html>