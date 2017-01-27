<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<%
	String titulo = "";
	Alumno alumno = (Alumno)request.getAttribute(Constantes.ATT_ALUMNO);

	if (alumno == null){
		titulo = "Crear Alumno";
		alumno = new Alumno();
		
	}else{
		titulo ="Editar Alumno";
		
	}

%>

<main>
<header><h2>Crear/Editar Alumno</h2></header>

		<form action="<%=Constantes.SERVLET_ALUMNO%>" method="post">
			<div>
				<input type="hidden" name ="<%=Constantes.PAR_CODIGO%>" id="<%=Constantes.PAR_CODIGO%>" value="${alumno.codigo}">
				<label for ="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
				<input value ="${alumno.nombre}" type="text" placeholder = "Introduzca el nombre ..." name ="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_APELLIDOS%>">Apellidos:</label>
				<input value ="${alumno.apellidos}" type ="text" placeholder = "Introduzca los apellidos ..." name = "<%=Constantes.PAR_APELLIDOS%>" id ="<%=Constantes.PAR_APELLIDOS%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_DNI%>">DNI:</label>
				<input value ="${alumno.dni}" type ="text" placeholder = "Introduzca el DNI ..." name = "<%=Constantes.PAR_DNI%>" id ="<%=Constantes.PAR_DNI%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_EMAIL%>">Email:</label>
				<input value ="${alumno.email}" type ="email" placeholder = "Introduzac el E-mail ..." name = "<%=Constantes.PAR_EMAIL%>" id ="<%=Constantes.PAR_EMAIL%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_DIRECCION%>">Direccion:</label>
				<input value ="${alumno.direccion}"type ="text" placeholder = "Introduzac la direccion ..." name = "<%=Constantes.PAR_DIRECCION%>" id ="<%=Constantes.PAR_DIRECCION%>">
			</div>
			<div>
				<%
				String date = "";
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(alumno.getfNacimiento());
				date =gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
				%>
				<label for ="<%=Constantes.PAR_FNACIMIENTO%>">Fecha Nacimiento:</label>
				<input value ="<%=date%>" type ="text" placeholder = "Introduzac la fecha de nacimiento ..." name = "<%=Constantes.PAR_FNACIMIENTO%>" id ="<%=Constantes.PAR_FNACIMIENTO%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_NHERMANOS%>">Numero Hermanos:</label>
				<input value ="${alumno.nHermanos}%>" type ="text" placeholder = "Introduzca el numero de hermanos ..." name = "<%=Constantes.PAR_NHERMANOS%>" id ="<%=Constantes.PAR_NHERMANOS%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_ACTIVO%>">Activo:</label>
				<%
				if (alumno.isActivo()){
				%>
				<select>
					<option selected value="1">Activo</option>
					<option value ="0">Desactivo</option>
				</select>
				<%
				}else{
				%>
				
				<select>
					<option value="1">Activo</option>
					<option selected value ="0">Desactivo</option>
				
				</select>
				<%
				}
				%>
			</div>
			<input type ="submit" value ="Enviar">
		</form>
	</main>
	<%@include file = "../includes/footer.html"%>
</body>
</html>