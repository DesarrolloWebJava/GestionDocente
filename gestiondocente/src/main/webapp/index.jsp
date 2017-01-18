<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Gestión Docente: Página de Inicio</title>
</head>
<body>
<header>
<h1>Gestor Docente Ipartek</h1>
	<nav>
<!-- marca que lo que hay dentro es un elemento de navegación-->
		<ul>
		<!-- Al hacer un enlace: poner SIEMPRE # 
		defecto, aunque luego lo sustituyamos. -->
		<!-- ¡NOTA! Cuidado no copiar el path del servlet con "/" incluída, tal y 
		como aparecen en el web.xml, porque aquí haría referencia 
		no al parent folder, sino al directorio raíz. -->
			<li><a href="alumno.do">G. Alumnos</a></li>
			<li><a href="profesor.do">G. Profesores</a></li>
			<li><a href="#">G. Cursos</a></li>
		</ul>
	</nav>
</header>
<main>
<!--solo puede haber uno;  marca que no es un contenido estático -->
Bienvenidos a Gestión Alumnos
</main>

<footer>
Realizada por Ipartek S. Coop.
</footer>

</body>
</html>