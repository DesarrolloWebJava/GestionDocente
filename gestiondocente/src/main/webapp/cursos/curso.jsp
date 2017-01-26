<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente - Crear Curso</title>
</head>
<jsp:include page="../includes/header.jsp"/>

<%
	String titulo ="";
	Curso curso = (Curso)request.getAttribute(Constantes.ATT_CURSO);
	if(curso==null){//es un create
		titulo="Crear Curso";	
		curso = new Curso();
		
	}
	else{
		titulo="Actualizar Curso";
	}

%>

<main>

<header><h2><%=titulo %></h2></header>


		<form action"<%=Constantes.SERVLET_CURSO %>" method="post"> 
		<input type="hidden" name="<%=Constantes.PAR_CODIGO%>" value="<%=curso.getCodigo()%>" id="<%=Constantes.PAR_CODIGO%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
			<input type="text" placeholder= "Introduzca el nombre del curso" name="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>" value="<%=curso.getNombre()%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DURACION%>">Duracion:</label>
			<input type="text" placeholder= "Introduzca la duracion del curso..." name="<%=Constantes.PAR_DURACION%>" id="<%=Constantes.PAR_DURACION%>" value="<%=curso.getDuracion()%>">
		</div>
		<div>
			<%
			String date = "";
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(curso.getfInicio());
			date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
			%>
			<label for="<%=Constantes.PAR_FINICIO%>">Fecha de Inicio:</label>
			<input type="text" placeholder= "Introduzca su fecha de inicio..." name="<%=Constantes.PAR_FINICIO%>" id="<%=Constantes.PAR_FINICIO%>" value="<%=date %>">
		</div>
		<div>
			<%
			String datefinal = "";
			GregorianCalendar gcfinal = new GregorianCalendar();
			gc.setTime(curso.getfFinal());
			datefinal = gcfinal.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gcfinal.get(GregorianCalendar.MONTH)+1)+"/"+gcfinal.get(GregorianCalendar.YEAR);
			%>
			<label for="<%=Constantes.PAR_FFINAL%>">Fecha de Final:</label>
			<input type="text" placeholder= "Introduzca su fecha de final..." name="<%=Constantes.PAR_FFINAL%>" id="<%=Constantes.PAR_FFINAL%>" value="<%=datefinal %>">
		</div>
		
		
		<input type="submit" value="Enviar"/>
		
	</form>
</main>
	<%@ include file="../includes/footer.html" %>
</body>
</html>