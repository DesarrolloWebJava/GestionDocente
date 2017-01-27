<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>    
Página Listado Profesores

<main>
<a href="<%= Constantes.SERVLET_PROFESOR %>?<%= Constantes.PAR_OPERACION %>=<%= Constantes.OP_CREATE %>">Crear Profesor</a>
<ul>
<%
Map<Integer,Profesor> profesores=(Map<Integer,Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
for(Map.Entry<Integer,Profesor> entrada:profesores.entrySet()){
	String btn_update="<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+entrada.getValue().getCodigo()+"'>Editar </>";
	String btn_delete="<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+entrada.getValue().getCodigo()+"'>Eliminar</a>";
	out.println("<li>"+entrada.getValue().toString()+" "+btn_update+" "+btn_delete+"</li>"); 

}
%>
</ul>
</main>
<%@ include file="../includes/footer.html"%>
</body>
</html>