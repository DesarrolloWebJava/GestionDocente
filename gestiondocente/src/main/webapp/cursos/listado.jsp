<%@page import="java.util.Collection"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp" />
<div class="container">
	<h1>Página Listado Cursos</h1>
	
	<% /* Esto es un scriplet */
	// recogemos el atributo de la request
	List<Curso> cursos =(List<Curso>) request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
	//cursos.sort(null);
	//CRUD
	%>
	
	<a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>" class="" role="button"> Crear Curso</a> <!-- petición get  -->
	<div>
		<%
		for(Curso curso : cursos){
			String dateInicio="";
	    	GregorianCalendar gc = new GregorianCalendar();
	    	
	    	gc.setTime(curso.getFechaInicio());
	    	dateInicio = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
	    	String dateFin="";
	    	gc = new GregorianCalendar();
	    	gc.setTime(curso.getFechaFin());
	    	dateFin = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
	    	
	    	out.println("<p>"+curso.toString()+"  "+"Fecha Inicio:"+dateInicio+" "+"Fecha de Fin:"+dateFin
			+"<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"' class='' role='button' >Editar</a>"+
			"<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"' class='' role='button' >Borrado</a>"+"</p>");	
		}
		%>
	</div>
</div>
<%@ include file="../includes/footer.html" %>
</html>