import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestAdminLogin {

    private WebDriver driver;

    @Before
    public void SetUp(){
        this.driver = new FirefoxDriver();
        this.driver.get("http://typi-test.webstars.bg/auth/login");
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    @Test
    public void TestAdminLoginWithValidCredentials(){
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("dani@plam.ch");

        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("qwe123");

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement adminSide = this.driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[1]/a"));
        assertEquals("Admin side", adminSide.getText());
    }

    @Test
    public void TestAdminLoginWithInvalidEmail(){
        String invalidEmail = "name" + UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys(invalidEmail);

        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("qwe123");

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement allertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("User does not exist", allertField.getText());
    }

    @Test
    public void TestAdminLoginWithInvalidPassword(){
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("dani@plam.ch");

        String invalidPassword = UUID.randomUUID().toString();
        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(invalidPassword);

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement allertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));
        assertEquals("Wrong password, try again", allertField.getText());
    }



    //@Test
    //public void TestAdminLoginWithEmailWithoutTailEnd(){
        //String invalidEmail = "name" + UUID.randomUUID().toString();
        //WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        //emailField.sendKeys(invalidEmail);

        //WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        //passwordField.sendKeys("qwe123");

        //WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        //loginButton.click();

        //WebElement allertField = this.driver.findElement(By.xpath("/html/body/div/div/div/div/div/p"));

    //}

    @After
    public void TearDown(){
        this.driver.quit();
    }

}
