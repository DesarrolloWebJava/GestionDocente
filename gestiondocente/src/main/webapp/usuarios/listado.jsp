<!-- Importación de clases. -->
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- Se importa la libreria de FMT 
     (Gestión de las claves de los ficheros de propiedades) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Importación de librerias java. -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Se asigna el idioma de la sesion,
     en caso de no existir se asigna castellano(es_ES). -->
<c:set var="language" 
       value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" 
       scope="session" />    
<!-- Se indica el formateo del fichero de propiedades al lenguaje de la página,
     es decir se le indica de que fichero ha de recogerlo.-->
<fmt:setLocale value="${language}" />
<!-- Se indica la ruta de los ficheros de propiedades de la internacionalizacion. -->
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" /> 


	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
     <jsp:include page="../includes/header.jsp"/>
	<main>
		<%
		/* Se recoge el atributo que contiene la lista de alumnos.
			(Previamente asignado en ProfesorServlet).*/
			/* Se recoge el atributo que contiene la lista de alumnos.
			(Previamente asignado en AlumnoServlet).*/
		List<Persona> personas = 
		                (List<Persona>) request.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
		
		/* Se recorre la lista de profesores recogida del request.*/
		for(Persona persona:personas){
			
			/* Se declara la variable para definir el boton de expulsar.*/
			String btnKick = "<a href ='" + 
					Constantes.SERVLET_USUARIO + "?" + 
                 	Constantes.PAR_OPERACION + "=" + 
													Constantes.OP_EXPULSAR + "&" +
				   	Constantes.PAR_SESSIONID + "=" +  persona.getsesionId()+ "'>" +
				   			"Expulsar</a>";
				
	                                   
				/* Se imprime el usaurio en la página con el boton expulsar. */
				out.println("<p>" + persona.toString() + " " + btnKick + "</p>" );
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