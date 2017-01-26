<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente - Crear Profesor</title>
</head>
<jsp:include page="../includes/header.jsp"/>

<%
	String titulo = "";
	Profesor profesor = (Profesor)request.getAttribute(Constantes.ATT_PROFESOR);
	if(profesor==null){//es un create
		titulo="Crear Profesor";	
		profesor = new Profesor();
	}
	else{//es un update
		titulo="Actualizar Profesor";	
	
	}
%>



<main>

<header><h2><%=titulo %></h2></header>



	<form action"<%=Constantes.SERVLET_PROFESOR %>" metod="post">
	<input type ="hidden" name="<%=Constantes.PAR_CODIGO %>" value="<%=profesor.getCodigo()%>" id=<%=Constantes.PAR_CODIGO %>">
	<div>
		<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
		<input type="text" placeholder="Introduzca su nombre..." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE%>" value="<%=profesor.getNombre()%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
		<input type="text" placeholder="Introduzca su apellido..." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS%>" value="<%=profesor.getApellidos()%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_DNI%>">Dni:</label>
		<input type="text" placeholder= "Introduzca su dni..." name="<%=Constantes.PAR_DNI%>" id="<%=Constantes.PAR_DNI%>"value="<%=profesor.getDni()%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_EMAIL%>">Email:</label>
		<input type="email" placeholder= "Introduzca su Email..." name="<%=Constantes.PAR_EMAIL%>" id="<%=Constantes.PAR_EMAIL%>" value="<%=profesor.getEmail()%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_DIRECCION%>">Direccion:</label>
		<input type="text" placeholder= "Introduzca su direccion..." name="<%=Constantes.PAR_DIRECCION%>" id="<%=Constantes.PAR_DIRECCION%>" value="<%=profesor.getDireccion()%>">
	</div>
	<div>
		<%
			String date = "";
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(profesor.getfNacimiento());
			date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
			%>
		<label for="<%=Constantes.PAR_FNACIMIENTO%>">Fecha Nacimiento:</label>
		<input type="text" placeholder= "Introduzca su fecha de nacimiento..." name="<%=Constantes.PAR_FNACIMIENTO%>" id="<%=Constantes.PAR_FNACIMIENTO%>" value="<%=date%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_NSS%>">Numero de Seguridad Social:</label>
		<input type="number" placeholder= "Introduzca el numero de la Seguridad Social..." name="<%=Constantes.PAR_NSS%>" id="<%=Constantes.PAR_NSS%>" value="<%=profesor.getnSS()%>">
	</div>
		
		<input type="submit" value="Enviar"/>
		
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>