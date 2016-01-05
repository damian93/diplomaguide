package tests;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RegistrationTests {

    private final static String testName = "AAJanusz2";
    private static WebDriver driver;

    @Before
    public void init() {
        driver = new FirefoxDriver();
        driver.get("https://localhost:8181/register.xhtml");
    }
    
    @After
    public void after() {
        driver.close();
    }


    @Test
    public void shouldRegister() throws Exception {

        WebElement login = driver.findElement(By.id("registerForm:registerLogin"));

        login.sendKeys(testName.toLowerCase());

        WebElement email = driver.findElement(By.id("registerForm:registerEmail"));

        email.sendKeys(testName.toLowerCase() + "@testowy.pl");

        WebElement name = driver.findElement(By.id("registerForm:registerName"));

        name.sendKeys(testName);

        WebElement surname = driver.findElement(By.id("registerForm:registerSurname"));

        surname.sendKeys(testName);

        WebElement password = driver.findElement(By.id("registerForm:registerPassword"));

        password.sendKeys(testName);

        WebElement password2 = driver.findElement(By.id("registerForm:registerPassword2"));

        password2.sendKeys(testName);

        WebElement button = driver.findElement(By.id("registerForm:registerButton"));

        button.click();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/index.xhtml"));
    }

    @Test
    public void shouldNotRegisterBecauseLoginRequired() throws Exception {
        WebElement email = driver.findElement(By.id("registerForm:registerEmail"));
        email.sendKeys(testName.toLowerCase() + "@testowy.pl");
        WebElement name = driver.findElement(By.id("registerForm:registerName"));
        name.sendKeys(testName);
        WebElement surname = driver.findElement(By.id("registerForm:registerSurname"));
        surname.sendKeys(testName);
        WebElement password = driver.findElement(By.id("registerForm:registerPassword"));
        password.sendKeys(testName);
        WebElement password2 = driver.findElement(By.id("registerForm:registerPassword2"));
        password2.sendKeys(testName);
        WebElement button = driver.findElement(By.id("registerForm:registerButton"));
        button.click();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://localhost:8181/register.xhtml"));
    }
}
