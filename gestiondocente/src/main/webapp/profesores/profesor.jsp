<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<%
	String titulo = "";
	Profesor profesor = (Profesor)request.getAttribute(Constantes.ATT_PROFESOR);

	if (profesor == null){
		titulo = "Crear Profesor";
		profesor = new Profesor();
		
	}else{
		titulo ="Editar Profesor";
		
	}

%>

<main>
<header><h2><%=titulo%></h2></header>

		<form action="<%=Constantes.SERVLET_PROFESOR%>" method="post">
			<div>
				<input type="hidden" name ="<%=Constantes.PAR_CODIGO%>" id="<%=Constantes.PAR_CODIGO%>" value="<%=profesor.getCodigo()%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
				<input value ="<%=profesor.getNombre()%>" type="text" placeholder = "Introduzca el nombre ..." name ="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_APELLIDOS%>">Apellidos:</label>
				<input value = "<%=profesor.getApellidos()%>" type ="text" placeholder = "Introduzac los apellidos ..." name = "<%=Constantes.PAR_APELLIDOS%>" id ="<%=Constantes.PAR_APELLIDOS%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_DNI%>">DNI:</label>
				<input value = "<%=profesor.getDni()%>" type ="text" placeholder = "Introduzac el DNI ..." name = "<%=Constantes.PAR_DNI%>" id ="<%=Constantes.PAR_DNI%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_EMAIL%>">Email:</label>
				<input value = "<%=profesor.getEmail()%>" type ="email" placeholder = "Introduzac el E-mail ..." name = "<%=Constantes.PAR_EMAIL%>" id ="<%=Constantes.PAR_EMAIL%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_DIRECCION%>">Direccion:</label>
				<input value = "<%=profesor.getDireccion()%>" type ="text" placeholder = "Introduzac la direccion ..." name = "<%=Constantes.PAR_DIRECCION%>" id ="<%=Constantes.PAR_DIRECCION%>">
			</div>
			<div>
				<%
				String date = "";
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(profesor.getfNacimiento());
				date =gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
				%>
				<label for ="<%=Constantes.PAR_FNACIMIENTO%>">Fecha Nacimiento:</label>
				<input value = "<%=date%>" type ="text" placeholder = "Introduzac la fecha de nacimiento ..." name = "<%=Constantes.PAR_FNACIMIENTO%>" id ="<%=Constantes.PAR_FNACIMIENTO%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_NSS%>">Numero Seguridad Social:</label>
				<input value = "<%=profesor.getnSS()%>" type ="text" placeholder = "Introduzca el numero de la seguridad social ..." name = "<%=Constantes.PAR_NSS%>" id ="<%=Constantes.PAR_NSS%>">
			</div>

			<input type ="submit" value ="Enviar">
		</form>
	</main>
	<%@include file = "../includes/footer.html"%>
</body>
</html>