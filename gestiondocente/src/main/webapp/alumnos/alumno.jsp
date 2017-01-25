<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<jsp:include page="../includes/header.jsp"></jsp:include>
	<%
		//Si recibimos un alumno
		Alumno alumno = (Alumno)request.getAttribute(Constantes.ATT_ALUMNO);
		String titulo = "";
		if(alumno == null){//CREATE
			titulo = "Crear alumno";
			//Si no existe creamos uno nuevo para que el value del form no de un NullPointerException
			alumno = new Alumno();
		}else{//UPDATE
			titulo = "Actualizar alumno";
		}
	%>
	<main>
	<header><h2><%=titulo %></h2></header>
	<form action="<%=Constantes.SERVLET_ALUMNO %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" 
		id="<%=Constantes.PAR_CODIGO %>" value="${alumno.codigo }">
	<div>
		<label for="<%=Constantes.PAR_NOMBRE %>">Nombre: </label>
		<input type="text" placeholder="Introduzca su nombre..." 
		name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>" 
		value="${alumno.nombre}">
	</div>
	<div>
		<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos: </label>
		<input type="text" placeholder="Introduzca los apellidos..." 
		name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>" 
		value="<%=alumno.getApellidos()%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_DNI %>">DNI: </label>
		<input type="text" placeholder="Introduzca el DNI..." 
		name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>" 
		value="<%=alumno.getDni() %>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_EMAIL %>">Email: </label>
		<input type="email" placeholder="Introduzca su email..." 
		name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>" 
		value="<%=alumno.getEmail()%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_DIRECCION %>">Dirección: </label>
		<input type="text" placeholder="Introduzca la dirección..." 
		name="<%=Constantes.PAR_DIRECCION %>" id="<%=Constantes.PAR_DIRECCION %>" 
		value="<%=alumno.getDireccion()%>">
	</div>
	<div>
		<%
			String date = "";
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(alumno.getfNacimiento());
			date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"
			+(GregorianCalendar.MONTH+1)+"/"+(GregorianCalendar.YEAR);
		%>
		<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de nacimiento: </label>
		<input type="text" placeholder="Introduzca la fecha de nacimiento..." 
		name="<%=Constantes.PAR_FNACIMIENTO %>" id="<%=Constantes.PAR_FNACIMIENTO %>" 
		value="<%=date%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_NHERMANOS %>">Número de hermanos: </label>
		<input type="number" placeholder="Introduzca el numero de hermanos..." 
		name="<%=Constantes.PAR_NHERMANOS %>" id="<%=Constantes.PAR_NHERMANOS %>" 
		value="<%=alumno.getnHermanos()%>">
	</div>
	<div>
		<label for="<%=Constantes.PAR_ACTIVO %>">Activo: </label>
		<select name="<%=Constantes.PAR_ACTIVO %>">
		<%
		if(alumno.isActivo()){
		%>
			<option selected value="1">Activo</option>
			<option value="0">Desactivado</option>	
		<% }else{ %>
			<option value="1">Activo</option>
			<option selected value="0">Desactivado</option>
		<% 
		} 
		%>
		</select>
	</div>
	<input type="submit" value ="Enviar" />
	</form>
	</main>
	<%@ include file="../includes/footer.html" %>
</body>
</html>