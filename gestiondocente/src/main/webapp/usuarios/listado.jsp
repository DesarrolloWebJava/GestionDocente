<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset ="UTF-8">
<title>Usuarios conectados</title>
</head>
<body>
<c:forEach items="${listadoUsuarios}" var="aux">
		<div>
		 ${aux.nombre}
		</div>
	</c:forEach>
</body>
</html>