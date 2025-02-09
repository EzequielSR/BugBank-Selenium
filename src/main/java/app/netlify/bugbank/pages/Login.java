package app.netlify.bugbank.pages;

import app.netlify.bugbank.utils.FecharModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends FecharModal {

    public Login(WebDriver driver) {
        super(driver);
    }

    public void realizarLogin(String email, String senha) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[3]/button[1]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"textBalance\"]")));
    }

}
