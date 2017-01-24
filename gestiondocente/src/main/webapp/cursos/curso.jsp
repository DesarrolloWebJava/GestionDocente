<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<%
	String titulo = "";
	Curso curso = (Curso)request.getAttribute(Constantes.ATT_CURSO);
	if(curso==null){//create
		titulo = "Crear Curso";
		curso = new Curso();
	}else{//update
		titulo = "Actualizar Curso";
	
	}
%>
<main>
	<header><h2><%=titulo %></h2></header>
	<form action="<%=Constantes.SERVLET_CURSO %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO %>" value="<%=curso.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input value="<%=curso.getNombre()%>" type="text" required placeholder="Indroduzca el nombre.." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>">
		</div>
		
		<div>
			<label for="<%=Constantes.PAR_DURACION %>">Duracion:</label>
			<input value="<%=curso.getDuracion()%>" type="text" required placeholder="Indroduzca la duracion" name="<%=Constantes.PAR_DURACION %>" id="<%=Constantes.PAR_DURACION %>">
		</div>
		
		<div>
			<label for="<%=Constantes.PAR_FECHAINICIO %>">Fecha inicio:</label>
			<input type="text" required placeholder="Introduzca la fecha de inicio" name="<%=Constantes.PAR_FECHAINICIO %>" id="<%=Constantes.PAR_FECHAINICIO %>">
		</div>
		
		<div>
			<label for="<%=Constantes.PAR_FECHAFIN %>">Fecha fin:</label>
			<input type="text" required placeholder="Introduzca la fecha de fin" name="<%=Constantes.PAR_FECHAFIN %>" id="<%=Constantes.PAR_FECHAFIN %>">
		</div>
		<input type="submit" value="Enviar">
	</form>
</main>
<%@ include file="../includes/footer.html" %>