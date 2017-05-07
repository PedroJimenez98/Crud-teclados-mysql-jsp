<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Añadir nuevo dispositivo</title>
<style>
body {
	background-color : #b2ebf2;
	background-image: url(https://i.ytimg.com/vi/cnRwS9smYdA/maxresdefault.jpg);
	background-repeat: no-repeat;
	background-attachment: fixed;
}
a {
	color: red;
}
</style>
</head>
<body>
	<%
		String servlet = "MainServlet?action=added";
	%>

	<form class="col s12" action="<%=servlet%>" method="post">
		<div class="row">

			<div class="input-field col l6 m12 s12">
			<font color='white'>
				<input type="text" name="marca" placeholder="Marca" value=""
					required> <label for="Marca">Marca</label>
			</div>
			<div class="input-field col l6 m6 s12">
				<input type="text" name="modelo" placeholder="Modelo" value=""
					required> <label for="Modelo">Modelo</label>
			</div>
			<div class="input-field col l4 m6 s12">
				<input type="text" name="tipo" placeholder="Tipo" value="" 
				required><label for="tipo">Tipo</label>
			</div>
			<div class="input-field col l4 m6 s12">
				<input type="text" name="precio" placeholder="Precio" value=""
					required> <label for="precio">Precio</label>
			</div>
			<div class="input-field col l4 m6 s12">
				<input type="text" name="cantidad" placeholder="Cantidad" value=""
					required> <label for="cantidad">Cantidad</label>
			</div>
			<br> <br> <br> <br>
			<center>
				<form action="MainServlet?action=added" method="post" name="added"><button class="btn waves-effect waves-light" type="submit" value="Modificar">Modificar<input type="hidden" name="action" value="added"></form>
			</center>
	</form>
</body>
</html>