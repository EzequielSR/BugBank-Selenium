package app.netlify.bugbank.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FecharModal {
    protected WebDriver driver;

    public FecharModal(WebDriver driver){
        this.driver = driver;
    }

    public void fecharModal() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('btnCloseModal').click();");
    }
}
