<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<body>
	<main>
	<%
	/* Esto es un scriplet*/
	
	//recogemos el atributo de la request
	
	Map<Integer, Profesor> profesores=(Map <Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	%>
	<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Profesor</a><br>
	<%
	for(Map.Entry<Integer, Profesor> entry: profesores.entrySet()){
		int codigo = entry.getKey();
		Profesor profesor = entry.getValue();
		String btn_delete = "<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+profesor.getCodigo()+"'>Borrar</a>";
		out.write(""+entry.getValue().toString()+"<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+profesor.getCodigo()+"'>Editar</a>"+btn_delete);
		%><br><%
	}
	%>
	</main>
 	<%@ include file="../includes/footer.html" %>
</body>
</html>