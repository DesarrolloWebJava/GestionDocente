<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<%
	String titulo = "";
	Curso curso = (Curso)request.getAttribute(Constantes.ATT_CURSO);

	if (curso == null){
		titulo = "Crear Curso";
		curso = new Curso();
		
	}else{
		titulo ="Editar Curso";
		
	}

%>

<main>
<header><h2><%=titulo%></h2></header>
	<form action=<%=Constantes.SERVLET_CURSO%> method = "Post">
		<div>
				<input type="hidden" name ="<%=Constantes.PAR_CODIGO%>" id="<%=Constantes.PAR_CODIGO%>" value="<%=curso.getCodigo()%>">
		</div>
		<div>
				<label for ="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
				<input value ="<%=curso.getNombre()%>"  type="text" placeholder = "Introduzca el nombre ..." name ="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>">
		</div>
		<div>
				<label for ="<%=Constantes.PAR_DURACION%>">Duracion:</label>
				<input value ="<%=curso.getDuracion()%>" type="text" placeholder = "Introduzca la duracion del curso ..."  name ="<%=Constantes.PAR_DURACION%>" id="<%=Constantes.PAR_DURACION%>">
		</div>
		<div>
			<%
				String date;
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(curso.getfInicio());
				date =gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
			
			%>	
			<label for ="<%=Constantes.PAR_FINICIO%>">Fecha Inicio:</label>
			<input value ="<%=date%>" type="text" placeholder = "Introduzca la fecha de inicio ..."  name ="<%=Constantes.PAR_FINICIO%>" id="<%=Constantes.PAR_FINICIO%>">	
		</div>
		<div>
			<%
				String date1;
				GregorianCalendar gc1 = new GregorianCalendar();
				gc1.setTime(curso.getfFinal());
				date1 =gc1.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc1.get(GregorianCalendar.MONTH)+1)+"/"+gc1.get(GregorianCalendar.YEAR);
			
			%>	
			<label for ="<%=Constantes.PAR_FFINAL%>">Fecha Final:</label>
			<input value ="<%=date1%>" type="text" placeholder = "Introduzca la fecha de finalizacion ..."  name ="<%=Constantes.PAR_FFINAL%>" id="<%=Constantes.PAR_FFINAL%>">	
		</div>
	
	<input type ="submit" value ="Enviar">
	</form>


</main>
<%@include file = "../includes/footer.html"%>
</body>
</html>