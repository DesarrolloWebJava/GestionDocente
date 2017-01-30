<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages"/>

			<nav>
				<ul>
					<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>"><fmt:message key="navBar.alumno" /></a></li>
					<li><a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>"><fmt:message key="navBar.profesor" /></a></li>
					<li><a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>"><fmt:message key="navBar.curso" /></a></li>
				</ul>
				
				<!--<c:if test="${empty session}">
					<div class="cerrarSesion"><a href="">Cerrar Sesión</a></div>
				</c:if>-->
				<%
				if(session!=null&&!session.isNew()&&session.getAttribute(Constantes.SESSION_IDIOMA)!=null){
				%>
					<div class="cerrarSesion"><a href="<c:url value='login.do' />" />Cerrar Sesión</a></div>
				<%
				}
				%>
			</nav>
			
		
	