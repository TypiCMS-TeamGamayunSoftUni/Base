import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGalleries {

    private WebDriver driver;

    @Before
    public void setUp(){
        this.driver = new FirefoxDriver();
        this.driver.get("http://typi-test.webstars.bg/auth/login");
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    private void LoginAndNavigateToNewGallery(){
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("dani@plam.ch");

        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("qwe123");

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement adminSideButton = this.driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[1]/a"));
        adminSideButton.click();

        WebElement galleriesButton = this.driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[3]/ul/li[2]/a[1]/div"));
        galleriesButton.click();

        WebElement newGalleryButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/a/i"));
        newGalleryButton.click();
    }

    @Test
    public void TestCreateNewGallery_AllFieldAreEmpty_ShouldThrowAlert(){
        LoginAndNavigateToNewGallery();

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div"));
        assertEquals("×\n" +
                "The form contains errors.\n" +
                "The Name field is required.", alertField.getText());
    }

    @Test
    public void TestCreateNewGallery_OnlyNameFilled_CreateGallery(){
        LoginAndNavigateToNewGallery();

        String name = "TestName";
        WebElement nameField = this.driver.findElement(By.id("name"));
        nameField.sendKeys(name);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement galleryField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody"));
        Assert.assertTrue(galleryField.getText().contains(name));
    }

    @Test
    public void TestCreateNewGallery_OnlyNameFilledWithUnicodeSymbols_CreateGallery(){
        LoginAndNavigateToNewGallery();

        String name = "ТестИме";
        WebElement nameField = this.driver.findElement(By.id("name"));
        nameField.sendKeys(name);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement galleryField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody"));
        Assert.assertTrue(galleryField.getText().contains(name));
    }

    @Test
    public void TestCreateNewGallery_TitleFilled_CreateGallery(){
        LoginAndNavigateToNewGallery();

        String name = "TestName";
        WebElement nameField = this.driver.findElement(By.id("name"));
        nameField.sendKeys(name);

        String title = "test " + UUID.randomUUID().toString();
        WebElement titleField = this.driver.findElement(By.id("en[title]"));
        titleField.sendKeys(title);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement galleryField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody"));
        Assert.assertTrue(galleryField.getText().contains(title));
    }

    @Test
    public void TestNameTitleSearchBar_SpecialSymbol_SearchSuccess(){
        LoginAndNavigateToNewGallery();

        WebElement backButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/a/span[1]"));
        backButton.click();

        String searchSpecialSymbol = "-";
        WebElement titleSearchBar = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/thead/tr[2]/td[3]/input"));
        titleSearchBar.sendKeys(searchSpecialSymbol);

        WebElement galleryField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody"));
        Assert.assertTrue(galleryField.getText().contains(searchSpecialSymbol));
    }

    @Test
    public void TestNameSearchBar_LatinSymbol_SearchSuccess(){
        LoginAndNavigateToNewGallery();

        WebElement backButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/a/span[1]"));
        backButton.click();

        String searchUnicode = "e";
        WebElement searchBar = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/thead/tr[2]/td[2]/input"));
        searchBar.sendKeys(searchUnicode);

        WebElement galleryField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody"));
        Assert.assertTrue(galleryField.getText().contains(searchUnicode));
    }

    @Test
    public void TestNameSearchBar_UnicodeSymbol_SearchSuccess(){
        LoginAndNavigateToNewGallery();

        WebElement backButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/a/span[1]"));
        backButton.click();

        String searchUnicode = "е";
        WebElement searchBar = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/thead/tr[2]/td[2]/input"));
        searchBar.sendKeys(searchUnicode);

        WebElement galleryField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody"));
        Assert.assertTrue(galleryField.getText().contains(searchUnicode));
    }

    @Test
    public void TestEditGalleryNameAndTitle_ValidSymbols_EditSuccess(){
        LoginAndNavigateToNewGallery();

        WebElement backButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/a/span[1]"));
        backButton.click();

        WebElement editButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[2]/td[2]/a"));
        editButton.click();

        String name = "TestNameEdited";
        WebElement nameField = this.driver.findElement(By.id("name"));
        nameField.clear();
        nameField.sendKeys(name);

        String title = "test edited " + UUID.randomUUID().toString();
        WebElement titleField = this.driver.findElement(By.id("en[title]"));
        titleField.sendKeys(title);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement galleryField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody"));
        Boolean nameCondition = galleryField.getText().contains(name);
        Boolean titleCondition = galleryField.getText().contains(title);
        assertTrue(nameCondition && titleCondition);
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }
}
