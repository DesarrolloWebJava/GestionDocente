<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado Profesores</title>
</head>
<body>
<h1>Listado Profesores</h1>
	<%
		/*esto es un criplet*/
		//recogemos el atributo de la request y lo gardamos en un objeto lista
		//hay q hacer un casting de request y decirle q es tipo list
		Map<Integer, Profesor > profesores =(Map<Integer, Profesor>) request.getAttribute("listado-profesores"); 
		//sacar la lista por pantalla	
	%>
	<ul>
	<% 
	for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
		 int codigo = entry.getKey();
		 Profesor profesor = entry.getValue();
		 out.println("clave=" + entry.getKey() + ", valor=" + profesor.getEmail());
	   
	}
	
	%>
	</ul>
</body>
</html>