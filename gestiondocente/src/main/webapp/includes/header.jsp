<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<header>
<h1>Gestor Docente Ipartek</h1>
	<nav>
<!-- marca que lo que hay dentro es un elemento de navegaci�n-->
		<ul>
		<!-- 
		Al hacer un enlace: poner SIEMPRE # 
		defecto, aunque luego lo sustituyamos. -->
		<!-- NOTA! Cuidado no copiar el path del servlet con "/" includa, tal y 
		como aparecen en el web.xml, porque aqu hara referencia 
		no al parent folder, sino al directorio raíz. 
		-->
			<li><a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Alumnos</a></li>
			<li><a href="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ%>">G. Profesores</a></li>
			<li><a href="#">G. Cursos</a></li>
		</ul>
	</nav>
	<jsp:include page="mensajes.jsp"/>
</header>
</body>
</html>