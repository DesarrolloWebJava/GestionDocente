<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Creamos la variable language -->
    <c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
    <!-- formatea el texto en el lenguaje que indica la variable -->
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="com.ipartek.formacion.controller.i18nmesages" /> 
    <jsp:include page="includes/header.jsp"></jsp:include>
	<main>
	
		<!-- Cargamos los mensajes de los properties -->
		<fmt:message key="index.mensaje"/> <fmt:message key="index.nombreApp"/>
		<!-- Formulario de entrada de usuario -->
		<form action="<%=Constantes.SERVLET_LOGIN %>" method="post">
		<div>
			<input type="text" placeholder="<fmt:message key="index.login.username"/>" 
		name ="<%=Constantes.PAR_USUARIO %>" id="<%=Constantes.PAR_USUARIO %>" > 
		</div>
		 <div>
		 	<input type="password" placeholder="<fmt:message key="index.login.password"/>" 
		name ="<%=Constantes.PAR_PASSWORD %>" id="<%=Constantes.PAR_PASSWORD %>" > 
		 </div>
		<div>
			<label for ="<%=Constantes.PAR_IDIOMA %>"><fmt:message key="index.login.idioma"/></label>
		<select id="<%=Constantes.PAR_IDIOMA %>" name="<%=Constantes.PAR_IDIOMA %>">
			<option value="<%=Constantes.IDIOMA_CASTELLANO %>"><fmt:message key="idioma.castellano"/></option>
			<option value="<%=Constantes.IDIOMA_EUSKERA %>"><fmt:message key="idioma.euskera"/></option>
			<option value="<%=Constantes.IDIOMA_INGLES %>"><fmt:message key="idioma.ingles"/></option>
		</select>
		</div>
		<div>
			<input type="submit" value="<fmt:message key="index.login.enviar"/>"/>
		</div>
		</form>
	</main>
	<%@ include file="includes/footer.html" %>
</body>
</html>