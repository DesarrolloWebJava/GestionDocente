<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="../includes/header.jsp"/>
	
<main>
	<div class="container">
		<h1>Página Listado Alumnos</h1>
			<%
				/*esto es un criplet*/
				//recogemos el atributo de la request y lo gardamos en un objeto lista
				//hay q hacer un casting de request y decirle q es tipo list
				List<Alumno> alumnos =(List<Alumno>) request.getAttribute("listado-alunmos"); 
				//sacar la lista por pantalla	
			%>
		<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE %>" class="btn btn-primary btn-lg active" role="button"> Crear Alumno </a>
		
		<div class="row">
			<% 
				for(Alumno alumno : alumnos){
				%>
					<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_UPDATE %>&<%=alumno.getCodigo()%>">
				<%
					out.println("<p>"+alumno.toString()+"</p>");	
				}
				%>
				</a>
			<% 
			%>
		</div>
	</div>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>