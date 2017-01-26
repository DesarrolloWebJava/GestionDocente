<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
		<%-- <%
		/*Esto es un Scriptlet*/
		/*
		 * Recogemos el atributo de la request
		 * Hacemos el casting porque es un objeto que queremos meter en una lista
		 */
		List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
		%> --%>
		<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Alumno</a>
		<!-- El items es la lista. 
		alumno es cada elemento de la lista
		El foreach recorre cada elemento de la lista -->
		<c:forEach var="alumno" items="${listadoAlumnos}">
			<!-- Con esto saco el nombre del alumno. Hace un getter -->
			<div>
				${alumno.nombre} ${alumno.apellidos} <a href="">Editar</a><a href="">Borrar</a>
			</div>
		</c:forEach>
		
		<%-- <%
		 for(Alumno alumno: alumnos){
			//Boton delete
			String btn_delete = "<a href='"+Constantes.SERVLET_ALUMNO+"?"
			+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="
			+alumno.getCodigo()+"'>Borrar</a>";
			//Boton editar
			String btn_editar = "<a href='"+Constantes.SERVLET_ALUMNO+"?"+
					Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+
					"="+alumno.getCodigo()+"'>Editar</a>";
			//Mostramos
			out.println("<p>"+alumno.toString()+" "+btn_editar+" "+ btn_delete + "</p>");
			
		} 
		%> --%> 
	</main>
	<%@ include file="../includes/footer.html" %>
</body>
</html>