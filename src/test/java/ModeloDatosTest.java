import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModeloDatosTest {

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
