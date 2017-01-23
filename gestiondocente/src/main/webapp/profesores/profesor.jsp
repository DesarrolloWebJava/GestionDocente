<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
	String titulo="";
	Profesor profesor=(Profesor)request.getAttribute(Constantes.ATT_PROFESOR);
	if (profesor==null){
		titulo="Crear profesor";
		profesor=new Profesor();
	}else{
		titulo="Actualizar profesor";
	}
%>
<main>
<header><h2><%=titulo %></h2></header>
<form action="<%=Constantes.SERVLET_PROFESOR%>" method="post">
	<input type="hidden" name="<%=Constantes.PAR_CODIGO %>"  id="<%=Constantes.PAR_CODIGO %>" value="value="<%=profesor.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input value="<%=profesor.getNombre()%>"type="text" placeholder="Introduzca el nombre" name="<%=Constantes.PAR_NOMBRE %>"  id="<%=Constantes.PAR_NOMBRE %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
			<input value="<%=profesor.getApellidos()%>" type="text" placeholder="Introduzca el apellido" name="<%=Constantes.PAR_APELLIDOS %>"  id="<%=Constantes.PAR_APELLIDOS %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_DIRECCION %>">Dirección:</label>
			<input value="<%=profesor.getDireccion()%>" type="text" placeholder="Introduzca la direccion" name="<%=Constantes.PAR_DIRECCION %>"  id="<%=Constantes.PAR_DIRECCION %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_DNI %>">DNI:</label>
			<input value="<%=profesor.getDni() %>" type="text" placeholder="Introduzca el DNI" name="<%=Constantes.PAR_DNI %>"  id="<%=Constantes.PAR_DNI %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
			<input value="<%=profesor.getEmail() %>" type="email" placeholder="Introduzca el email" name="<%=Constantes.PAR_EMAIL %>"  id="<%=Constantes.PAR_EMAIL %>" >
		</div>
		<div>
		<%
				String date="";
				GregorianCalendar gc=new GregorianCalendar();
				gc.setTime(profesor.getfNacimiento());
				date=gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
			%>
		
			<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de nacimiento:</label>
			<input value="<%=date %>" type="text" placeholder="Introduzca la fecha de nacimiento" name="<%=Constantes.PAR_FNACIMIENTO%>"  id="<%=Constantes.PAR_FNACIMIENTO %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_NSS %>">NSS:</label>
			<input value="<%=profesor.getnSS()%>" type="text" placeholder="Introduzca el NSS" name="<%=Constantes.PAR_NSS %>"  id="<%=Constantes.PAR_NSS %>" >
		</div>
		<input type="submit" value="Enviar"/>
</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>