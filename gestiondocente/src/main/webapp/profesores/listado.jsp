<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión Docente - Listado Profesores</title>
</head>
<body>
<header><h1>Listado Profesores</h1></header>
<main>
	<%
		Map<Integer,Profesor> profesores = (Map<Integer,Profesor>) request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	
	for(Map.Entry<Integer,Profesor> entry: profesores.entrySet()){
		out.write("<p>"+entry.getValue().toString()+"</p>");
	}
	%>
</main>
<footer>
</footer>

</body>
</html>