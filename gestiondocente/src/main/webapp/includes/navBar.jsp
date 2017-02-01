<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	    <c:set var="idioma" value="${not empty  cookie.language ?  cookie.language.value : 'es_ES'}"  />
    <c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : idioma }" scope="page" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages"/>

			<nav>
				<ul>
					<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>"><fmt:message key="navBar.alumno" /></a></li>
					<li><a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>"><fmt:message key="navBar.profesor" /></a></li>
					<li><a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>"><fmt:message key="navBar.curso" /></a></li>
					<%
				if(session!=null&&!session.isNew()&&session.getAttribute(Constantes.SESSION_IDIOMA)!=null){
					Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
					 
					 
				
				%>
					 
					<li><div class="cerrarSesion"><a href="<c:url value='login.do' />" />Cerrar Sesión</a> </div></li>
					<li><div class="cerrarSesion userLogin">Usuario: <%=p.getNombre() %> <%=p.getApellidos() %></div></li> 
				<% String admin = "admin";
				if(admin.equals(p.getNombre())){ %>
					<li><div class="cerrarSesion"><a href="<c:url value='usuario.do' />" />Lista Usuarios</a> </div></li>
				<%
					}
				}
					
					//session.getAttribute(name)/lo primero para sacar el usuario en la barra de navegacion
				%>
				</ul>
				
				<!--<c:if test="${empty session}">
					<div class="cerrarSesion"><a href="">Cerrar Sesión</a></div>
				</c:if>-->
				
					
				
			</nav>
			
		
	