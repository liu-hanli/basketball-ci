import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModeloDatosTest {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/baloncesto";
    private static final String USER = "usuario";
    private static final String PASSWORD = "clave";
    private static final String TABLE = "Jugadores";
    private static final String XML_FILE = "jugadores.xml";

    @BeforeAll
    public static void setUp() throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD, TABLE);
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(this.getClass()
                .getResourceAsStream(XML_FILE));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
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
    public void testActualizarJugador() {
        System.out.println("Prueba de actualizarJugador");
        String nombre = "Rudy";
        ModeloDatos instance = new ModeloDatos();
        int votosAntes = instance.getVotos(nombre);
        instance.actualizarJugador(nombre);
        int votosDespues = instance.getVotos(nombre);
        assertEquals(votosAntes + 1, votosDespues);
    }
}
