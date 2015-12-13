package tests;

import java.util.Calendar;
import java.util.Date;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RegistrationTests {
    
    private final static String testName = "Janusz2";
    private static Date testDate;
    
    @Before
    private void init(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        testDate = c.getTime();
    }
    

    @Test
    public void shouldRegister() throws Exception {
        WebDriver driver = new FirefoxDriver();

        driver.get("https://localhost:8181/register.xhtml");

        WebElement login = driver.findElement(By.id("registerForm:registerLogin"));

        login.sendKeys(testName);

        WebElement email = driver.findElement(By.id("registerForm:registerEmail"));

        email.sendKeys(testName+"@testowy.pl");

        WebElement name = driver.findElement(By.id("registerForm:registerName"));
        
        name.sendKeys(testName);

        WebElement surname = driver.findElement(By.id("registerForm:registerSurname"));
        
        surname.sendKeys(testName);

        WebElement password = driver.findElement(By.id("registerForm:registerPassword"));
        
        password.sendKeys(testName);

        WebElement password2 = driver.findElement(By.id("registerForm:registerPassword2"));
        
        password2.sendKeys(testName);

        WebElement regDate = driver.findElement(By.id("registerForm:registerDate"));
        
        regDate.sendKeys(testDate.toString());

     //   WebElement type = driver.findElement(By.id("registerForm:type"));
        
     //   type.s

        WebElement button = driver.findElement(By.id("registerForm:registerButton"));

        button.click();
     
        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/index.xhtml"));
    }
}
