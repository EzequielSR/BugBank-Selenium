import app.netlify.bugbank.pages.Cadastro;
import app.netlify.bugbank.pages.Extrato;
import app.netlify.bugbank.pages.Login;
import app.netlify.bugbank.pages.Transferencia;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExtratoTest extends BaseTest {
    private Cadastro cadastro;
    private Login login;
    private Transferencia transferencia;
    private Extrato extrato;

    @BeforeEach
    public void setUp() {
        super.setUp();
        cadastro = new Cadastro(driver);
        login = new Login(driver);
        transferencia = new Transferencia(driver);
        extrato = new Extrato(driver);
    }
    public void botaoSairPaginaPrincipal(){
        driver.findElement(By.xpath("//*[@id='btnExit']")).click();
    }

    @Test
    @Order(1)
    public void testRealizarTransferenciaComSucesso() {
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastroComSaldo("nometeste1@gmail.com", "testeUsuario1", "Senha123");
        cadastro.fecharModal();
        login.realizarLogin("nometeste1@gmail.com", "Senha123");

        botaoSairPaginaPrincipal();
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastro("nometeste2@gmail.com", "testeUsuario2", "Senha123");
        cadastro.fecharModal();
        login.realizarLogin("nometeste2@gmail.com", "Senha123");

        transferencia.obterDadosContaDestino();

        botaoSairPaginaPrincipal();
        login.realizarLogin("nometeste1@gmail.com", "Senha123");

        transferencia.realizarTransferencia();
        transferencia.fecharModal();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('btnBack').click();");

        extrato.clicarExtrato();

        double saldoAtual = extrato.obterSaldoDisponivel();
        assertEquals(900.0, saldoAtual, 0.01, "O saldo disponível não está correto!");
    }
}