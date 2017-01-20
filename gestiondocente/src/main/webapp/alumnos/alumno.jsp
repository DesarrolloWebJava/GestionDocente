<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente - Crear Alumno</title>
</head>
<jsp:include page="../includes/header.jsp"/>
<main>

Crear Alumno


		<form action"<%=Constantes.SERVLET_ALUMNO %>" method="post"> 
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" value="-1" id="<%=Constantes.PAR_CODIGO%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
			<input type="text" placeholder= "Introduzca su nombre" name="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
			<input type="text" placeholder="Introduzca su apellido..." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS%>">
		</div>
			<div>
			<label for="<%=Constantes.PAR_DNI%>">Dni:</label>
			<input type="text" placeholder= "Introduzca su dni..." name="<%=Constantes.PAR_DNI%>" id="<%=Constantes.PAR_DNI%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL%>">Email:</label>
			<input type="email" placeholder= "Introduzca su Email..." name="<%=Constantes.PAR_EMAIL%>" id="<%=Constantes.PAR_EMAIL%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DIRECCION%>">Direccion:</label>
			<input type="text" placeholder= "Introduzca su direccion..." name="<%=Constantes.PAR_DIRECCION%>" id="<%=Constantes.PAR_DIRECCION%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_FNACIMIENTO%>">Fecha Nacimiento:</label>
			<input type="text" placeholder= "Introduzca su fecha de nacimiento..." name="<%=Constantes.PAR_FNACIMIENTO%>" id="<%=Constantes.PAR_FNACIMIENTO%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_NHERMANOS%>">Numero de hermanos:</label>
			<input type="number" placeholder= "Introduzca el numero de hermanos..." name="<%=Constantes.PAR_NHERMANOS%>" id="<%=Constantes.PAR_NHERMANOS%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_ACTIVO%>">Activo:</label>
			<select name"<%=Constantes.PAR_ACTIVO %>>
				<option value="1">Activo</option>
				<option value="0">Descativo</option>			
			</select>
		</div>
		
		<input type="submit" value="Enviar"/>
		
	</form>
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>