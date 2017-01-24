<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
	String titulo="";
	Curso curso=(Curso)request.getAttribute(Constantes.ATT_CURSO);
	if(curso==null){
		titulo="Crear Curso";
		curso=new Curso();
	}else{
		titulo="Actualizar Curso";
	}

%>
<main>
<header><h2><%=titulo %></h2></header>
<form action="<%=Constantes.SERVLET_CURSO %>" method="post">
<input type="hidden" name="<%=Constantes.PAR_CODIGO %>"  id="<%=Constantes.PAR_CODIGO %>" value="<%=curso.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input value="<%=curso.getNombre()%>" type="text" placeholder="Introduzca el nombre" name="<%=Constantes.PAR_NOMBRE %>"  id="<%=Constantes.PAR_NOMBRE %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_DURACION %>">Duracion</label>
			<input value="<%=curso.getDuracion()%>" type="number" placeholder="Introduzca la duracion" name="<%=Constantes.PAR_DURACION%>" id="<%=Constantes.PAR_DURACION%>">
		</div>
			<%
				String date="";
				GregorianCalendar gc=new GregorianCalendar();
				gc.setTime(curso.getfInicio());
				date=gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
			%>
		<div>
			<label for="<%=Constantes.PAR_FINI%>">Fecha Inicio</label>
			<input value="<%=date%>" type="text" placeholder="Introduzca la fecha de inicio" name="<%=Constantes.PAR_FINI%>" id="<%=Constantes.PAR_FINI%>">
		</div>
		<div>
		<%	
				String date2="";
				GregorianCalendar gc2=new GregorianCalendar();
				gc2.setTime(curso.getfFin());
				date2=gc2.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc2.get(GregorianCalendar.MONTH)+1)+"/"+gc2.get(GregorianCalendar.YEAR);	
			%>
			<label for="<%=Constantes.PAR_FFIN%>">Fecha Fin</label>
			<input value="<%=date2%>" type="text" placeholder="Introduzca la fecha de fin" name="<%=Constantes.PAR_FFIN%>" id="<%=Constantes.PAR_FFIN%>">
		</div>
		<input type="submit" value="Enviar"/>
</form>
</main>
</body>
</html>