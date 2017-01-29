<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"/>
<div class="container">
	<h1>Página Listado Cursos</h1>
	<%
		List<Curso> cursos =(List<Curso>) request.getAttribute(Constantes.ATT_CURSO); 	
		//cursos.sort(null);
		//mas generico el¡s el objeto collecentions, es lo mismo q lo de arriba
		//Collection.sort(cursos);
		//ordenar al reves
		//Collections.reverse(cursos);
	%>
	<br>
	<br>
	<a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE %>" class="" role="button"> Crear Curso </a>
	<br>
	<br>
	<div>
		<%	
			for(Curso curso : cursos){
				String dateInicio="";
		    	GregorianCalendar gc = new GregorianCalendar();
		    	gc.setTime(curso.getFinicio());
		    	dateInicio = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
		    	String dateFin="";
		    	gc = new GregorianCalendar();
		    	gc.setTime(curso.getFFinal());
		    	dateFin = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
		    	
		    	out.println("<p>"+curso.toString()+"  "+"Fecha Inicio:"+dateInicio+" "+"Fecha de Fin:"+dateFin
				+"<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"' class='' role='button' >Editar</a>"+
				"<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"' class='' role='button' >Borrado</a>"+"</p>");	
			}
			%>
			</a>
		<% 
		%>
	</div>
</div>

<%@ include file="../includes/footer.html" %>
</body>
</html>