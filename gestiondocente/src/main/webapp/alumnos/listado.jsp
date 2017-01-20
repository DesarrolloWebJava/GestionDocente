<!-- Importación de clases. -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>

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
				(Previamente asignado en AlumnoServlet).*/
			List<Alumno> alumnos = 
			                (List<Alumno>) request.getAttribute("listado-alumnos");
		%>
			<!-- Enlace al Servlet para crear el alumno. -->
			<a href="<%= Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE%>">
				Crear Profesor
			</a>
		
		<%
		/* Se recorre la lista de alumnos recogida del request.*/
		for(Alumno alumno:alumnos){
			/* Se imprime el alumno en la página.*/
			out.println("<p>" + alumno.toString() + "</p>");	
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