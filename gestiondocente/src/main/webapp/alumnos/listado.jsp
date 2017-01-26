<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>	
	
		<%
			//Dentro de estas tags, esto es puro Java en un Scriplet.
			//Aquí TENEMOS ACCESSO AL REQUEST Y RESPONSE
			// * Recogemos el atributo de la request
//USAMOS MEJOR JSTL...			List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
		//AÑADIMOS FUNCIONALIDAD:
			//- CRUD
			//- Contamos con un formulario que recoja datos en un archivo jsp: alumno.jsp
			//--> llamamos al SERVLET, que es el controlador, para acceder a ese archivo.
		%>
		<!-- Esto es una petición get. LA tengo que diferenciar entre la que crea alumno, y la que getAll
		todos los alumnos. ¿Cómo? CON PARÁMETROS!!! -->
		<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear alumno</a>
	
	<!-- Algo falla en el sg cód... 
		<c:forEach var="alumno" items="${Constantes.ATT_LISTADO_ALUMNOS}">
			<div>
				${alumno.nombre} ${alumno.apellidos} 
				<a href=""> Editar </a> 
				<a href=""> Borrar </a>
			</div>
		</c:forEach>
	-->
	<c:forEach items="${requestScope.listadoAlumnos}" var="alumno"> <!-- var hace ref a un obj, el compilador 
																	no sabe cuál es pero va a intentar acceder al toString().
																	Si la clases NO está serializada toString muestra un código por
																	defecto ininteligible. -->
    	<div> ${alumno} </div> <!-- por defecto intenta acceder al método toString(): por eso las clases pojo MUST HAVE TOSTRING -->
	</c:forEach>
		
		<ul>

<!-- Lo siguiente era el recorrido de acuerdo con el obj list, pero lo sustituimos con el EL anterior. -->
		<%	
/*		for(Alumno alumno: alumnos){
			String btn_delete = "<a href='"
					+Constantes.SERVLET_ALUMNO
					+"?"
					+Constantes.PAR_OPERACION
					+"="
					+Constantes.OP_DELETE
					+"&"
					+Constantes.PAR_CODIGO
					+"="
					+alumno.getCodigo()
					+"'> Borrar </a>";
				//CÓMO IMPRIMIR HTML:
				out.println("<li>"
							+alumno.toString()
							+"<a href='"
							+Constantes.SERVLET_ALUMNO
							+"?"
							+Constantes.PAR_OPERACION
							+"="
							+Constantes.OP_UPDATE
							+"&"
							+Constantes.PAR_CODIGO
							+"="
							+alumno.getCodigo()
							+"'>  Editar  </a>" + btn_delete
							+"</li>");
			}
*/		%>
		</ul>
		
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>