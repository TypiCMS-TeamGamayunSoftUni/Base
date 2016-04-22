import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;


import java.util.UUID;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class TestPublishingNews {
    private WebDriver driver;

    @Before
    public void setUp(){
        this.driver = new FirefoxDriver();
        this.driver.get("http://typi-test.webstars.bg/auth/login");
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    private void LoginAndNavigateToNewsPanel(){
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("dani@plam.ch");

        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("qwe123");

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement adminSideButton = this.driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[1]/a"));
        adminSideButton.click();

        WebElement newsButton = this.driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[2]/ul/li[2]/a[1]/div"));
        newsButton.click();

        WebElement newNewsButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/a/i"));
        newNewsButton.click();
    }

    @Test
    public void TestPublishingNews_WithoutDate_ShouldThrowAlert(){
        LoginAndNavigateToNewsPanel();

        WebElement dateField = this.driver.findElement(By.id("date"));
        dateField.clear();

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/ul/li"));
        assertEquals("The Date field is required.", alertField.getText());
    }

    @Test
    public void TestPublishingNews_WithUnsupportedCharactersInSlug_ShouldThrowAlert(){
        LoginAndNavigateToNewsPanel();

        String unsupportedCharacters = " ^ / < >";
        WebElement slugField = this.driver.findElement(By.id("en[slug]"));
        slugField.sendKeys(unsupportedCharacters);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/ul/li"));
        assertEquals("The en.slug may only contain letters, numbers, and dashes.", alertField.getText());
    }

    @Test
    public void TestPublishingNews_WithoutTextInBody_NewsByPublished(){
        LoginAndNavigateToNewsPanel();

        String title = "title " + UUID.randomUUID().toString();
        WebElement titleField = this.driver.findElement(By.id("en[title]"));
        titleField.sendKeys(title);

        String summaryText = "summary text " + UUID.randomUUID().toString();
        WebElement summaryField = this.driver.findElement(By.id("en[summary]"));
        summaryField.sendKeys(summaryText);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement titleFieldForPublishedNews = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[6]"));
        assertEquals(title, titleFieldForPublishedNews.getText());
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }
}
