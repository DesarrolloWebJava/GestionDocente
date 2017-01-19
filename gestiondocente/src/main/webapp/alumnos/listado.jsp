<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Gestión Docente -Listado Alumnos</title>
	</head>
	<body>
		<header>
			<h1>Página Listado de Alumnos</h1>
		</header>
		<main>
			<ul>
			<%
			/* esto es un scriplet */
				List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
			%>
			<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Alumno</a>
			<%
				for(Alumno alumno: alumnos){
					out.println("<li>"+alumno.toString()+"</li>");
				}
			%>
			</ul>
		</main>
		<footer>
		</footer>
	</body>
</html>