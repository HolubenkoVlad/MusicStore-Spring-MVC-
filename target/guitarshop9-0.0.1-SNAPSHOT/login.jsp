<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="room_styles.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function call() {
	  var loginForm   = $('#form').serialize();
            $.ajax({
            	type: "POST",
                url: '<c:url value="/login.do"></c:url>',
                data: loginForm,
                success:function(msg){
                	if (msg.status) {
                		window.location.href = '<c:url value="/'+msg.message+'.jsp"></c:url>';           	    
                	} else {
                		alert(msg.message);
                	}
                }
            });
}
    </script>
<title>Вход</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="content">
<div id="access">
<form action="javascript:void(null);" method="post" id="form" name="test" onsubmit="call()">
	<h2><p id="namesign">Вход</p></h2>
	<br><p>Введите логин:  <input name="login" type="text" id="name"></p>
	<br><p>Введите пароль: <input name="password" type="password" id="password"></p>
	<br><input id="but" type="submit" name="submit "value="Войти">
</form>
<a id="regbutt" href="<c:url value="/register.do"/>">Зарегистрироваться</a></div>	
</div>


</body>
</html>