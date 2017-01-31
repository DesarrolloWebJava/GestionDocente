<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main>
	<header><h2>Usuarios Conectados</h2></header>
	<!-- Recorrear la lista de personas -->
	
	 <c:forEach  var="usuario" items="${requestScope.listadoUsuarios}">
	 
	<p>${usuario.nombre} ${usuario.apellidos} <a href='<%=Constantes.SERVLET_USUARIOS%>?<%=Constantes.PAR_SESSION%>=${usuario.sessionId}'>Expulsar</a></p>
	</c:forEach>
	

	<!--  plus añada boton de echar a la calle -->
</main>
<%@ include file ="../includes/footer.html" %>
</body>
</html>