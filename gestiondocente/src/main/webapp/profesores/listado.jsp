<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente -Listado de Profesores</title>
</head>
<body>

<header><h1>Pagina Listado de Profesores</h1></header>
	<main>
	<%
	
	Map<Integer,Profesor> profesor = (Map<Integer, Profesor>)request.getAttribute("listado-profesores");
	
	for(Map.Entry<Integer,Profesor> entry:profesor.entrySet()){
		out.println("clave="+entry.getKey()+",valor"+entry.getValue());
	}
	
	/*Esto es un scriplet*/
	//Recogemos el atributo de la request
	
	%>


</body>
</html>