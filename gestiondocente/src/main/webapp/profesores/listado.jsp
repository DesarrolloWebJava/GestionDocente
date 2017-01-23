<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
		<%
		/*Esto es un Scriptlet*/
		/*
		 * Recogemos el atributo de la request
		 * Hacemos el casting porque es un objeto que queremos meter en una lista
		 */
		 Map<Integer,Profesor> profesores = (Map<Integer,Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
		//Vamos a hacer el resto de operaciones de CRUD en el SERVLET
		%>
		<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>" >Crear Profesor</a>
		<%
		//Recorremos la lista con un Entry porque es un Map
		for (Map.Entry<Integer,Profesor> entry: profesores.entrySet()){
			//Boton delete
			String btn_delete = "<a href='"+Constantes.SERVLET_PROFESOR+"?"
			+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO
			+"="+entry.getValue().getCodigo()+"'>Borrar </a>";
			//Boton editar
			String btn_editar = "<a href='"+Constantes.SERVLET_PROFESOR+"?"
			+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO
			+"="+entry.getValue().getCodigo()+"'>Editar </a>";
			//Mostramos
			out.println("<p>"+entry.getValue().toString()+" "+btn_editar+" "+ btn_delete + "</p>");
		}
		%>
	</main>
	<%@ include file="../includes/footer.html" %>
</body>
</html>