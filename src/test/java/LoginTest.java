import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {
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

    private void realizarCadastro(String email, String nome, String senha) {
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[3]/button[2]")).click();
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div[2]/input")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div[3]/input")).sendKeys(nome);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div[4]/div/input")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div[5]/div/input")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/button")).click();

        fecharModal();
    }

    private void realizarLogin(String email, String senha) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[3]/button[1]")).click();
    }

    private void fecharModal() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('btnCloseModal').click();");
    }

    @Test
    @Order(1)
    public void testLoginComSucesso() {
        realizarCadastro("nometeste@gmail.com", "testeUsuario", "Senha123");
        realizarLogin("nometeste@gmail.com", "Senha123");

    }

    @Test
    @Order(2)
    public void testLoginSemSucesso() {
        realizarLogin("nometeste@gmail.com", "senha123");
        fecharModal();
    }


}