package app.netlify.bugbank.pages;

import app.netlify.bugbank.utils.FecharModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Transferencia extends FecharModal {
    private String contaDestino;
    private String digitoDestino;

    public Transferencia(WebDriver driver) {
        super(driver);
    }

    public void obterDadosContaDestino() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement contaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='textAccountNumber']")));

        String contaCompleta = contaElement.getText();
        String[] partes = contaCompleta.split("-");

        if (partes.length == 2) {
            this.contaDestino = partes[0].replaceAll("[^0-9]", "");
            this.digitoDestino = partes[1].replaceAll("[^0-9]", "");
        } else {
            throw new RuntimeException("Formato da conta inválido. Esperado: 'número-dígito'");
        }
    }

    public String realizarTransferencia() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement transferenciaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btn-TRANSFERÊNCIA']")));
        transferenciaButton.click();

        WebElement contaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("accountNumber")));
        contaInput.sendKeys(this.contaDestino);

        WebElement digitoInput = driver.findElement(By.name("digit"));
        digitoInput.sendKeys(this.digitoDestino);

        WebElement valorInput = driver.findElement(By.name("transferValue"));
        valorInput.sendKeys("100.0");

        WebElement descricaoInput = driver.findElement(By.name("description"));
        descricaoInput.sendKeys("Transferência de teste");

        WebElement transferirButton = driver.findElement(By.xpath("//*[@id='__next']/div/div[3]/form/button"));
        transferirButton.click();

        WebElement mensagemSucesso = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modalText']")));

        return mensagemSucesso.getText();
    }

    public String realizarTransferenciaDadosInvalidos(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement transferenciaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btn-TRANSFERÊNCIA']")));
        transferenciaButton.click();

        WebElement contaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("accountNumber")));
        contaInput.sendKeys("9999");

        WebElement digitoInput = driver.findElement(By.name("digit"));
        digitoInput.sendKeys("9");

        WebElement valorInput = driver.findElement(By.name("transferValue"));
        valorInput.sendKeys("100.0");

        WebElement descricaoInput = driver.findElement(By.name("description"));
        descricaoInput.sendKeys("Transferência de teste conta inexistente");

        WebElement transferirButton = driver.findElement(By.xpath("//*[@id='__next']/div/div[3]/form/button"));
        transferirButton.click();

        WebElement mensagemSemSucesso = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modalText']")));

        return mensagemSemSucesso.getText();
    }

    public String realizarTransferenciaSaldoMaior(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement transferenciaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btn-TRANSFERÊNCIA']")));
        transferenciaButton.click();

        WebElement contaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("accountNumber")));
        contaInput.sendKeys(this.contaDestino);

        WebElement digitoInput = driver.findElement(By.name("digit"));
        digitoInput.sendKeys(this.digitoDestino);

        WebElement valorInput = driver.findElement(By.name("transferValue"));
        valorInput.sendKeys("9900.0");

        WebElement descricaoInput = driver.findElement(By.name("description"));
        descricaoInput.sendKeys("Transferência de teste saldo maior");

        WebElement transferirButton = driver.findElement(By.xpath("//*[@id='__next']/div/div[3]/form/button"));
        transferirButton.click();

        WebElement mensagemSucesso = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modalText']")));

        return mensagemSucesso.getText();
    }
}
