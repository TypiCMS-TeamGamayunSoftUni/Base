import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TestAdminLogin {

    private WebDriver driver;

    @Before
    public void setUp(){
        this.driver = new FirefoxDriver();
        this.driver.get("http://typi-test.webstars.bg/auth/login");
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();

    }

    @Test
    public void TestAdminLogin_EmptyEmailAndPasswordFields_ShouldBeThrowAlert(){
        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("The Email field is required.", alertField.getText());
    }

    @Test
    public void TestAdminLogin_EmptyEmailField_ShouldBeThrowAlert(){
        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        String password = "qwe123";
        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(password);

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("The Email field is required.", alertField.getText());
    }

    @Test
    public void TestAdminLogin_InvalidEmailFieldAndEmptyPasswordField_ShouldBeThrowAlert(){
        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        String invalidEmail = "name" + UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys(invalidEmail);

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("The Email field is required.", alertField.getText());
    }

    @Test
    public void TestAdminLogin_EmptyPasswordField_ShouldBeThrowAlert(){
        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        String email = "dani@plam.ch";
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys(email);

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("The Email field is required.", alertField.getText());
    }

    @Test
    public void TestAdminLogin_InvalidEmailEmptyPasswordField_ShouldBeThrowAlert(){
        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        String invalidEmail = "name" + UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys(invalidEmail);

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("The Email field is required.", alertField.getText());
    }

    @Test
    public void TestAdminLogin_InvalidCredentials_ShouldBeThrowAlert(){
        String invalidEmail = "name" + UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys(invalidEmail);

        String invalidPassword = "qwe1235";
        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(invalidPassword);

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("User does not exist", alertField.getText());
    }

    @Test
    public void TestAdminLogin_InvalidEmail_ShouldBeThrowAlert(){
        String invalidEmail = "name" + UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys(invalidEmail);

        String password = "qwe123";
        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(password);

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("User does not exist", alertField.getText());
    }

    @Test
    public void TestAdminLogin_InvalidPassword_ShouldBeThrowAlert(){
        String email = "dani@plam.ch";
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys(email);

        String invalidPassword = UUID.randomUUID().toString();
        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(invalidPassword);

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("Wrong password, try again", alertField.getText());
    }

    @Test
    public void TestAdminLogin_ValidCredentials_ShouldLoginCorrectly(){
        String email = "dani@plam.ch";
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys(email);

        String password = "qwe123";
        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(password);

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement adminSide = this.driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[1]/a"));
        assertEquals("Admin side", adminSide.getText());
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }

}
