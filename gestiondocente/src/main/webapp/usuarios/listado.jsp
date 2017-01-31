<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../includes/header.jsp"/>
	<header><h1>Pagina listado de Usuarios activos</h1></header>
	
	<main>
	<%
	//esto es un script de java
	List<Persona> personas= (List<Persona>)request.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
		
	%>
	<c:forEach var ="persona" items="${listadoUsuarios}">
		<div>
		${persona.nombre} ${persona.sessionID} <a href="<%=Constantes.SERVLET_USUARIOS_ACTIVOS%>?sessionID=${persona.sessionID}">Expulsar de la sesion</a> 
		</div>
	</c:forEach>
	
	</main>
	
<!-- Recorrer la lista de persona (usuarios conectados)   -->
<!-- Cuando se termine lo anterior , añadir boton de kick(exppulsar a un usuario)  --> 
</body>
</html>