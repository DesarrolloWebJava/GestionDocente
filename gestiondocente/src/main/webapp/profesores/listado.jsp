<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.service.ProfesorServiceImp"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.service.ProfesorService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión Docente - Listado Profesores</title>
</head>
<body>
	<header><h1>Página de Listado de Profesores</h1></header>
	<main>
		<%
		//List<Alumno> alumnos = (List<Alumno>)request.getAttribute("listado-alumnos");
		
		//ProfesorService profesorService = new ProfesorServiceImp();
		//Map<Integer, Profesor> profesores = profesorService.getAll();
		Map<Integer, Profesor> profesores = (Map<Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
%>
<ul>
<%
		for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
			int codigo = entry.getKey();
			Profesor profesor = entry.getValue();
			out.println("<li>" + profesor.getEmail() + "</li>");
		}
		%>
		</ul>
	</main>
</body>
</html>