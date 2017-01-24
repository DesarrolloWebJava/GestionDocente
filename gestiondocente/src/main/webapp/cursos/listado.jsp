<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="../includes/header.jsp"></jsp:include>
    <main>
    	<%
    	//SCRIPTLET
    	//Pasa la lista como atributo del request y hace cast a Lista de cursos
    	List<Curso> cursos = (List<Curso>)request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
    	%>
    	<!-- Enlace al servlet que le pasa como parametro la operacion -->
    	<a href="<%=Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Curso </a>
    	<%
    		for(Curso curso: cursos){
    			//Boton editar
    			String btn_editar = "<a href='"+Constantes.SERVLET_CURSO+"?"+
    					Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+
    					"="+curso.getCodigo()+"'>Editar</a>";
    			//Boton delete
    			String btn_delete = "<a href='"+Constantes.SERVLET_CURSO+"?"
    					+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="
    					+curso.getCodigo()+"'>Borrar</a>";
    			
 				//Mostramos
 				out.println("<p>"+curso.toString()+" "+btn_editar+" "+ btn_delete + "</p>");
    		}
    	%>
    </main>
    <%@ include file="../includes/footer.html" %>
</body>
</html>