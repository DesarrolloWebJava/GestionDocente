<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
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

<%
	String titulo = "";
	Alumno alumno = (Alumno)request.getAttribute(Constantes.ATT_ALUMNO);
	if(alumno==null){//es un create
		titulo="Crear Alumno";	
		alumno = new Alumno();
	}
	else{//es un update
		titulo="Actualizar Alumno";	
	}
%>


<main>

<header><h2><%=titulo %></h2></header>
		



		<form action"<%=Constantes.SERVLET_ALUMNO %>" method="post"> 
		<input type="hidden" name="<%=Constantes.PAR_CODIGO%>" value="<%=alumno.getCodigo()%>" id="<%=Constantes.PAR_CODIGO%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
			<input type="text" placeholder= "Introduzca su nombre" name="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>" value="<%=alumno.getNombre()%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
			<input type="text" placeholder="Introduzca su apellido..." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS%>" value="<%=alumno.getApellidos()%>">
		</div>
			<div>
			<label for="<%=Constantes.PAR_DNI%>">Dni:</label>
			<input type="text" placeholder= "Introduzca su dni..." name="<%=Constantes.PAR_DNI%>" id="<%=Constantes.PAR_DNI%>" value="<%=alumno.getDni()%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL%>">Email:</label>
			<input type="email" placeholder= "Introduzca su Email..." name="<%=Constantes.PAR_EMAIL%>" id="<%=Constantes.PAR_EMAIL%>" value="<%=alumno.getEmail()%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DIRECCION%>">Direccion:</label>
			<input type="text" placeholder= "Introduzca su direccion..." name="<%=Constantes.PAR_DIRECCION%>" id="<%=Constantes.PAR_DIRECCION%>" value="<%=alumno.getDireccion()%>">
		</div>
		<div>
			<%
			String date = "";
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(alumno.getfNacimiento());
			date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
			%>
			<label for="<%=Constantes.PAR_FNACIMIENTO%>">Fecha Nacimiento:</label>
			<input type="text" placeholder= "Introduzca su fecha de nacimiento..." name="<%=Constantes.PAR_FNACIMIENTO%>" id="<%=Constantes.PAR_FNACIMIENTO%>" value="<%=date %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_NHERMANOS%>">Numero de hermanos:</label>
			<input type="number" placeholder= "Introduzca el numero de hermanos..." name="<%=Constantes.PAR_NHERMANOS%>" id="<%=Constantes.PAR_NHERMANOS%>" value="<%=alumno.getnHermanos() %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_ACTIVO%>">Activo:</label>
			<select name"<%=Constantes.PAR_ACTIVO %>>
				<%
				if (alumno.isActivo()){
				%>
					<option selected value="1">Activo</option>
					<option>value="0">Desactivado</option>
				<%}else{
					%>
					<option value="1">Activo</option>
					<option selected value="0">Desactivado</option>			
				<%} %>
			</select>
		</div>
		
		<input type="submit" value="Enviar"/>
		
	</form>
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>