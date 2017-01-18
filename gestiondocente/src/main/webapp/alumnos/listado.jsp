<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión Docente - Listado Alumnos</title>
</head>
<body>
	<header><h1>Página Listado de Alumnos</h1></header>
	<main>
	<%
	/*Esto es un scriplet */
	
	//recogemos el atributo de la request
	List<Alumno> alumnos = (List<Alumno>)request.getAttribute("listado-alumnos");
	%>
	<ul>
	<%
	for(Alumno alumno: alumnos){
		out.println("<li>"+alumno.toString()+"</li>");
	}
	 %>
	</ul>
	%>
	
	
	
	</main>
</body>
</html>