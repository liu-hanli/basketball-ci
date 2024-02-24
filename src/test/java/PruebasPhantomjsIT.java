import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class PruebasPhantomjsIT {
    private static WebDriver driver = null;

    @Test
    public void tituloIndexTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[]{"--web-security=no", "--ignore-ssl-errors=yes"});
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        assertEquals("Votacion mejor jugador liga ACB", driver.getTitle(),
                "El titulo no es correcto");
        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();
    }

    @Test
    public void ponerVotosCeroTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[]{"--web-security=no", "--ignore-ssl-errors=yes"});
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        driver.findElement(By.name("BVotosCero")).click();
        driver.findElement(By.name("BVerVotos")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs("Ver Votos"));
        WebElement votaciones = driver.findElement(By.tagName("table"));
        ArrayList<WebElement> rows = new ArrayList<>(votaciones.findElements(By.tagName("tr")));

        boolean hayDistintoDeCero = false;

        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> row = rows.get(i).findElements(By.tagName("td"));
            String voto = row.get(2).getText().trim();
            if (!voto.equals("0")) {
                hayDistintoDeCero = true;
                break;
            }
        }

        assertFalse(hayDistintoDeCero, "Hay votos distintos de cero");
        System.out.println("Votos de todos los " + (rows.size() - 1) + " jugadores son cero");

        driver.close();
        driver.quit();
    }

    @Test
    public void votarOtroJugador() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[]{"--web-security=no", "--ignore-ssl-errors=yes"});
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        String exampleName = "Azure";
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.findElement(By.name("txtOtros")).sendKeys(exampleName);
        driver.findElement(By.id("radioOtros")).click();
        driver.findElement(By.name("B1")).click();

        wait.until(ExpectedConditions.titleIs("Votación mejor jugador liga ACB"));
        driver.findElement(By.id("home")).click();

        wait.until(ExpectedConditions.titleIs("Votacion mejor jugador liga ACB"));
        driver.findElement(By.name("BVerVotos")).click();

        wait.until(ExpectedConditions.titleIs("Ver Votos"));
        WebElement votaciones = driver.findElement(By.tagName("table"));
        ArrayList<WebElement> rows = new ArrayList<>(votaciones.findElements(By.tagName("tr")));
        rows.remove(0);

        boolean votoEncontrado = false;
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> row = rows.get(i).findElements(By.tagName("td"));
            String nombreJugador = row.get(1).getText().trim();
            String numVotos = row.get(2).getText().trim();
            if (nombreJugador.equals(exampleName) && numVotos.equals("1")) {
                votoEncontrado = true;
                System.out.println("Jugador de ejemplo encontrado en fila " + i);
                break;
            }
        }

        assertTrue(votoEncontrado, "No se ha encontrado el voto para jugador de ejemplo");

        driver.close();
        driver.quit();
    }
}
