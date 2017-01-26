<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmesages" /> 
<jsp:include page="includes/header.jsp"/>
<main> 
	<fmt:message key="index.mensaje"/> <fmt:message key="index.nombreApp"/>
</main>
	<%@ include file="includes/footer.html" %>
</body>
</html>