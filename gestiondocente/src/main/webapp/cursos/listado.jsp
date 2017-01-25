<!-- Importación de clases. -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
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
			List<Curso> cursos = 
			                (List<Curso>) request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
		%>
			<!-- Enlace al Servlet para crear el curso. -->
			<a href="<%= Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE%>">
				Crear Curso
			</a>
		
		<%
		/* Se recorre la lista de alumnos recogida del request.*/
		for(Curso curso:cursos){
			/* Se declara en enlace para el borrado.*/
			String btn_delete = "<a href ='" + 
									Constantes.SERVLET_CURSO + "?" + 
                                   	Constantes.PAR_OPERACION + "=" + 
																	Constantes.OP_DELETE + "&" +
								   	Constantes.PAR_CODIGO + "=" +  curso.getCodigo()+"'>" +
									"Borrar</a>";
											                 
			/* Se declara en enlace para el modificado.*/
			String btn_update = "<a href ='" + 
									Constantes.SERVLET_CURSO + "?" + 
                                   	Constantes.PAR_OPERACION + "=" + 
																	Constantes.OP_UPDATE + "&" +
                                   	Constantes.PAR_CODIGO+"="+ curso.getCodigo()+"'>" +
            					   	"Modificar</a>";
                                   
			/* Se imprime el alumno en la página con los botones modificar y borrar.*/
			out.println("<p>" + curso.toString() + " " + 
			                                           btn_update + " " + btn_delete + "</p>" );
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