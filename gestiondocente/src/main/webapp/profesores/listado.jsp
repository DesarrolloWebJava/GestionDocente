<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.service.ProfesorServiceImp"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.service.ProfesorService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
		<%
		//List<Alumno> alumnos = (List<Alumno>)request.getAttribute("listado-alumnos");
		
		//ProfesorService profesorService = new ProfesorServiceImp();
		//Map<Integer, Profesor> profesores = profesorService.getAll();
		Map<Integer, Profesor> profesores = (Map<Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
		String btnUpdate;
		String btnDelete;
%>

	<div>
			<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear profesor</a>
	</div>
	
	<ul>
	<%
		for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
			int codigo = entry.getKey();
			Profesor profesor = entry.getValue();
			btnUpdate = "<a href='" 
					+ Constantes.SERVLET_PROFESOR 
					+ "?"
					+ Constantes.PAR_OPERACION 
					+ "=" 
					+ Constantes.OP_UPDATE 
					+ "&" 
					+ Constantes.PAR_CODIGO 
					+ "="
					+ profesor.getCodigo()
					+ "'> Actualizar </a>";
		btnDelete = "<a href='" 
					+ Constantes.SERVLET_PROFESOR 
					+ "?"
					+ Constantes.PAR_OPERACION 
					+ "=" 
					+ Constantes.OP_DELETE
					+ "&" 
					+ Constantes.PAR_CODIGO 
					+ "'> Delete </a>";
			out.println("<li>" + profesor.getCodigo() + ". " + profesor.toString() + " " + btnUpdate + " " + btnDelete + "</li>");
		}
	%>
	</ul>
		
	</main>
	
	<div>
		<p></p>
	</div>
	
	<div>
		<%@ include file="../includes/footer.html" %>
	</div>
</body>
</html>