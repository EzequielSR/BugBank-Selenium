import app.netlify.bugbank.pages.Cadastro;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastroTest extends BaseTest {
    private Cadastro cadastro;

    @BeforeEach
    public void setUp() {
        super.setUp();
        cadastro = new Cadastro(driver);
    }


    @Test
    @Order(1)
    public void testCadastroComSaldo() {
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastroComSaldo("nometeste@gmail.com", "testeUsuario", "Senha123");
        cadastro.fecharModal();
    }

    @Test
    @Order(2)
    public void testCadastroSemSaldo() {
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastro("nometeste@gmail.com", "testeUsuario", "Senha123");
        cadastro.fecharModal();
    }

    @Test
    @Order(3)
    public void testFalhaCadastroSemEmail() {
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastro("", "testeUsuario", "Senha123");
        WebElement emailErroInput = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/p"));
        Assertions.assertTrue(emailErroInput.isDisplayed());
    }

    @Test
    @Order(4)
    public void testFalhaCadastroSemNome() {
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastro("gmailteste@gmail.com", "", "Senha123");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('modalText');");
        cadastro.fecharModal();
    }

    @Test
    @Order(5)
    public void testFalhaCadastrarSemSenha() {
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastro("nometeste@gmail.com", "usuarioAleatorio", "");
        WebElement senhaErroInput = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/p"));
        Assertions.assertTrue(senhaErroInput.isDisplayed());

    }

}
