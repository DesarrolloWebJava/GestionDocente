<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp" />
	<main>
		<div>
		<form action="<%=Constantes.SERVLET_PROFESOR %>" method="post"> 
			<input type="hidden" id="<%=Constantes.PAR_CODIGO %>" name="<%=Constantes.PAR_CODIGO %>" value="-1" >
			<div>
				<label for="<%=Constantes.PAR_NOMBRE %>">*NOMBRE: </label>
				<input type="text" id="<%=Constantes.PAR_NOMBRE %>" name="<%=Constantes.PAR_NOMBRE %>" placeholder="Introduce el nombre..." required >
			</div>
			<div>
				<label for="<%=Constantes.PAR_APELLIDOS %>">APELLIDOS: </label>
				<input type="text" id="<%=Constantes.PAR_APELLIDOS %>" name="<%=Constantes.PAR_APELLIDOS %>" placeholder="Introduce los apellidos..." >
			</div>	
			<div>
				<label for="<%=Constantes.PAR_DNI %>">*DNI: </label>
				<input type="text" id="<%=Constantes.PAR_DNI %>" name="<%=Constantes.PAR_DNI %>" placeholder="Introduce el dni..." required >
			</div>	
			<div>
				<label for="<%=Constantes.PAR_FNACIMIENTO %>">*FECHA DE NACIMIENTO: </label>
				<input type="text" id="<%=Constantes.PAR_FNACIMIENTO %>" name="<%=Constantes.PAR_FNACIMIENTO %>" placeholder="iNTRODUCE LA FECHA DE NACIMIENTO..." required >
			</div>	
			<div>
				<label for="<%=Constantes.PAR_EMAIL %>">EMAIL: </label>
				<input type="email" id="<%=Constantes.PAR_EMAIL %>" name="<%=Constantes.PAR_EMAIL %>" placeholder="Introduce el email" >
			</div>	
			<div>
				<label for="<%=Constantes.PAR_DIRECCION %>">DIRECCION: </label>
				<input type="text" id="<%=Constantes.PAR_DIRECCION %>" name="<%=Constantes.PAR_DIRECCION %>" placeholder="Introduce la direccion" >
			</div>
			<div>
				<label for="<%=Constantes.PAR_NSS %>">Nº SS: </label>
				<input type="text" id="<%=Constantes.PAR_NSS %>" name="<%=Constantes.PAR_NSS %>" placeholder="Introduce el nº de la SS..." >
			</div>
					<input  type="submit" value="Enviar" /><!-- este si se cierra porque es una version mas antigua que html5 -->
				</div>	
			</div>	
		</form>
		</div>
		</main>
		<%@ include file="../includes/footer.html" %>
	</body>
</html>