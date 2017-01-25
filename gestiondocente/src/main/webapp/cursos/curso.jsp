<%@page import="java.util.GregorianCalendar"%>

<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
 String titulo = "";	
 Curso curso = (Curso)request.getAttribute(Constantes.ATT_CURSO);
 
 if(curso == null){
	 titulo = "Crear Curso";
	 curso= new Curso();
 }else{
	 titulo = "Actualizar Curso";
 }
 %>   
	<jsp:include page="../includes/header.jsp" />
		
		<main>
			<header><h2><%=titulo%></h2></header>
			<form action=<%=Constantes.SERVLET_CURSO%> method="post">
				<input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>" value="<%=curso.getCodigo()%>">
				<div>
					<label for="<%=Constantes.PAR_NOMBRE%>">*NOMBRE: </label>
					<input type="text" value="<%=curso.getNombre()%>" id="<%=Constantes.PAR_NOMBRE%>" name="<%=Constantes.PAR_NOMBRE%>" placeholder="Introduce el nombre..." required >
				</div>
				<div>
					<label for="<%=Constantes.PAR_DURACION%>">DURACION: </label>
					<input type="number" value="<%=curso.getDuracion()%>" id="<%=Constantes.PAR_DURACION%>" name="<%=Constantes.PAR_DURACION%>" placeholder="Introduce la duracion..." required >
				</div>
				<div>
				<%
				String dateini = "";
				GregorianCalendar gci = new GregorianCalendar();
				gci.setTime(curso.getFechaInicio());
				dateini = gci.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gci.get(GregorianCalendar.MONTH)+1)+"/"+gci.get(GregorianCalendar.YEAR);
				%>
					<label for="<%=Constantes.PAR_FECHAINICIO%>">NOMBRE: </label>
					<input type="text" value="<%=dateini%>" id="<%=Constantes.PAR_FECHAINICIO%>" name="<%=Constantes.PAR_FECHAINICIO%>" placeholder="Introduce la fecha de inicio..." required >
				</div>
				<div>
				<%
				String datefin = "";
				GregorianCalendar gcf = new GregorianCalendar();
				gcf.setTime(curso.getFechaFin());
				datefin = gcf.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gcf.get(GregorianCalendar.MONTH)+1)+"/"+gcf.get(GregorianCalendar.YEAR);
				%>
					<label for="<%=Constantes.PAR_FECHAFIN%>">NOMBRE: </label>
					<input type="text" value="<%=datefin%>" id="<%=Constantes.PAR_FECHAFIN%>" name="<%=Constantes.PAR_FECHAFIN%>" placeholder="Introduce la fecha fin..." required >
				</div>
				<input  type="submit" value="Enviar" />
			</form>
		</main>
		<%@ include file="../includes/footer.html" %>
	</body>
</html>