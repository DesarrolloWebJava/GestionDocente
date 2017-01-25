<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<% 
	Curso curso=(Curso)request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
	String titulo="";
	String titulo_boton="";
	
	if(curso == null){
		titulo ="Crear Curso";
		titulo_boton = "Guardar Curso";
		//asi no me da nulo y no casca, instancio un nuevo objeto curso con value = -1
		curso = new Curso();
	}
	else{
		titulo ="Actualizar Curso";
		titulo_boton="Actualizar Curso";
	}	
%>
<main>

	<h1><%=titulo %></h1> 
	
	<form role="form" action="<%=Constantes.SERVLET_CURSO%>" method="post">
	<div class="form-group">
		<input type="hidden" class="form-control" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO %>" value="<%=curso.getCodigo()%>">
	</div>	
	<div class="form-group">
			<label for="<%=Constantes.PAR_NOMBRE_CURSO %>">Nombre</label>
			<input type="text" class="form-control" id="<%=Constantes.PAR_NOMBRE_CURSO %>" name="<%=Constantes.PAR_NOMBRE_CURSO %>"
				   value="<%=curso.getNombreCurso()%>" placeholder="Introduce el nombre del curso" >
			
	</div>
	<div class="form-group">
			<label for="<%=Constantes.PAR_DURACION %>">Duración del curso</label>
			<input type="text" class="form-control" id="<%=Constantes.PAR_DURACION %>" name="<%=Constantes.PAR_DURACION %>"
				   value="<%=curso.getDuracion()%>" placeholder="Introduce la duración del curso">
	</div>
	<%
		String dateInicio = "";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(curso.getFechaInicio());
		dateInicio = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
	%>
	<div class="form-group">
			<label for="<%=Constantes.PAR_FECHA_INICIO %>">Fecha de Inicio</label>
			<input type="text" class="form-control" id="<%=Constantes.PAR_FECHA_INICIO %>" name="<%=Constantes.PAR_FECHA_INICIO %>" 
				value="<%=dateInicio%>" placeholder="Introduce la fecha de inicio (dd/MM/yyyy)">
	</div>
	<%
		String dateFin = "";
		gc = new GregorianCalendar();
		gc.setTime(curso.getFechaFin());
		dateFin = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
	%>
	<div class="form-group">
			<label for="<%=Constantes.PAR_FECHA_FIN %>">Fecha de Fin</label>
			<input type="text" class="form-control" id="<%=Constantes.PAR_FECHA_FIN %>" name="<%=Constantes.PAR_FECHA_FIN%>" 
				   value="<%=dateFin%>" placeholder="Introduce la fecha de fin (dd/M/yyyy)">
	</div>
		<input type="submit" value="enviar">
	</form>	
</main>
	<%@ include file="../includes/footer.html" %>
</html>