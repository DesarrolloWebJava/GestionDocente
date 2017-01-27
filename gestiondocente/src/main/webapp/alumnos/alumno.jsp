<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.GregorianCalendar"%>

<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 String titulo = "";	
 Alumno alumno = (Alumno)request.getAttribute(Constantes.ATT_ALUMNO);
 
 if(alumno == null){
	 titulo = "Crear Alumno";
	 alumno= new Alumno();
 }else{
	 titulo = "Actualizar Alumno";
 }
 %>   
<jsp:include page="../includes/header.jsp" />

	<main>
		<header><h2><%=titulo%></h2></header>
		<form action="<%=Constantes.SERVLET_ALUMNO%>" method="post"> 
			<input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>" value="<%=alumno.getCodigo()%>" >
			<div>
				<label for="<%=Constantes.PAR_NOMBRE%>">*NOMBRE: </label>
				<input type="text" value="<%=alumno.getNombre()%>" id="<%=Constantes.PAR_NOMBRE%>" name="<%=Constantes.PAR_NOMBRE%>" placeholder="Introduce el nombre..." required >
			</div>
			<div>
				<label for="<%=Constantes.PAR_APELLIDOS %>">APELLIDOS: </label>
				<input type="text" value="<%=alumno.getApellidos()%>" value="<%=alumno.getApellidos()%>" id="<%=Constantes.PAR_APELLIDOS %>" name="<%=Constantes.PAR_APELLIDOS%>" placeholder="Introduce los apellidos..." >
			</div>	
			<div>
				<label for="<%=Constantes.PAR_DNI %>">*DNI: </label>
				<input type="text" value="<%=alumno.getDni()%>" id="<%=Constantes.PAR_DNI %>" name="<%=Constantes.PAR_DNI %>" placeholder="Introduce el dni..." required >
			</div>	
			<div>
				<%
				String date = "";
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(alumno.getfNacimiento());
				date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
				%>
				<label for="<%=Constantes.PAR_FNACIMIENTO %>">*FECHA DE NACIMIENTO: </label>
				<input type="text" value="<%=date%>" id="<%=Constantes.PAR_FNACIMIENTO %>" name="<%=Constantes.PAR_FNACIMIENTO %>" placeholder="iNTRODUCE LA FECHA DE NACIMIENTO..." required >
			</div>	
			<div>
				<label for="<%=Constantes.PAR_EMAIL %>">EMAIL: </label>
				<input type="email" value="<%=alumno.getEmail()%>" id="<%=Constantes.PAR_EMAIL %>" name="<%=Constantes.PAR_EMAIL %>" placeholder="Introduce el email" >
			</div>	
			<div>
				<label for="<%=Constantes.PAR_DIRECCION %>">DIRECCION: </label>
				<input type="text" value="<%=alumno.getDireccion()%>" id="<%=Constantes.PAR_DIRECCION %>" name="<%=Constantes.PAR_DIRECCION %>" placeholder="Introduce la direccion" >
			</div>
			<div>
				<label for="<%=Constantes.PAR_NHERMANOS %>">NUMERO DE HERMANOS: </label>
				<input type="number" value="<%=alumno.getnHermanos()%>" id="<%=Constantes.PAR_NHERMANOS %>" name="<%=Constantes.PAR_NHERMANOS %>" placeholder="Introduce el numero de hermanos" >
			</div>
			<div>
				<label for="<%=Constantes.PAR_ACTIVO %>">ACTIVO: </label>
				
				<select name="<%=Constantes.PAR_ACTIVO %>" id="<%=Constantes.PAR_ACTIVO %>">
					<% if (alumno.isActivo()){%>
						<option selected value=1>Activado</option>
						<option value=0>Desactivado</option>
					<%}else{%>
						<option value=1>Activado</option>
						<option selected value=0>Desactivado</option>
					<%}%>
				</select>
				<div>
					<input  type="submit" value="Enviar" /><!-- este si se cierra porque es una version mas antigua que html5 -->
				</div>	
			</div>	
		</form>
		</main>
		<%@ include file="../includes/footer.html" %>
	</body>
</html>