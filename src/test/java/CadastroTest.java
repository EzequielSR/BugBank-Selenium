import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    }

    private void fecharModal() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('btnCloseModal').click();");
    }

    private void botaoComSaldo() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.querySelector(\"#__next > div > div.pages__FormBackground-sc-1ee1f2s-2.jNpkvU > div > div.card__register > form > div.styles__ContainerToggle-sc-7fhc7g-2.cJsFYf > label\").click();");
    }

    @Test
    @Order(1)
    public void testCadastroComSaldo() {
        clicarBotaoRegistrar();
        realizarCadastro("nometeste@gmail.com", "testeUsuario", "Senha123");
        botaoComSaldo();
        fecharModal();
    }

    @Test
    @Order(2)
    public void testCadastroSemSaldo() {
        clicarBotaoRegistrar();
        realizarCadastro("nometeste@gmail.com", "testeUsuario", "Senha123");
        fecharModal();
    }

    @Test
    @Order(3)
    public void testFalhaCadastroSemEmail() {
        clicarBotaoRegistrar();
        realizarCadastro("", "testeUsuario", "Senha123");
        WebElement emailErroInput = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/p"));
        Assertions.assertTrue(emailErroInput.isDisplayed());
    }

    @Test
    @Order(4)
    public void testFalhaCadastroSemNome() {
        clicarBotaoRegistrar();
        realizarCadastro("gmailteste@gmail.com", "", "Senha123");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('modalText');");
        fecharModal();
    }

    @Test
    @Order(5)
    public void testFalhaCadastrarSemSenha() {
        clicarBotaoRegistrar();
        realizarCadastro("nometeste@gmail.com", "usuarioAleatorio", "");
        WebElement senhaErroInput = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/p"));
        Assertions.assertTrue(senhaErroInput.isDisplayed());

    }

}
