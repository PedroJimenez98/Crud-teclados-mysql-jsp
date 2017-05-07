<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de usuarios</title>
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
<title>Tabla</title>
<style>
body {
	background-color: #b2ebf2;
	background-image:
		url("https://wallpaperscraft.com/image/computer_keyboard_mouse_laptop_mac_apple_66734_1920x1080.jpg");
	background-attachment: fixed;
	background-position: medium bottom;
}

a {
	color: red;
}
</style>
</head>
<body>
	<%
		String[][] teclados = (String[][]) request.getAttribute("tecladosAUX");
	%>

	<div class="container">
		<div class="row">
			<div class="col l12 m12 s12">
				<table style="margin: 0 auto;" class="striped" bgcolor="#00acc1">
					<thead>
						<tr>
							<th>Marca</th>
							<th>Modelo</th>
							<th>Tipo</th>
							<th>Precio</th>
							<th>Cantidad</th>
						<tr>
					</thead>
					<%
						int i = 0;
						String modelo = teclados[i][1];
						for (i = 0; i < teclados.length; i++) {
							out.print("<tr><td>" + teclados[i][0] + "</td><td>" + teclados[i][1] + "</td><td>" + teclados[i][2]
									+ "</td><td>" + teclados[i][3] + " euros</td><td>" + teclados[i][4]

									+ "</td><td><form action=\"MainServlet?action=eliminar\" method=\"get\" name=\"eliminar\"><button class=\"btn waves-effect waves-light\""
									+ " type=\"submit\" value=\"Eliminar\">Eliminar<input type=\"hidden\" name=\"action\" value=\"eliminar\"><input type=\"hidden\" name=\"modelo\" value=\""
									+ teclados[i][1] + "\"></button></form></td>"

									+ "<td><form action=\"MainServlet?action=modificar\" method=\"get\" name=\"modificar\"><button class=\"btn waves-effect waves-light\""
									+ " type=\"submit\" value=\"Modificar\">Modificar<input type=\"hidden\" name=\"action\" value=\"modificar\"><input type=\"hidden\" name=\"modelo\" value=\""
									+ teclados[i][1] + "\"></button></form></td>");
						}
					%>
				</table>
			</div>
		</div>
	</div>
	<center>
		<form action="MainServlet?action=addNew" method="get" name="addNew">
			<button class="btn waves-effect waves-light" type="submit"
				value="Añadir">
				Añadir<input type="hidden" name="action" value="addnew">
			</button>
		</form>
	</center>
</body>
</html>
