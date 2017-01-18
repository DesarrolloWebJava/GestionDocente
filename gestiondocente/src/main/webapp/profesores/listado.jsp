<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión Docente - Listado Profesores</title>
</head>
<body>
<header>
<h1>Página Listado Profesores</h1>
</header>
<main>
<%
Map<Integer,Profesor> profesores=(Map<Integer,Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
for(Map.Entry<Integer,Profesor> entrada:profesores.entrySet()){
	out.println(entrada.getValue().toString());

}
%>
</main>

</body>
</html>