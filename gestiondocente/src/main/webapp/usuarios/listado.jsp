<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"/>

	<div class="container">
	
		<h1>Usuarios Conectados</h1>
	
		<!-- Recorrer la lista (jstl o scrimbles) -->
		<c:forEach items="${listadoUsuario}" var="item">
		<p>
		  	<h2>${item.nombre}
		  	<a href="<%=Constantes.SERVLET_LOGIN%>" class="btn  btn-warning btn-sm">Desconectar</a></h2>
		</p>
		</c:forEach> 
	</div>
<!-- Plus: añada boton de  kick(expulsar a alguien)-->


<%@ include file="../includes/footer.html" %>
</body>
</html>