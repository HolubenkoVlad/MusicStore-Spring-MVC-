<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
<header>
<nav>
	<ul id="navbar">
		<li> <a href="<c:url value="/list.do"/>">Каталог товаров</a></li>
		
		<c:choose>
      <c:when test="${empty login}">
        <li> <a href="<c:url value="/login.do"/>">Вход</a></li>	
      </c:when>
      <c:otherwise>
        <li> <a href="<c:url value="/privateroom.do"/>">${login.getLogin()}</a></li>
      </c:otherwise>
    	</c:choose>
    
				
	</ul>
</nav>
</header>
</div>