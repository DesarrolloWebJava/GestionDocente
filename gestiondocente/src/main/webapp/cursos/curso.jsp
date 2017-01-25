<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main>
<% 
	String titulo = "";
	Curso curso = (Curso)request.getAttribute(Constantes.ATT_CURSO);
	if(curso==null){ //create
			titulo = "Crear Curso";
			curso = new Curso();
	}else{//update
		titulo = "Actualizar Curso";
	}

%>

	<header><h2><%=titulo %></h2> </header>	
	<form action="<%=Constantes.SERVLET_CURSO%>" method="post">
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO%>" value="<%=curso.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE_CURSO%>">Nombre:</label>
			<input value="<%=curso.getNombreCurso() %>" type="text" placeholder="Introduzca el nombre del curso.." name="<%=Constantes.PAR_NOMBRE_CURSO %>" id="<%=Constantes.PAR_NOMBRE_CURSO %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DURACION%>">Duración:</label>
			<input value="<%=curso.getDuracion() %>" type="text" placeholder="Introduzca la duracion del curso.." name="<%=Constantes.PAR_DURACION %>" id="<%=Constantes.PAR_DURACION %>">
		</div>
		<%
		String dateInicio = "";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(curso.getFechaInicio());
		dateInicio = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
		%>
		<div>
			<label for="<%=Constantes.PAR_FECHA_INICIO%>">Fecha de inicio:</label>
			<input value="<%=dateInicio%>" type="text" placeholder="Introduzca la fecha de inicio.." name="<%=Constantes.PAR_FECHA_INICIO %>" id="<%=Constantes.PAR_FECHA_INICIO %>">
		</div>
		<%
		String dateFin = "";
		gc = new GregorianCalendar();
		gc.setTime(curso.getFechaFin());
		dateFin = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
		%>
		<div>
			<label for="<%=Constantes.PAR_FECHA_FIN%>">Fecha de fin:</label>
			<input value="<%=dateFin %>" type="text" placeholder="Introduzca la fecha de fin.." name="<%=Constantes.PAR_FECHA_FIN %>" id="<%=Constantes.PAR_FECHA_FIN %>">
		</div>
		<input type="submit" value="enviar">
	</form>	
</main>
	<%@ include file="../includes/footer.html" %>
</html>