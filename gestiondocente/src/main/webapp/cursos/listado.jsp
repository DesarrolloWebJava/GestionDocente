<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../includes/header.jsp"/>


<header><h1>Pagina listado de Cursos</h1></header>

<main>
	<%
		List<Curso> cursos = (List<Curso>) request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
	%>
	<a href = "<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Curso</a> 
	<%
	
	for (Curso c : cursos)
	{
	
		String btn_update = "<a href = '"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+c.getCodigo()+"'>Modificar Curso</a>";
		String btn_delete = "<a href = '"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+c.getCodigo()+"'>Eliminar Curso</a>";
		out.println("<li>"+c.toString()+" "+btn_update+" "+btn_delete+"</li>");
	}
	
	%>

</main>
<%@ include file = "../includes/footer.html" %>
</body>
</html>