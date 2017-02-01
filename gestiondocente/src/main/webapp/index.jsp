<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" />

<jsp:include page="includes/header.jsp" />
<main>
	
	<div class="myproperties">
		<p><fmt:message key="index.mensaje"/></p>
		<p><fmt:message key="index.nombreApp"/></p>
	</div>

<!--  
	<c:choose>
		<c:when test="${not_empty_cookie.username}">
			<c:set var="name" value="${cookie.username}"/>
		</c:when>
		<c:otherwise>
			<c:set var="name" value="''" />
		</c:otherwise>
	</c:choose>
-->
	<c:set var="name" value="${not empty cookie.username ? cookie.username.value : ''}" />
	<c:set var="password" value="${not empty cookie.password ? cookie.password.value : ''}" />
	<c:set var="language" value="${not empty cookie.language ? cookie.language.value : 'es_ES'}" />
	
	<form action="<%=Constantes.SERVLET_LOGIN%>" method="post">
	
		<!-- El $name lo define el choose de arriba -->
		<input value="${name}" name="<%=Constantes.PAR_USUARIO%>" type="text" placeholder="<fmt:message key="index.login.username"/> "/>
		
		<input value="${password}" name="<%=Constantes.PAR_PASSWORD%>" type="password" placeholder="<fmt:message key="index.login.password"/> "/>
		
	<label for="<%=Constantes.PAR_IDIOMA%>"><fmt:message key="index.login.idioma"/></label>
			
			<select id="<%=Constantes.PAR_IDIOMA%>" name="<%=Constantes.PAR_IDIOMA%>" >
				<option ${language == '2' ? 'selected' : '' } value="<%=Constantes.IDIOMA_CASTELLANO%>"><fmt:message key="idioma.castellano"/></option>
				<option ${language == '1' ? 'selected' : '' } value="<%=Constantes.IDIOMA_EUSKERA%>"><fmt:message key="idioma.euskera"/></option>
				<option ${language == '3'  ? 'selected' : '' } value="<%=Constantes.IDIOMA_INGLES%>"><fmt:message key="idioma.ingles"/></option>
			</select>
			
			<!-- En el servlet se recoge el name. En el for se lee el id. Id y name son iguales. -->
	<input type="checkbox" value="si" name="recuerdame" id="recuerdame"><label for="recuerdame">Recuérdame</label>
	<input type="submit" value="<fmt:message key="index.login.enviar"/> "/>
</form>
</main>
<%@ include file="includes/footer.html" %>
</body>
</html>