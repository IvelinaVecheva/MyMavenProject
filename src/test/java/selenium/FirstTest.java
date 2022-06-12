package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static io.restassured.RestAssured.given;

public class FirstTest {

    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        // 1-st approach - set driver manually
        //  System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");

        // 2-nd approach - use WebDriverManager library
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

//        Headless implementation
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
//        options.addArguments("headless", "--disable-gpu", "--ignore-certificate-errors");
//        driver = new ChromeDriver(options);

        //Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Page load wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));

        driver.manage().window().maximize();
    }

    @Test
    public void loginTest() {

        driver.get("http://training.skillo-bg.com/posts/all");

        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        List<WebElement> loginButtons = driver.findElements(By.id("nav-link-login"));
        loginButton.click();

//   Thread.sleep(2000);
        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("test51");
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("test51");
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        signInButton.click();
        WebElement newPostButton = driver.findElement(By.id("nav-link-new-post"));

        Assert.assertTrue(newPostButton.isDisplayed());
    }

    @Test
    public void dropDownTest() {

        driver.get("https://www.mobile.bg/pcgi/mobile.cgi");
        WebElement cookieConsentButton = driver.findElement(By.xpath("//div[@class='fc-dialog-container']//button[@class='fc-button fc-cta-consent fc-primary-button']//p[@class='fc-button-label']"));
        cookieConsentButton.click();
        Select dropDownMarka = new Select(driver.findElement(By.xpath("//select[@name='marka']")));
        dropDownMarka.selectByVisibleText("Volvo");
        Select dropDownModel = new Select(driver.findElement(By.xpath("//select[@name='model']")));
        dropDownMarka.selectByVisibleText("Mitsubishi");
        dropDownModel.selectByVisibleText("Lancer");

        WebElement searchButton = driver.findElement(By.xpath("//input[@id='button2']"));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        WebElement container = driver.findElement(By.xpath("//table[@class='tablereset'][2]"));

        Assert.assertTrue(container.isDisplayed());
    }

    @Test
    public void someTest() {
        given()
                .when()
                .get("http://training.skillo-bg.com:3100/posts/all")
                .then()
                .log()
                .all();
    }

    @AfterMethod
    public void tearDown(){
                driver.quit();
}

}
