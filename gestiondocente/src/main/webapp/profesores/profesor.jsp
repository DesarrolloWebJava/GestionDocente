<%@page import="com.ipartek.formacion.service.Util"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<jsp:include page="../includes/header.jsp"></jsp:include>
	<%
		//Si recibimos un profesor
		Profesor profesor = (Profesor)request.getAttribute(Constantes.ATT_PROFESOR);
		String titulo = "";
		if(profesor == null){//CREATE
			titulo = "Crear profesor";
			profesor = new Profesor();
		}else{//UPDATE
			titulo = "Actualizar profesor";
		}
	%>
	<main>
		<header><h2><%=titulo %></h2></header>
		<form action = "<%=Constantes.SERVLET_PROFESOR %>" method= "post">
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" 
			id ="<%=Constantes.PAR_CODIGO %>" value = "<%=profesor.getCodigo() %>" />
			<div>
				<label for= "<%=Constantes.PAR_NOMBRE %>">Nombre: </label>
				<input type="text" placeholder="Introduzca un nombre..." 
				name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>" 
				value = "<%=profesor.getNombre() %>"> 
			</div>
			<div>
				<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos: </label>
				<input type="text" placeholder="Introduce el apellido..." 
				name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>" 
				value = "<%=profesor.getApellidos() %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_DNI %>">DNI: </label>
				<input type="text" placeholder="Introduzca el DNI..." 
				name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>" 
				value = "<%=profesor.getDni() %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_EMAIL %>">Email: </label>
				<input type="email" placeholder="Introduzca el email..." 
				name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>" 
				value = "<%=profesor.getEmail() %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_DIRECCION %>">Dirección: </label>
				<input type="text" placeholder="Introduce una dirección..." 
				name="<%=Constantes.PAR_DIRECCION %>" id="<%=Constantes.PAR_DIRECCION %>" 
				value = "<%=profesor.getDireccion() %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de nacimiento: </label>
				<input type="text" placeholder="Introduzca una fecha de nacimiento..." 
				name="<%=Constantes.PAR_FNACIMIENTO %>" id="<%=Constantes.PAR_FNACIMIENTO %>" 
				value = "<%=Util.formatoFecha(profesor.getfNacimiento())%>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_NSS %>">Número de la SS: </label>
				<input type="text" placeholder="Introduzca un número de la SS..." 
				name="<%=Constantes.PAR_NSS %>" id="<%=Constantes.PAR_NSS %>" 
				value = "<%=profesor.getnSS() %>">
			</div>
			<input type="submit" value="Enviar"/>
		</form>
	</main>
	<%@ include file="../includes/footer.html" %>
</body>
</html>