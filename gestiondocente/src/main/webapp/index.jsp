<!-- Se importa la libreria de JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Se importa la libreria de FMT 
     (Gestión de las claves de los ficheros de propiedades) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Se indica que la codificación de la página será UTF8. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- Include de cabecera (header).	
	     Se llama al include dimanico 
	     (posee codigo java que cambie en función de alguna variable).
	     Contiene (<body>).-->	
    <jsp:include page="includes/header.jsp"/>
    <!-- Se asigna el lenguaje castellano a la página. -->
    <c:set var="language" value="${'es_ES'}" scope="page" />
    
    <!-- Se indica el formateo del fichero de propiedades al lenguaje de la página,
         es decir se le indica de que fichero ha de recogerlo.-->
	<fmt:setLocale value="${language}" />
	<!-- Se indica la ruta de los ficheros de propiedades de la internacionalizacion. -->
	<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" /> 
	
	<main>
		<p>
			<!-- Se recoge la tradución del fichero de propiedades de 
			     internacionalizazión para el mensaje de bienvenida.-->
			<fmt:message key="index.mensaje"/> 
		</p>
		<p>
			<!-- Se recoge la tradución del fichero de propiedades de 
			     internacionalizazión para el mensaje de bienvenida.-->
			<fmt:message key="index.nombreApp"/>
		</p>
	</main>
	
	<!-- Include del Pie de la página.	
		  Se llama al include estetico 
		  (no posee codigo java que cambie en función de alguna variable).
		  Contiene las etiquetas <header> y </header>.-->
	<%@ include file ="includes/footer.html" %>								
</body>

</html>