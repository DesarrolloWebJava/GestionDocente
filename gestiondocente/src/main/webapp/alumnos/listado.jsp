<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
	<%
	/*Esto es un scriplet */
	
	//recogemos el atributo de la request
	List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
	//crud
	%>
	<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE %>">Crear Alumnos </a>
	<% 
	
	for(Alumno alumno: alumnos){
		//para el borrado
		String btn_delete="<a href='"+Constantes.SERVLET_ALUMNO+"?"+Constantes.PAR_OPERACION+"="+
		Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+alumno.getCodigo()+"'>Borrar</a>";
		//redireccion para la update
		out.println("<p>"+alumno.toString()+"</p>"+"<a href='"+Constantes.SERVLET_ALUMNO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+
		Constantes.PAR_CODIGO+"="+alumno.getCodigo()+"'>Editar</a>"+btn_delete);
	}
	%>
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>