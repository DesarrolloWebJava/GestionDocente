<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../includes/header.jsp"/>
	<header><h1>Pagina listado de Alumnos</h1></header>
	
	<main>
	<%
	//esto es un script de java
	//List<Alumno> alumnos= (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
	
	//CRUD
	%>
	<a href = "<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Alumno</a>
	<c:forEach var ="alumno" items="${listadoAlumnos}">
		<div>
		${alumno.nombre} ${alumno.apellidos} ${alumno.dni} ${alumno.email} ${alumno.direccion} ${alumno.nHermanos} <a href="">Editar</a> <a href="">Borrar</a>
		</div>
	</c:forEach>
	<%	
	/*
	for (Alumno alumno :alumnos){
		String btn_delete ="<a href='"+Constantes.SERVLET_ALUMNO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+alumno.getCodigo()+"'>Borrar</a>";	
		out.println(""+alumno.toString()+"<a href ='"+Constantes.SERVLET_ALUMNO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+alumno.getCodigo()+"'>Editar</a>"+btn_delete);	
	}
	
	*/
	%>
	
	</main>
	<%@ include file = "../includes/footer.html" %>
</body>
</html>