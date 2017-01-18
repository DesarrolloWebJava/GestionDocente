<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Docente - Listado Alumnos</title>
</head>
<body>
	<header><h1>Pagina listado de Alumnos</h1></header>
	
	<main>
	<%
	//esto es un script de java
	List<Alumno> alumnos= (List<Alumno>)request.getAttribute("listado-alumnos");
		
	for (Alumno alumno :alumnos){
		
		out.println(alumno.toString());	
	}
	
	
	%>
	
	</main>
</body>
</html>