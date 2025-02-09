package app.netlify.bugbank.pages;

import app.netlify.bugbank.utils.FecharModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Extrato extends FecharModal {

    public Extrato(WebDriver driver) {
        super(driver);
    }

    public void clicarExtrato() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement extratoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btn-EXTRATO']")));
        extratoButton.click();
    }

    public double obterSaldoDisponivel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement saldoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='textBalanceAvailable']")));

        String saldoTexto = saldoElement.getText().replace("R$", "").trim().replace(".", "").replace(",", ".");
        return Double.parseDouble(saldoTexto);
    }
}
