<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="room_styles.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
<script src="jQuery.serializeObject-2.0.3/jquery.serializeObject.js"></script> 
<script>

$(document).ready(function() {
	PopUpHide();
	hideEdit();
	hideAdd();
});

function PopUpShow(){
	//$("#popup1").show();
	$("#popup1").css("display","block");
	}

function PopUpHide(){
	//$("#popup1").hide();
	$("#popup1").css("display","none");
	}

function call() {
	  var order  = $('#formorder').serializeObject();
            $.ajax({
            	type: "POST",
            	contentType: 'application/json; charset=utf-8',
                url: '<c:url value="/addorder.do"></c:url>',
                data: JSON.stringify(order),
                dataType: 'json',
                success:function(msg){
                	if (msg.status) {
                		$.ajax({
                        	type: "GET",
                            url: '<c:url value="/order.do"></c:url>',
                            data: msg.order
                        });
                		window.location.href = '<c:url value="/'+msg.message+'.jsp"></c:url>';           	    
                	} else {
                		alert(msg.message);
                	}
                }
            });
}

function showTable() {
	$('#divtable').empty();
          $.ajax({
          	type: "GET",
              url: '<c:url value="/api/items.do"></c:url>',
              dataType: 'json',
              async: true,
              success:function(listitems){
            	  var content = "<table id=\"itemstable\" border=\"1\" cellpadding=\"10px\" margin-bottom=\"10px\">";
            	  content+="<td>id</td><td>Название</td><td>Описание</td><td>Цена, грн</td><td>Фото</td><td></td>"
            	  $.each(listitems, function(index,item){
        			  content+='<tr>';
        		      content += '<td id="id">' +  item.id + '</td>'+'<td id="name">' +  item.name + '</td>';
        		      content += '<td id="feature">' +  item.feature + '</td>'+'<td id="price">' +  item.price + '</td>'+'<td id="photo">' +  item.photo + '</td>'
        		      +'<td><button onclick="showEdit('+item.id+')">Edit</button></td>';
        		      content+='</tr>';
            	  });
            		  content += "</table>"
            		  content+="<a id=\"erase\" onclick=\"hideTable()\" >Скрыть таблицу</a>"
            		  $('#divtable').append(content);
              }
          });
}

function showEdit(id){
	$.ajax({
    	type: "GET",
        url: '<c:url value="/api/items/'+id+'.do"></c:url>',
        data: id,
        success:function(item){
        	$("#popup2").css("display","block");
        	$("#formEdit :input[name='id']").val(item.id);
        	$("#formEdit :input[name='name']").val(item.name);
        	$("#formEdit :input[name='feature']").val(item.feature);
        	$("#formEdit :input[name='price']").val(item.price);
        	$("#formEdit :input[name='photo']").val(item.photo);
        },
        error:function(){
        	alert("Ошибка ID");
        }
    });
	
}

function hideEdit(){
	//$("#popup2").hide();
	$("#popup2").css("display","none");
}

function showAdd(item){
	//$("#popup3").show();
	$("#popup3").css("display","block");	
}

function hideAdd(){
	//$("#popup3").hide();
	$("#popup3").css("display","none");
}

function updateItem(){
	 var item  = $('#formEdit').serializeObject();
     $.ajax({
    	 type: 'PUT',
    	 contentType: 'application/json; charset=utf-8',
         url:  '<c:url value="/api/items/item.do"></c:url>',
         data: JSON.stringify(item),
         dataType: 'json',
         async: true,
         success: function(msg){
        	 showTable();
        	 alert(msg.message);},
         error:function(){alert("Ошибка обновления")}
     });
}

function addItem(){
	 var item  = $('#formAdd').serializeObject();
    $.ajax({
   	    type: 'POST',
   	 	contentType: 'application/json; charset=utf-8',
        url:  '<c:url value="/api/items.do"></c:url>',
        data: JSON.stringify(item),
        dataType: 'json',
        async: true,
        success: function(msg){
        	showTable();
        	alert(msg.message);},
        error:function(){alert("Ошибка добавления")}
    });
}

function hideTable(){
	$('#divtable').empty();
}
</script>
<title>Личный кабинет</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
<div id="welcome">
  <form action="<c:url value="/logout.do"/>" method="post"><input id="logout" type="submit" value="Выйти"></form>
  <c:choose>
  <c:when test="${login.getSurname() eq 'admin'}">
  <div id="divbutt">
  <button class="adminbutton" onclick="showAdd()">Добавить товар</button>
  <button class="adminbutton" onclick="showTable()">Показать список товаров</button>
  </div>
  <div id="divtable"></div>
  </c:when>
  <c:otherwise>
