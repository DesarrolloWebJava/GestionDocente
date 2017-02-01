<!-- Se importa la libreria de JSTL -->
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Se importa la libreria de FMT 
     (Gestión de las claves de los ficheros de propiedades) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Se indica que la codificación de la página será UTF8. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Se asigna el idioma de la sesion,
     en caso de no existir se asigna castellano(es_ES). -->
<c:set var="language" 
       value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" 
       scope="page" />    
<!-- Se indica el formateo del fichero de propiedades al lenguaje de la página,
     es decir se le indica de que fichero ha de recogerlo.-->
<fmt:setLocale value="${language}" />
<!-- Se indica la ruta de los ficheros de propiedades de la internacionalizacion. -->
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" /> 
	
	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
    <jsp:include page="includes/header.jsp"/>   
	
	<main>
		<!-- Estructura if/esle con JSTL 
		     Se comprueba si la cookie NombreUsuario posee valor.-->
		<c:choose> 	
			<c:when test="${not empty cookie.nombreUsuario}">
				<!-- Se asigna el valor de la cookie a la variale 'name'. -->
				<c:set var="nombreUsuario" value="${cookie.nombreUsuario.value}"/>
			</c:when>
			<c:otherwise>
				<c:set var="nombreUsuario" value="''"/>
			</c:otherwise>
		</c:choose>
		<!-- Se puede simplificar en una linea:
		 /*
		 <c:set var="nombreUsuario" value="${not empty  cookie.nombreUsuario ? cookie.nombreUsuario.value :'' }"/>
		 */
		 -->	
		
		<!-- Estructura if/esle con JSTL 
		     Se comprueba si la cookie passwordUsuario posee valor.-->
		<c:choose> 	
			<c:when test="${not empty cookie.passwordUsuario}">
				<!-- Se asigna el valor de la cookie a la variale 'name'. -->
				<c:set var="passwordUsuario" value="${cookie.passwordUsuario.value}"/>
			</c:when>
			<c:otherwise>
				<c:set var="passwordUsuario" value="''"/>
			</c:otherwise>
		</c:choose>
		
		<!-- Estructura if/esle con JSTL 
		     Se comprueba si la cookie IdiomaUsuario posee valor.-->
		<c:choose> 	
			<c:when test="${not empty cookie.idiomaUsuario}">
				<c:choose> 	
					<c:when test="${cookie.idiomaUsuario.value == 1}">
						<!-- Se asigna el valor de la cookie a la variale 'name'. -->
						<c:set var="idiomaEuskera" value=" selected"/>
					</c:when>
					<c:when test="${cookie.idiomaUsuario.value == 2}">
						<!-- Se asigna el valor de la cookie a la variale 'name'. -->
						<c:set var="idiomaCastellano" value=" selected"/>
					</c:when>
					<c:when test="${cookie.idiomaUsuario.value == 3}">
						<!-- Se asigna el valor de la cookie a la variale 'name'. -->
						<c:set var="idiomaIngles" value=" selected"/>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>	
		
		<!-- Estructura if/esle con JSTL 
		     Se comprueba si la cookie recuerdameUsuario posee valor.-->
		<c:choose> 	
			<c:when test="${not empty cookie.recuerdameUsuario}">
				<!-- Se asigna el valor de la cookie a la variale 'name'. -->
				<c:set var="recuerdameUsuario" value=" checked"/>
			</c:when>
		</c:choose>
	
		
			
		<!-- Formulario de Login. -->
		<form action="<%=Constantes.SERVLET_LOGIN %>" method="post">
			<!-- Se comprueba si tenemos cookie de nombre de usuario. -->
			
			
			<!-- Label para el input de usuario. -->
			<label for="<%=Constantes.PAR_USUARIO %>">
				<fmt:message key="index.login.usuario"/>
			</label>
			<!-- Input de usuario.
				 En el placeholder se coje del fichero de internacionalización. -->
			<input type="text" placeholder=<fmt:message key="index.login.username"/>  
			       id="<%=Constantes.PAR_USUARIO %>" 
			       name="<%=Constantes.PAR_USUARIO %>"
			       value="${nombreUsuario}"/>
			<!-- Label para el input de password. -->
			<label for="<%=Constantes.PAR_PASSWORD %>">
				<fmt:message key="index.login.password"/>
			</label>
			<!-- Input de password.
				 En el placeholder se coje del fichero de internacionalización. -->
			<input type="password" placeholder=<fmt:message key="index.login.password"/>  
			       id="<%=Constantes.PAR_PASSWORD %>" 
			       name="<%=Constantes.PAR_PASSWORD %>"
			       value="${passwordUsuario}" />	
			<!--  Check de recordar contraseña. -->
			<input type ="checkbox" name="recuerdame" id="recuerdame" ${recuerdameUsuario}>
			<label for="recuerdame">Recuérdame</label>
			
			<!-- Label para el input de idioma. -->
			<label for="<fmt:message key="index.idioma"/>"></label>
			<!-- Input Select de idioma.
				 En el placeholder se coje del fichero de internacionalización. -->
			<select id="<%=Constantes.PAR_IDIOMA %>" name="<%=Constantes.PAR_IDIOMA %>">
				<!-- Opción de Castellano. -->
				<option value="<%=Constantes.IDIOMA_CASTELLANO %>"  ${idiomaCastellano}>
												<fmt:message key="idioma.castellano"/>
				</option>
				<!-- Opción de Ingles. -->
				<option value="<%=Constantes.IDIOMA_INGLES %>" ${idiomaIngles}>
												<fmt:message key="idioma.ingles"/>
				</option>
				<!-- Opción de Euskera. -->
				<option value="<%=Constantes.IDIOMA_EUSKERA %>" ${idiomaEuskera}>
												<fmt:message key="idioma.euskera"/>
				</option>				
			</select>	
			
			
			<!--  Botón para el envio de los datos del formulario (alumno.*). -->
			<input type="submit" value="<fmt:message key="index.aceptar"/>"/>
		</form>
		
	</main>
	
	<!-- Include del Pie de la página.	
		  Se llama al include estetico 
		  (no posee codigo java que cambie en función de alguna variable).
		  Contiene las etiquetas <header> y </header>.-->
	<%@ include file ="includes/footer.html" %>								
</body>

</html>