<!-- Importación de clases. -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
    <jsp:include page="../includes/header.jsp"/>
	<main>
	<!-- ScriptLet que contiene codigo java. -->
		<%
			/* Se recoge el atributo que contiene la lista de alumnos.
				(Previamente asignado en ProfesorServlet).*/
			Map<Integer,Profesor> profesores = 
			        (Map<Integer,Profesor>) request.getAttribute("listado-profesores");
		%>
		
			<!-- Enlace al Servlet para crear el profesor. -->
			<a href="<%= Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE%>">
				Crear Profesor
			</a>
		<%
			/* Se recorre la lista de profesores recogida del request.*/
			for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
				int codigo = entry.getKey();
				Profesor profesor = entry.getValue();
			    /* Se imprime el profesor en la página.*/
				out.println("<p>" + profesor.toString() + "</p>");	
			}			
		%>
	</main>
	<!-- Include del Pie de la página.	
	  Se llama al include estetico 
	  (no posee codigo java que cambie en función de alguna variable).
	  Contiene las etiquetas <header> y </header>.-->
	<%@ include file ="../includes/footer.html" %>	
</body>
</html>