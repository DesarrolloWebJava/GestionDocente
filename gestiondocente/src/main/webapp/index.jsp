<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controler.Constantes"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controler.i18nmesages" /> 
<jsp:include page="includes/header.jsp"/>
${sessionScope.usuario}
${bandera}
<main>
<div class="container">
	<c:if test="${not empty sessionScope.usuario}">
		<div class="wrapper">
			<h1><fmt:message key="index.mensaje" /></h1>
			<h2><fmt:message key="index.nombreApp" /></h2>
		</div>
	</c:if>	


		<div class="wrapper">
		
			<c:set var="name" value="${not empty cookie.username ? cookie.username.value : ''}" scope="page" />
			<c:set var="namepass" value="${not empty cookie.password ? cookie.password.value : ''}" scope="page" />
			<c:set var="nameidioma" value="${not empty cookie.lang ? cookie.lang.value : ''}" scope="page" />
		    <form class="form-signin" action="<%=Constantes.SERVLET_LOGIN%>" method="post">    
		      	<h2 class="form-signin-heading">Login</h2>
		      	
		     	<input type="text" class="form-control" name="<%=Constantes.PAR_USUARIO %>" placeholder="<fmt:message key="index.login.user" />" value="${name}"  required="" autofocus="" />
		      	<br>
		      	<input type="password" class="form-control" name="<%=Constantes.PAR_PASSWORD %>" placeholder="<fmt:message key="index.login.password" />" value="${namepass}" />      
		     	<br>
		     	<label for="<%=Constantes.PAR_IDIOMA%>"><fmt:message key="index.login.idioma"/></label>
		     	 
		     	<select class="form-control" name="<%=Constantes.PAR_IDIOMA %>" id="<%=Constantes.PAR_IDIOMA %>" >
			 		<option value="<%=Constantes.IDIOMA_CASTELLANO%>" ${nameidioma == '2' ? 'selected' : ''}><fmt:message key="menu.IdiomaE" /></option>
				   	<option value="<%=Constantes.IDIOMA_EUSKERA%>" ${nameidioma == '1' ? 'selected' : ''}><fmt:message key="menu.IdiomaEu" /></option>
				   	<option value="<%=Constantes.IDIOMA_INGLES%>" ${nameidioma == '3' ? 'selected' : ''}><fmt:message key="menu.IdiomaI" /></option>
				</select>
		     <br>
		     <br>
			<div class="checkbox">
			    <label for="recuerdame">
			        <input type="checkbox" name="recuerdame" id= "recuerdame"> Recuérdame
			    </label>
			</div>
			<br>
		      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
		    </form>
	  </div>

</div>
</main>


<%@ include file="includes/footer.html" %>
</body>
</html>