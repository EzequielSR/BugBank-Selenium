import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

public class CadastroTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://bugbank.netlify.app/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCadastroUsuario() {
        clicarBotaoRegistrar();
        realizarCadastro("nometeste@gmail.com", "testeUsuario", "Senha123");

    }

    private void clicarBotaoRegistrar() {
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[3]/button[2]"))
                .click();
    }

    private void realizarCadastro(String email, String nome, String senha) {
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div[2]/input")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div[3]/input")).sendKeys(nome);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div[4]/div/input")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div[5]/div/input")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/button")).click();

        fecharModalSucesso();
    }

    private void fecharModalSucesso() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('btnCloseModal').click();");
    }
}
