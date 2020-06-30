<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="room_styles.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
<!-- <script>
 $(document).ready(function(){
	 $.ajax({
		 type: "POST",
         url: '<c:url value="/ReturnPage"></c:url>',
         data:{
        	 id:"ttt"
         },
         success: function(data){
        	 var i=1;
    		 while(i<=data){
    			 $("#pagin").append($("<a>"+i+"</a>"));
    		 }
    		 alert("Its OKEY")
         }		
	 });
     	
 });
 </script>-->
    <script>
        function addToCart(it_id){
            $.ajax({
            	type: "POST",
                url: '<c:url value="/add_to_cart.do"></c:url>',
                data: {
                    id:it_id
                },
                success: function (msg){
                    alert("Продукт добавлен в корзину");
                }
            });
        }
    </script>
<title>Каталог товаров</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<ul id="catalogblock">
<c:forEach var="item" items="${product}"  >
<li id="list">
<div class="photoblock">
<img src="images/${item.getPhoto()}" width="100" height="196"/>
</div>
<p class="titleblock">${item.getName()}</p>
<c:choose>
      <c:when test="${empty login}">
        <a class="buttoncart" href="<c:url value="/login.do"/>"></a>
      </c:when>
      <c:otherwise>
        <a class="buttoncart" onclick="addToCart(${item.getId()})"></a>
      </c:otherwise>
</c:choose>
<p class="priceblock"><strong>${item.getPrice()}</strong> грн.</p>
<p class="featureblock">${item.getFeature()}</p>
</li>
</c:forEach>
</ul>
<!--<div id="pagin">
</div>-->
</body>
</html>