
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;

public class TestBlock {
    private WebDriver driver;

    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        this.driver.get("http://typi-test.webstars.bg/auth/login");
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    private void LoginAndNavigateToBlockPanelAndClickNewBlock(){
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("dani@plam.ch");

        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("qwe123");

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement adminSideButton = this.driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[1]/a"));
        adminSideButton.click();

        WebElement blockButton = this.driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[2]/ul/li[3]/a[1]/div"));
        blockButton.click();

        WebElement newBlockButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/a/i"));
        newBlockButton.click();
    }

    @Test
    public void TestCreateNewBlock_AllFieldsAreEmpty_ShouldThrowAlert(){
        LoginAndNavigateToBlockPanelAndClickNewBlock();

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div"));
        Assert.assertEquals("×\n" +
                "The form contains errors.\n" +
                "The Name field is required.", alertField.getText());
    }


    @Test
    public void TestCreateNewBlock_NameFieldIsFilledValidName_CreateBlock(){
        LoginAndNavigateToBlockPanelAndClickNewBlock();

        // Before this, clean other automated test from blockField (not unique)
        String name = "TestNewBlock";
        WebElement nameField = this.driver.findElement(By.id("name"));
        nameField.sendKeys(name);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement blockField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody"));
        Assert.assertTrue(blockField.getText().contains(name));
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }
}
