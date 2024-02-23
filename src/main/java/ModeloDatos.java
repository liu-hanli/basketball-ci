import java.sql.*;
import java.util.logging.Logger;

public class ModeloDatos {

    private Connection con;
    private Statement set;
    private ResultSet rs;
    private final Logger logger = Logger.getLogger("ModeloDatos");

    public void abrirConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Con variables de entorno
            String dbHost = System.getenv().get("DATABASE_HOST");
            String dbPort = System.getenv().get("DATABASE_PORT");
            String dbName = System.getenv().get("DATABASE_NAME");
            String dbUser = System.getenv().get("DATABASE_USER");
            String dbPass = System.getenv().get("DATABASE_PASS");

            String url = dbHost + ":" + dbPort + "/" + dbName;
            con = DriverManager.getConnection(url, dbUser, dbPass);

        } catch (Exception e) {
            // No se ha conectado
            logger.severe("No se ha podido conectar");
            logger.severe("El error es: " + e.getMessage());
        }
    }

    public boolean existeJugador(String nombre) {
        boolean existe = false;
        String cad;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores");
            while (rs.next()) {
                cad = rs.getString("Nombre");
                cad = cad.trim();
                if (cad.compareTo(nombre.trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            // No lee de la tabla
            logger.severe("No lee de la tabla");
            logger.severe("El error es: " + e.getMessage());
        }
        return (existe);
    }

    public void actualizarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre " + " LIKE '%" + nombre + "%'");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No modifica la tabla
            logger.severe("No modifica la tabla");
            logger.severe("El error es: " + e.getMessage());
        }
    }

    public void insertarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO Jugadores " + " (nombre,votos) VALUES ('" + nombre + "',1)");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No inserta en la tabla
            logger.severe("No inserta en la tabla");
            logger.severe("El error es: " + e.getMessage());
        }
    }

    public void votosACero() {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=0");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No modifica la tabla
            logger.severe("No modifica la tabla");
            logger.severe("El error es: " + e.getMessage());
        }
    }

    public int getVotos(String nombre) {
        int votos = 0;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT votos FROM Jugadores WHERE nombre LIKE '%" + nombre + "%'");
            while (rs.next()) {
                votos = rs.getInt(1);
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            // No lee de la tabla
            logger.severe("No lee de la tabla");
            logger.severe("El error es: " + e.getMessage());
        }
        return (votos);
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

}
