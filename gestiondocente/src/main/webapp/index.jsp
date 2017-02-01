<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:set var="idioma" value="${not empty  cookie.language ?  cookie.language.value : 'es_ES'}"  />
    <c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : idioma}" scope="page" />
  

     
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages"/>
		<jsp:include page="includes/header.jsp" />
		<main>
		
			<% if(session==null||session.isNew()||session.getAttribute(Constantes.SESSION_IDIOMA)==null){ %>
				<c:set var="name" value="${not empty cookie.username ? cookie.username.value : '' }" />
				<c:set var="passwd" value="${not empty cookie.password ? cookie.password.value : '' }" />
				
				<form action="<%=Constantes.SERVLET_LOGIN %>" method="post" name=>
				<div class="formulario">
					<h2>Iniciar sesión</h2>
					<c:import url="/includes/mensajes.jsp"/>
					<div class="formularioInput">
						<input type="text" value="${name}" name="<%=Constantes.PAR_USUARIO%>" id="<%=Constantes.PAR_USUARIO%>" placeholder='<fmt:message key="index.login.username" />' >
					</div>
					<div class="formularioInput">
						<input type="password" value="${passwd}" name="<%=Constantes.PAR_PASSWORD%>" id="<%=Constantes.PAR_PASSWORD%>" placeholder='<fmt:message key="index.login.password" />' >
					</div>
					<div class="formularioInput">
						<label id= name=<%=Constantes.PAR_IDIOMA%>><fmt:message key="index.login.idioma" /></label>
				
						<select id="<%=Constantes.PAR_IDIOMA%>" name="<%=Constantes.PAR_IDIOMA%>">

								<option ${"es_ES" == language ? 'selected' : ''} value="<%=Constantes.IDIOMA_ES%>"><fmt:message key="idioma.castellano" /></option>
								<option ${"eu_EU" == language ? 'selected' : ''} value="<%=Constantes.IDIOMA_EU%>"><fmt:message key="idioma.euskera" /></option>
								<option ${"en_EN" == language ? 'selected' : ''} value="<%=Constantes.IDIOMA_EN%>"><fmt:message key="idioma.ingles" /></option>

						</select>
					</div>
					<div class="formularioInput">
						<input type="checkbox" value="si" name="recuerdame" id="recuerdame" ><label>Recuerdame</label> </inpuit>
						
					</div>
					<div class="formularioInput finalForm">
						<input type="submit" name="" id="" value="<fmt:message key="index.login.enviar" />" />	
					</div>	
					</div>
				</form>
			<% }else{ %>
				<h1><fmt:message key="index.mensaje" />
				<fmt:message key="index.nombreApp" /></h1>
			<%} %>
			
			
		</main>
		<%@ include file="includes/footer.html" %>
	</body>
</html>