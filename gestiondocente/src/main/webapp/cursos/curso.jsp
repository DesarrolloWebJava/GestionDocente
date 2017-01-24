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
		titulo = "Crear Nuevo Curso";
		curso = new Curso();
	}else{//update
		titulo = "Actualizar Cursoooo";
	
	}
%>
<main>
	<header><h2><%=titulo %></h2></header> 
	<form action="<%=Constantes.SERVLET_CURSO%>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO %>" value="<%=curso.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
			<input value="<%=curso.getNombre()%>" type="text" placeholder="Indroduzca el nombre.." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>">
		</div>
	
		<div>
			<%
			String date = "";
			GregorianCalendar gc = new GregorianCalendar();
			if(curso.getfInicio()!=null){
				gc.setTime(curso.getfInicio());	
			}
		
			date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+gc.get(GregorianCalendar.MONTH)+"/"+gc.get(GregorianCalendar.YEAR);
			%>
			<label for="<%=Constantes.PAR_FINICIO %>">Fecha Inico:</label>
			<input value="<%=date%>" type="text" placeholder="Introduzca la fecha de inicio del curso" name="<%=Constantes.PAR_FINICIO %>" id="<%=Constantes.PAR_FINICIO %>">
		</div>
		
			<div>
			<%
			String date2 = "";
			GregorianCalendar gc2 = new GregorianCalendar();
			if(curso.getfFin()!=null){
				gc2.setTime(curso.getfFin());	
			}
		
			date2 = gc2.get(GregorianCalendar.DAY_OF_MONTH)+"/"+gc2.get(GregorianCalendar.MONTH)+"/"+gc2.get(GregorianCalendar.YEAR);
			%>
			<label for="<%=Constantes.PAR_FFIN %>">Fecha Fin:</label>
			<input value="<%=date2%>" type="text" placeholder="Introduzca la fecha de fin de curso" name="<%=Constantes.PAR_FFIN %>" id="<%=Constantes.PAR_FFIN %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DURACION %>">Duracion:</label>
			<input value="<%=curso.getDuracion()%>" type="number" placeholder="Introduzca la duración en horas" name="<%=Constantes.PAR_DURACION %>" id="<%=Constantes.PAR_DURACION %>">
		</div>
	
		<input type="submit" value="Enviar">
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>