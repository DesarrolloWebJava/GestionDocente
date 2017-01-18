<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión Docente - Listado Profesores</title>
</head>
<body>
	<header><h1>Página Listado de Profesores</h1></header>
	<main>
	<%
	/*Esto es un scriplet */
	
	//recogemos el atributo de la request
	Map<Integer, Profesor> profesores =(Map<Integer, Profesor>)request.getAttribute("listado-profesores");


	for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
			out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
		}
	
	%>
	
	
	
	</main>
</body>
</html>