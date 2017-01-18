<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente - Listado Alumnos</title>
</head>
<body>
<header><h1>Página Listado Alumnos</h1></header>
<main>
<%
/*Esto es un scriplet*/
List<Alumno> alumnos=(List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
for(Alumno alumno:alumnos){
	out.println(alumno.toString());
	
}
%>
</main>
</body>
</html>