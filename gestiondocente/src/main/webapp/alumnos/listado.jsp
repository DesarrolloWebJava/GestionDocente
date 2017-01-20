<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
		<%
			//Dentro de estas tags, esto es puro Java en un Scriplet.
			//Aquí TENEMOS ACCESSO AL REQUEST Y RESPONSE
			// * Recogemos el atributo de la request
			List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
		//AÑADIMOS FUNCIONALIDAD:
			//- CRUD
			//- Contamos con un formulario que recoja datos en un archivo jsp: alumno.jsp
			//--> llamamos al SERVLET, que es el controlador, para acceder a ese archivo.
		%>
		
		<!-- Esto es una petición get. LA tengo que diferenciar entre la que crea alumno, y la que getAll
		todos los alumnos. ¿Cómo? CON PARÁMETROS!!! -->
		<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear alumno.</a>
		<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_UPDATE %>">Actualizar alumno.</a>
		<ul>
		<%	
		for(Alumno alumno: alumnos){
				//CÓMO IMPRIMIR HTML:
				out.println("<li>"+alumno.toString()+"</li>");
			}
		%>
		</ul>
		
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>