<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
	<main>
	<%
	/*Esto es un scriplet */
	
	//recogemos el atributo de la request
	//List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
	%>
	<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Alumno</a>

	<c:forEach items="${listadoAlumno}" var="alumno">
    	<div> ${alumno.nombre} </div>
	</c:forEach>

	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>