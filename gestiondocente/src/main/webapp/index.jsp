<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controler.Constantes"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controler.i18nmesages" /> 
<jsp:include page="includes/header.jsp"/>
 
<main>
<div class="container">
<c:if test="${not empty sessionScope.usuario}">


	
		<h1><fmt:message key="index.mensaje" /></h1>

		<h2><fmt:message key="index.nombreApp" /></h2>
</c:if>

<c:if test="${empty sessionScope.usuario}">
		<div class="wrapper">
		    <form class="form-signin" action="<%=Constantes.SERVLET_LOGIN%>" method="post">    
		      	<h2 class="form-signin-heading">Login</h2>
		      	
		     	<input type="text" class="form-control" name="<%=Constantes.PAR_USUARIO %>" placeholder="<fmt:message key="index.login.user" />" required="" autofocus="" />
		      	<br>
		      	<input type="password" class="form-control" name="<%=Constantes.PAR_PASSWORD %>" placeholder="<fmt:message key="index.login.password" />" />      
		     	<br>
		     	<label for="<%=Constantes.PAR_IDIOMA%>"><fmt:message key="index.login.idioma"/></label>
		     	 
		     	<select class="form-control" name="<%=Constantes.PAR_IDIOMA %>" id="<%=Constantes.PAR_IDIOMA %>" >
			 		<option value="<%=Constantes.IDIOMA_CASTELLANO%>"><fmt:message key="menu.IdiomaE" /></option>
				   	<option value="<%=Constantes.IDIOMA_EUSKERA%>"><fmt:message key="menu.IdiomaEu" /></option>
				   	<option value="<%=Constantes.IDIOMA_INGLES%>"><fmt:message key="menu.IdiomaI" /></option>
				</select>
		     <br>
		     <br>
		      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
		    </form>
	  </div>
</c:if>
</div>
</main>
<br>

<%@ include file="includes/footer.html" %>
</body>
</html>