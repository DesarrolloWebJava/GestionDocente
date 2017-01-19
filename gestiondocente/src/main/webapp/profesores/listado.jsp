<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión Docente -Listado profesores</title>
</head>
<body>
<header>
			<h1>Página Listado de Profesores</h1>
		</header>
		<main>
			<ul>
			<%
			/* esto es un scriplet */
				Map<Integer, Profesor> profesor = (HashMap<Integer, Profesor>)request.getAttribute("listado-profesores");
			
				for (Map.Entry<Integer, Profesor> entry : profesor.entrySet()) {
				
					out.println("<li>"+ entry.getValue()+"</li>");
				}
			%>
			</ul>
		</main>
		<footer>
		</footer>
</body>
</html>