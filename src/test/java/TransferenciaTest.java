import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransferenciaTest {

    private WebDriver driver;
    private String contaDestino;
    private String digitoDestino;

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

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('toggleAddBalance').click();");

        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/button")).click();

        fecharModal();
    }

    private void realizarLogin(String email, String senha) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[3]/button[1]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Oiá usuário, bem vindo ao BugBank')]")));

    }

    private void realizarLoginComSaldo(String email, String senha) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[3]/button[1]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"textBalance\"]")));
    }

    private void obterDadosContaDestino() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement contaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='textAccountNumber']")));

        String contaCompleta = contaElement.getText();

        String[] partes = contaCompleta.split("-");

        if (partes.length == 2) {
            contaDestino = partes[0].replaceAll("[^0-9]", "");
            digitoDestino = partes[1].replaceAll("[^0-9]", "");
        } else {
            throw new RuntimeException("Formato da conta inválido. Esperado: 'número-dígito'");
        }
    }

    private void fecharModal() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('btnCloseModal').click();");
    }

    private void realizarTransferencia(String numeroConta, String digitoConta, double valorTransferencia, String descricao) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement transferenciaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btn-TRANSFERÊNCIA']")));
        transferenciaButton.click();

        WebElement contaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("accountNumber")));
        contaInput.sendKeys(numeroConta);

        WebElement digitoInput = driver.findElement(By.name("digit"));
        digitoInput.sendKeys(digitoConta);

        WebElement valorInput = driver.findElement(By.name("transferValue"));
        valorInput.sendKeys(String.valueOf(valorTransferencia));

        WebElement descricaoInput = driver.findElement(By.name("description"));
        descricaoInput.sendKeys(descricao);

        WebElement transferirButton = driver.findElement(By.xpath("//*[@id='__next']/div/div[3]/form/button"));
        transferirButton.click();

        WebElement mensagemSucesso = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modalText']")));
        Assertions.assertTrue(mensagemSucesso.getText().contains("Transferencia realizada com sucesso"));
    }

    @Test
    @Order(1)
    public void testRealizarTransferenciaComSucesso() {
        realizarCadastro("nometeste1@gmail.com", "testeUsuario1", "Senha123");
        realizarLoginComSaldo("nometeste1@gmail.com", "Senha123");

        driver.findElement(By.xpath("//*[@id='btnExit']")).click();
        realizarCadastro("nometeste2@gmail.com", "testeUsuario2", "Senha123");
        realizarLoginComSaldo("nometeste2@gmail.com", "Senha123");

        obterDadosContaDestino();

        driver.findElement(By.xpath("//*[@id='btnExit']")).click();
        realizarLoginComSaldo("nometeste1@gmail.com", "Senha123");

        realizarTransferencia(contaDestino, digitoDestino, 100.0, "Transferência de teste");
    }
}
