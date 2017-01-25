<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<%
	String titulo= ""; 
	String String_boton="";
	Curso curso= (Curso)request.getAttribute(Constantes.ATT_CURSO);

	if (curso==null){//create
		titulo = "Crear Curso";
		String_boton = "Crear Curso";
		curso = new Curso();
	}else{//update
		titulo= "Actualizar Curso";
		String_boton = "Actualizar Curso";
	}
%>
<main>
	<header><h2><%=titulo %></h2></header> 
	<form action="<%=Constantes.SERVLET_CURSO %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO %>" value="<%=curso.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input value="<%=curso.getNombre() %>" type="text" placeholder="Indroduzca el nombre.." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DURACION %>">Duracion:</label>
			<input value="<%=curso.getDuracion() %>" type="text" placeholder="Introduzca la duracion.." name="<%=Constantes.PAR_DURACION %>" id="<%=Constantes.PAR_DURACION %>">
		</div>
		<div>
			<%
				String dateInicio = "";
				GregorianCalendar gcI = new GregorianCalendar();
				gcI.setTime(curso.getfInicio());
				dateInicio = gcI.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gcI.get(GregorianCalendar.MONTH)+1)+"/"+gcI.get(GregorianCalendar.YEAR);
				
			%>
			<label for="<%=Constantes.PAR_FINICIO %>">Fecha Inicio:</label>
			<input value="<%=dateInicio%>" type="text" placeholder="Introduzca la fecha de inicio" name="<%=Constantes.PAR_FINICIO %>" id="<%=Constantes.PAR_FINICIO %>">
		</div>
		<div>
			<%
				String dateFin = "";
				GregorianCalendar gcF = new GregorianCalendar();
				gcF.setTime(curso.getfFin());
				dateFin = gcF.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gcF.get(GregorianCalendar.MONTH)+1)+"/"+gcF.get(GregorianCalendar.YEAR);
				
			%>
			<label for="<%=Constantes.PAR_FFIN %>">Fecha Fin:</label>
			<input value="<%=dateFin%>" type="text" placeholder="Introduzca la fecha de fin" name="<%=Constantes.PAR_FFIN %>" id="<%=Constantes.PAR_FFIN %>">
		</div>
	
		<input type="submit" value="<%=String_boton%>">
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>