import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestPages {
    private WebDriver driver;

    @Before
    public void setUp(){
        this.driver = new FirefoxDriver();
        this.driver.get("http://typi-test.webstars.bg/auth/login");
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    private void LoginAndNavigateToPagePanelAndClickNewPage(){
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("dani@plam.ch");

        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("qwe123");

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement adminSideButton = this.driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[1]/a"));
        adminSideButton.click();

        WebElement pageButton = this.driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[2]/ul/li[1]/a[1]/div"));
        pageButton.click();

        WebElement newPageButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/a/i"));
        newPageButton.click();
    }

    @Test
    public void TestCreateNewPage_AllFieldsAreEmpty_CreateNewPage(){
        LoginAndNavigateToPagePanelAndClickNewPage();

        WebElement saveButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/button[2]"));
        saveButton.click();

        WebElement pageNameField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/h1"));
        Assert.assertEquals("Untitled", pageNameField.getText());
    }

    @Test
    public void TestCreateNewPage_OnlyUnusualSymbolsInTitle_CreateNewPage(){
        LoginAndNavigateToPagePanelAndClickNewPage();

        String title = " ^ / < >";
        WebElement titleField = this.driver.findElement(By.id("en[title]"));
        titleField.sendKeys(title);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement pagesField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div"));
        Assert.assertTrue(pagesField.getText().contains(title));
    }

    @Test
    public void TestCreateNewPage_ValidTitle_CreateNewPage(){
        LoginAndNavigateToPagePanelAndClickNewPage();

        String title = "title " + UUID.randomUUID().toString();
        WebElement titleField = this.driver.findElement(By.id("en[title]"));
        titleField.sendKeys(title);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement pagesField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div"));
        Assert.assertTrue(pagesField.getText().contains(title));
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }
}
