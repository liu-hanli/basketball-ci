import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ModeloDatosTest {

    public static Connection simulateConnection() throws Exception {
        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(0).thenReturn(1);

        return connection;
    }

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        // fail("Fallo forzado.");
    }

    @Test
    public void testActualizarJugador() throws Exception {
        System.out.println("Prueba de actualizarJugador");
        String nombre = "Rudy";
        ModeloDatos instance = new ModeloDatos();
        instance.abrirConexion(simulateConnection());
        int votosAntes = instance.getVotos(nombre);
        instance.actualizarJugador(nombre);
        int votosDespues = instance.getVotos(nombre);
        assertEquals(votosAntes + 1, votosDespues);
        System.out.println("Votos antes: " + votosAntes + ", votos después: " + votosDespues);
    }
}
