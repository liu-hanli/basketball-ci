<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.Jugador" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Ver Votos</title>
    <link href="estilos.css" rel="stylesheet" type="text/css"/>
</head>
<body class="votaciones">
<h1>Resultados de la votaci&oacute;n</h1>
<% ArrayList<Jugador> jugadores = (ArrayList<Jugador>) session.getAttribute("jugadores");%>
<table>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Jugador</th>
        <th scope="col">Votos</th>
    </tr>

    <%
        for (Jugador jugador : jugadores) { %>
    <tr>
        <td><%= jugador.getId() %>
        </td>
        <td><%= jugador.getNombre() %>
        </td>
        <td><%= jugador.getVotos() %>
        </td>
    </tr>
    <%
        }
    %>

</table>
<br/>

<a href="index.html" id="home">Volver a la p&aacute;gina principal</a>
</body>
</html>
