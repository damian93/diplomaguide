package tests;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RegistrationTests {

    @Test
    public void shouldRegister() throws Exception {
        WebDriver driver = new FirefoxDriver();

        driver.get("https://localhost:8181/register.xhtml");

        WebElement login = driver.findElement(By.id("registerForm:registerLogin"));

        login.sendKeys("Janusz2");

        WebElement email = driver.findElement(By.id("registerForm:registerEmail"));

        email.sendKeys("janusz2@testowy.pl");

        WebElement name = driver.findElement(By.id("registerForm:registerName"));
        
        name.sendKeys("Janusz");

        WebElement surname = driver.findElement(By.id("registerForm:registerSurname"));
        
        surname.sendKeys("Janusz");

        WebElement password = driver.findElement(By.id("registerForm:registerPassword"));
        
        password.sendKeys("Janusz");

        WebElement password2 = driver.findElement(By.id("registerForm:registerPassword2"));
        
        password2.sendKeys("Janusz");

        WebElement regDate = driver.findElement(By.id("registerForm:registerDate"));
        
        regDate.sendKeys("08/18/2015 00:00:00");

     //   WebElement type = driver.findElement(By.id("registerForm:type"));
        
     //   type.s

        WebElement button = driver.findElement(By.id("registerForm:registerButton"));

        button.click();
     
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/index.xhtml"));
    }
}
