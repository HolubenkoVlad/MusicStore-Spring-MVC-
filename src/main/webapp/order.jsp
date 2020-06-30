<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="order.css" rel="stylesheet" type="text/css" />
<title>Накладная</title>
</head>
<body>
<div class="mainblock">
	<h3 id="caption"> Заказ №${orderob.getId()}</h3>
<table class="tab" border="1px solid black" cellpadding="10px">
<tr class="headers">
	<td>N</td>
    <td>id</td>
    <td>Название</td>
    <td>Цена, грн</td>
    <td>Количество</td>
</tr>
<c:forEach var="cart" items="${orderob.getList()}" varStatus="status">
    <tr>
    	<td>${status.index+1}</td>
    	<td>${cart.getItem().getId()}</td>
  		<td>${cart.getItem().getName()}</td>
  		<td>${cart.getTotalprice()}</td>
  		<td>${cart.getCount()}</td>
    </tr>
</c:forEach>
</table>
<p id="totalprice">Всего: ${totalprice} грн.</p>
<div class="orderinfo">
    	<p>	Карта: ${orderob.getCard()}</p>
       <p>	Адрес: ${orderob.getAddress()}</p>
</div>   
<a href="<c:url value="/list.do"/>">Назад в каталог</a>
</div>
</body>
</html>