<h2>Добро пожаловать, <span > ${login.getSurname()} </span></h2>
<c:choose>
<c:when test="${Cart.getCount() eq 0 || empty Cart}">
       <h3 id="emptycart">Корзина пустая</h3>
     </c:when>
    <c:otherwise>
    <h3 id="casth">Корзина</h3>
       <table id="casttable" border="1" cellpadding="10px">
<tr class="headers">
	<td>N</td>
    <td>id</td>
    <td>Название</td>
    <td>Характеристика</td>
    <td>Цена, грн</td>
    <td>Количество</td>
      <td></td>
</tr>
<c:forEach var="cart" items="${Cart.getItems()}" varStatus="status">
    <tr>
    	<td>${status.index+1}</td>
    	<td>${cart.getItem().getId()}</td>
  		<td>${cart.getItem().getName()}</td>
  		<td>${cart.getItem().getFeature()}</td>
  		<td>${cart.getTotalprice()}</td>
  		<td><input size="2" id="quen" value="${cart.getCount()}" type="number" onchange="<c:url value="/ChangeQuentity?nomer=${status.index}&quentity=${cart.getCount()}"/>">.</td>
  		<td><a id="deleteitem" href="<c:url value="/remove_from_cart.do?position=${status.index}"/>"></a></td>
    </tr>
</c:forEach>
</table>
<p id="totalprice">Всего: ${totalprice} грн.</p>
<a id="erase" href="<c:url value="/erase_cart.do"/>" >Очистить корзину</a>
		
<button class="orderbutton" onclick="PopUpShow()">Составить заказ</button>

    </c:otherwise>
</c:choose>
</c:otherwise>
</c:choose>
</div>

<div class="b-popup" id="popup1">
	<div class="b-popup-content">
	<h3 id="roomcaption"> Заказ</h3>
<table id="casttable" border="1px solid black" cellpadding="10px">
<tr class="headers">
	<td>N</td>
    <td>id</td>
    <td>Название</td>
    <td>Цена, грн</td>
    <td>Количество</td>
</tr>
<c:forEach var="cart" items="${Cart.getItems()}" varStatus="status">
    <tr>
    	<td>${status.index+1}</td>
    	<td>${cart.getItem().getId()}</td>
  		<td>${cart.getItem().getName()}</td>
  		<td>${cart.getTotalprice()}</td>
  		<td>${cart.getCount()}</td>
    </tr>
</c:forEach>
</table>
<p id="totalprice2">Всего: ${totalprice} грн.</p>
<div class="divform">
<form action="javascript:void(null);"  method="post" id="formorder"   onsubmit="call()">
    		Карта: <input type="text" name="card">
        <br>Адрес: <input type="text" name="address">
    <br><input id="orderbutt" type="submit" value="Заказать">
</form>
</div>

<a id="erase" onclick="PopUpHide()">Отменить</a>    
</div>
</div>

<div class="b-popup2" id="popup2">
	<div class="b-popup-content2">
	<h3 id="roomcaption"> Изменить</h3>
	<div class="divform2">
<table id="editaddtable">
<tr class="headers">
    <td>Название</td>
    <td>Описание</td>
    <td>Цена, грн</td>
    <td>Фото</td>
</tr>
</table>
<form action="javascript:void(null);"  id="formEdit"   onsubmit="updateItem()">
        <input type="hidden" name="id" >
        <input type="text" name="name">
        <input type="text" name="feature">
        <input type="text" name="price">
        <input type="text" name="photo">
    <br><input id="orderbutt2" type="submit" value="Изменить">
</form>
<a id="erase" onclick="hideEdit()">Отменить</a>  
</div>
</div>
</div>

<div class="b-popup2" id="popup3">
	<div class="b-popup-content2">
	<h3 id="roomcaption"> Добавить</h3>
	<div class="divform2">
<table id="editaddtable" >
<tr class="headers">
    <td>Название</td>
    <td>Описание</td>
    <td>Цена, грн</td>
    <td>Фото</td>
</tr>
</table>
<form action="javascript:void(null);"  method="post" id="formAdd"   onsubmit="addItem()">
        <input type="text" name="name">
        <input type="text" name="feature">
        <input type="text" name="price">
        <input type="text" name="photo">
    <br><input id="orderbutt2" type="submit" value="Добавить">
</form>
<a id="erase" onclick="hideAdd()">Отменить</a>
</div>
</div>
</div>

    
</div>
</body>
</html>