<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
		<header><h2>Usuarios conectados</h2></header>
		<!-- Recorrer la lista de personas(usuarios) -->
		<c:forEach var="usuario" items="${listadoUsuarios}">
			<div>
				${usuario.nombre} ${usuario.apellidos}   <a href="<%=Constantes.SERVLET_USUARIO%>?<%=Constantes.PAR_SESSION_ID%>=${usuario.sessionId}"> Expulsar</a>
			</div>
		</c:forEach>
		<!-- Plus: añada boton kick(expulsar a un usuario conectado-->
	</main>
	<%@ include file="../includes/footer.html" %>
</body>
</html>