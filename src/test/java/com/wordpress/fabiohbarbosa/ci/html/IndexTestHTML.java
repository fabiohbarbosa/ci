package com.wordpress.fabiohbarbosa.ci.html;

import com.wordpress.fabiohbarbosa.ci.config.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class IndexTestHTML {

    @Value("${local.server.port}")
    private int port;

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        String serverUrl = "http://localhost:" + port + "/";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "src/test/resources/phantomjs"
        );

        driver = new PhantomJSDriver(caps);
        driver.get(serverUrl);
    }

    @Test
    public void deveMostrarTabelaDeUsuariosParaUsuarioExistente() {
        WebElement idUsuario = driver.findElement(By.id("id"));
        idUsuario.sendKeys("1");

        WebElement btnBuscarId = driver.findElement(By.id("btn-buscar-id"));
        btnBuscarId.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabela"))).isDisplayed());
        assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("erro"))));
    }

    @Test
    public void naoDeveMostrarTabelaDeUsuariosParaUsuarioInexistente() {
        WebElement idUsuario = driver.findElement(By.id("id"));
        idUsuario.sendKeys("10");

        WebElement btnBuscarId = driver.findElement(By.id("btn-buscar-id"));
        btnBuscarId.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("tabela"))));
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("erro"))).isDisplayed());
    }

    @Test
    public void deveListarTodosUsuarios() {
        WebElement btnBuscarTodos = driver.findElement(By.id("btn-buscar-todos"));
        btnBuscarTodos.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabela"))).isDisplayed());
        assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("erro"))));
    }

    @Test
    public void deveLimparOListarPorId() {
        // buscar por ID
        WebElement idUsuario = driver.findElement(By.id("id"));
        idUsuario.sendKeys("1");

        WebElement btnBuscarId = driver.findElement(By.id("btn-buscar-id"));
        btnBuscarId.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        // confirma que o resultado apareceu
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabela"))).isDisplayed());

        // limpa o resultado
        WebElement btnLimpar = driver.findElement(By.id("btn-limpar"));
        btnLimpar.click();

        assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("tabela"))));
    }

    @Test
    public void deveLimparOListarTodos() {
        // busca todos
        WebElement btnBuscarTodos = driver.findElement(By.id("btn-buscar-todos"));
        btnBuscarTodos.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        // confirma que o resultado apareceu
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabela"))).isDisplayed());

        // limpa o resultado
        WebElement btnLimpar = driver.findElement(By.id("btn-limpar"));
        btnLimpar.click();

        assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("tabela"))));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}