import app.netlify.bugbank.pages.Cadastro;
import app.netlify.bugbank.pages.Login;
import app.netlify.bugbank.pages.Transferencia;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransferenciaTest extends BaseTest {
    private Cadastro cadastro;
    private Login login;
    private Transferencia transferencia;

    @BeforeEach
    public void setUp() {
        super.setUp();
        cadastro = new Cadastro(driver);
        login = new Login(driver);
        transferencia = new Transferencia(driver);
    }

    @Test
    @Order(1)
    public void testRealizarTransferenciaComSucesso() {
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastroComSaldo("nometeste1@gmail.com", "testeUsuario1", "Senha123");
        cadastro.fecharModal();
        login.realizarLogin("nometeste1@gmail.com", "Senha123");

        driver.findElement(By.xpath("//*[@id='btnExit']")).click();
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastro("nometeste2@gmail.com", "testeUsuario2", "Senha123");
        cadastro.fecharModal();
        login.realizarLogin("nometeste2@gmail.com", "Senha123");

        transferencia.obterDadosContaDestino();

        driver.findElement(By.xpath("//*[@id='btnExit']")).click();
        login.realizarLogin("nometeste1@gmail.com", "Senha123");

        String mensagemSucesso = transferencia.realizarTransferencia();

        assertTrue(mensagemSucesso.contains("Transferencia realizada com sucesso"));
    }
}
