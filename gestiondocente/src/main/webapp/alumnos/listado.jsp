<!-- Importación de clases. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

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
			                (List<Alumno>) request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
		%>
			<!-- Enlace al Servlet para crear el alumno. -->
			<a href="<%= Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE%>">
				Crear Alumno
			</a>
		
		<%
		/* Se recorre la lista de alumnos recogida del request.*/
		for(Alumno alumno:alumnos){
			/* Se declara en enlace para el borrado.*/
			String btnDelete = "<a href ='" + 
									Constantes.SERVLET_ALUMNO + "?" + 
                                   	Constantes.PAR_OPERACION + "=" + 
																	Constantes.OP_DELETE + "&" +
								   	Constantes.PAR_CODIGO + "=" +  alumno.getCodigo()+"'>" +
									"Borrar</a>";
											                 
			/* Se declara en enlace para el modificado.*/
			String btnUpdate = "<a href ='" + 
									Constantes.SERVLET_ALUMNO + "?" + 
                                   	Constantes.PAR_OPERACION + "=" + 
																	Constantes.OP_UPDATE + "&" +
                                   	Constantes.PAR_CODIGO+"="+ alumno.getCodigo()+"'>" +
            					   	"Modificar</a>";
                                   
			/* Se imprime el alumno en la página con los botones modificar y borrar.*/
			out.println("<p>" + alumno.toString() + " " + 
			                                           btnUpdate + " " + btnDelete + "</p>" );
		 }
		 %>
		 
		<!-- Parrafo con  --> 
		<p>Listado con JSTL.</p>
		
		<!-- Se recorre la lista utilizando JSTL. -->
		<c:forEach  items="${listadoAlumnos}" var="alumno">
			<!-- Al recoger los atributos de una clase el JSTL llama directamente al
			     getter,por lo que este ha de seguir obligatoriamente la nomenclatura 
			     camello para que lo encuentre. -->
			 
		
			<div>
				<p>${alumno.nombre} ${alumno.apellidos}  <c:out value="${btnDelete}"/></p>
			</div>	
		</c:forEach>

	</main>
	<!-- Include del Pie de la página.	
	  Se llama al include estetico 
	  (no posee codigo java que cambie en función de alguna variable).
	  Contiene las etiquetas <header> y </header>.-->
	<%@ include file ="../includes/footer.html" %>		
</body>
</html>