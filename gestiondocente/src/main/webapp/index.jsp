<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages"/>
		<jsp:include page="includes/header.jsp" />
		<main>
			<fmt:message key="index.mensaje" />
			<fmt:message key="index.nombreApp" />
<div class="formularioLogin">
			<form action="<%=Constantes.SERVLET_LOGIN %>" method="post" name=>
				<h2>Inicia sesión</h2>
				<c:import url="/includes/mensajes.jsp"/>
				<div class="formulario">
					<input type="text" name="<%=Constantes.PAR_USUARIO%>" id="<%=Constantes.PAR_USUARIO%>" placeholder='<fmt:message key="index.login.username" />' >
				</div>
				<div class="formulario">
					<input type="password" name="<%=Constantes.PAR_PASSWORD%>" id="<%=Constantes.PAR_PASSWORD%>" placeholder='<fmt:message key="index.login.password" />' >
				</div>
				<div class="formulario">
					<label id= name=<%=Constantes.PAR_IDIOMA%>><fmt:message key="index.login.idioma" /></label>
			
					<select id="<%=Constantes.PAR_IDIOMA%>" name="<%=Constantes.PAR_IDIOMA%>">
						<option value="<%=Constantes.IDIOMA_ES%>"><fmt:message key="idioma.castellano" /></option>
						<option value="<%=Constantes.IDIOMA_EU%>"><fmt:message key="idioma.euskera" /></option>
						<option value="<%=Constantes.IDIOMA_EN%>"><fmt:message key="idioma.ingles" /></option>
					</select>
				</div>
				<div class="formulario">
					<input type="submit" name="" id="" value="<fmt:message key="index.login.enviar" />" />	
				</div>	
			</form>
			
			</div>
			
		</main>
		<%@ include file="includes/footer.html" %>
	</body>
</html>