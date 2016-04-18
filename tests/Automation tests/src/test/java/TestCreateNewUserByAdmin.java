import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class TestCreateNewUserByAdmin {
    private WebDriver driver;

    @Before
    public void setUp(){
        this.driver = new FirefoxDriver();
        this.driver.get("http://typi-test.webstars.bg/auth/login");
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    private void LoginAndNavigateToNewUserPanel(){
        WebElement emailField = this.driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("dani@plam.ch");

        WebElement passwordField = this.driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("qwe123");

        WebElement loginButton = this.driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[4]/button"));
        loginButton.click();

        WebElement adminSideButton = this.driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[1]/a"));
        adminSideButton.click();

        WebElement userButton = this.driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[4]/ul/li[1]/a[1]/div"));
        userButton.click();

        WebElement newUserButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/a/i"));
        newUserButton.click();
    }

    @Test
    public void TestCreateNewUserByAdmin_ValidCredentials_UserShouldBeCreated(){
        LoginAndNavigateToNewUserPanel();

        String newUserEmail = "name" +  UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.id("email"));
        emailField.sendKeys(newUserEmail);

        String password = "12345678";
        WebElement passwordField = this.driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement passwordConfirmationField = this.driver.findElement(By.id("password_confirmation"));
        passwordConfirmationField.sendKeys(password);

        String firstName = "firstName" + UUID.randomUUID().toString();
        WebElement firstNameField = this.driver.findElement(By.id("first_name"));
        firstNameField.sendKeys(firstName);

        String lastName = "lastName" + UUID.randomUUID().toString();
        WebElement lastNameField = this.driver.findElement(By.id("last_name"));
        lastNameField.sendKeys(lastName);

        WebElement activatedCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[5]/div[1]/label/input"));
        activatedCheckButton.click();

        WebElement visitorCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[6]/div[2]/label/input"));
        visitorCheckButton.click();

        WebElement saveButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/button[2]"));
        saveButton.click();

        WebElement greetingSign = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/h1"));
        assertEquals((firstName + " " + lastName), greetingSign.getText());
    }

    @Test
    public void TestCreateNewAdminByAdmin_ValidCredentials_UserShouldBeCreated(){
        LoginAndNavigateToNewUserPanel();

        String newUserEmail = "name" +  UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.id("email"));
        emailField.sendKeys(newUserEmail);

        String password = "12345678";
        WebElement passwordField = this.driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement passwordConfirmationField = this.driver.findElement(By.id("password_confirmation"));
        passwordConfirmationField.sendKeys(password);

        String firstName = "firstName" + UUID.randomUUID().toString();
        WebElement firstNameField = this.driver.findElement(By.id("first_name"));
        firstNameField.sendKeys(firstName);

        String lastName = "lastName" + UUID.randomUUID().toString();
        WebElement lastNameField = this.driver.findElement(By.id("last_name"));
        lastNameField.sendKeys(lastName);

        WebElement activatedCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[5]/div[1]/label/input"));
        activatedCheckButton.click();

        WebElement administratorCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[6]/div[1]/label/input"));
        administratorCheckButton.click();

        WebElement saveButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/button[2]"));
        saveButton.click();

        WebElement greetingSign = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/h1"));
        assertEquals((firstName + " " + lastName), greetingSign.getText());
    }

    @Test
    public void TestCreateNewAdminSuperUserByAdmin_ValidCredentials_UserShouldBeCreated(){
        LoginAndNavigateToNewUserPanel();

        String newUserEmail = "name" +  UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.id("email"));
        emailField.sendKeys(newUserEmail);

        String password = "12345678";
        WebElement passwordField = this.driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement passwordConfirmationField = this.driver.findElement(By.id("password_confirmation"));
        passwordConfirmationField.sendKeys(password);

        String firstName = "firstName" + UUID.randomUUID().toString();
        WebElement firstNameField = this.driver.findElement(By.id("first_name"));
        firstNameField.sendKeys(firstName);

        String lastName = "lastName" + UUID.randomUUID().toString();
        WebElement lastNameField = this.driver.findElement(By.id("last_name"));
        lastNameField.sendKeys(lastName);

        WebElement activatedCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[5]/div[1]/label/input"));
        activatedCheckButton.click();

        WebElement superUserCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[5]/div[2]/label/input"));
        superUserCheckButton.click();

        WebElement administratorCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[6]/div[1]/label/input"));
        administratorCheckButton.click();

        WebElement saveButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/button[2]"));
        saveButton.click();

        WebElement greetingSign = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/h1"));
        assertEquals((firstName + " " + lastName), greetingSign.getText());
    }

    @Test
    public void TestCreateNewAdminAndVisitorByAdmin_ValidCredentials_UserShouldBeCreated(){
        LoginAndNavigateToNewUserPanel();

        String newUserEmail = "name" +  UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.id("email"));
        emailField.sendKeys(newUserEmail);

        String password = "12345678";
        WebElement passwordField = this.driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement passwordConfirmationField = this.driver.findElement(By.id("password_confirmation"));
        passwordConfirmationField.sendKeys(password);

        String firstName = "firstName" + UUID.randomUUID().toString();
        WebElement firstNameField = this.driver.findElement(By.id("first_name"));
        firstNameField.sendKeys(firstName);

        String lastName = "lastName" + UUID.randomUUID().toString();
        WebElement lastNameField = this.driver.findElement(By.id("last_name"));
        lastNameField.sendKeys(lastName);

        WebElement activatedCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[5]/div[1]/label/input"));
        activatedCheckButton.click();

        WebElement administratorCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[6]/div[1]/label/input"));
        administratorCheckButton.click();

        WebElement visitorCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[6]/div[2]/label/input"));
        visitorCheckButton.click();

        WebElement saveButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/button[2]"));
        saveButton.click();

        WebElement greetingSign = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/h1"));
        assertEquals((firstName + " " + lastName), greetingSign.getText());
    }

    @Test
    public void TestCreateNewUserByAdmin_ValidCredentialsWithoutActivated_UserShouldBeCreated(){
        LoginAndNavigateToNewUserPanel();

        String newUserEmail = "name" +  UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.id("email"));
        emailField.sendKeys(newUserEmail);

        String password = "12345678";
        WebElement passwordField = this.driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement passwordConfirmationField = this.driver.findElement(By.id("password_confirmation"));
        passwordConfirmationField.sendKeys(password);

        String firstName = "firstName" + UUID.randomUUID().toString();
        WebElement firstNameField = this.driver.findElement(By.id("first_name"));
        firstNameField.sendKeys(firstName);

        String lastName = "lastName" + UUID.randomUUID().toString();
        WebElement lastNameField = this.driver.findElement(By.id("last_name"));
        lastNameField.sendKeys(lastName);

        WebElement saveAndExitButton = this.driver.findElement(By.id("exit"));
        saveAndExitButton.click();

        WebElement activatedFieldForUsers = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[3]/td[6]"));
        assertEquals("0", activatedFieldForUsers.getText());
    }

    @Test
    public void TestCreateNewUserByAdmin_InvalidPassword_ShouldBeThrowAlert(){
        LoginAndNavigateToNewUserPanel();

        String newUserEmail = "name" +  UUID.randomUUID().toString() + "@abv.bg";
        WebElement emailField = this.driver.findElement(By.id("email"));
        emailField.sendKeys(newUserEmail);

        String invalidPassword = "1234";
        WebElement passwordField = this.driver.findElement(By.id("password"));
        passwordField.sendKeys(invalidPassword);

        WebElement passwordConfirmationField = this.driver.findElement(By.id("password_confirmation"));
        passwordConfirmationField.sendKeys(invalidPassword);

        String firstName = "firstName" + UUID.randomUUID().toString();
        WebElement firstNameField = this.driver.findElement(By.id("first_name"));
        firstNameField.sendKeys(firstName);

        String lastName = "lastName" + UUID.randomUUID().toString();
        WebElement lastNameField = this.driver.findElement(By.id("last_name"));
        lastNameField.sendKeys(lastName);

        WebElement activatedCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[5]/div[1]/label/input"));
        activatedCheckButton.click();

        WebElement visitorCheckButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[6]/div[2]/label/input"));
        visitorCheckButton.click();

        WebElement saveButton = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/button[2]"));
        saveButton.click();

        WebElement alertField = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div"));
        assertEquals("×\n" +
                "The form contains errors.\n" +
                "The Password must be at least 8 characters.", alertField.getText());
    }


    @After
    public void tearDown(){
        this.driver.quit();
    }
}
