<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Votaci&oacute;n mejor jugador liga ACB</title>
    <link href="estilos.css" rel="stylesheet" type="text/css" />
  </head>
  <body class="resultado">
    Votaci&oacute;n al mejor jugador de la liga ACB
    <hr />
    <% String nombreP = (String) session.getAttribute("nombreCliente"); %>
    <br />Muchas gracias <%=nombreP%> por su voto
    <br />
    <br />
    <a href="index.html" id="home">Volver a la p&aacute;gina principal</a>
  </body>
</html>
