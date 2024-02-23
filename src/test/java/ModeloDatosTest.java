import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ModeloDatosTest {
    private static final String XML_FILE = "src/test/java/jugadores.xml";

    private static IDatabaseTester databaseTester;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String dbHost = System.getenv().get("DATABASE_HOST");
        String dbPort = System.getenv().get("DATABASE_PORT");
        String dbName = System.getenv().get("DATABASE_NAME");
        String dbUser = System.getenv().get("DATABASE_USER");
        String dbPass = System.getenv().get("DATABASE_PASS");

        String url = dbHost + ":" + dbPort + "/" + dbName;
        // Initialize the database tester
        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver", url, dbUser, dbPass);
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Load the dataset into the database
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(XML_FILE));
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
