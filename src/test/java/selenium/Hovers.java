package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Hovers {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        actions = new Actions(driver);
    }

    @Test
    public void hovers() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        List<WebElement> pictures = driver.findElements(By.xpath("//div[@class='figure']/img"));
        int i = 1;

        for (WebElement picture:pictures) {
            actions.moveToElement(picture).click(picture).build().perform();
            WebElement userName = driver.findElement(By.xpath("//div[@class='figure'][" + i + "]/div[@class='figcaption']/h5"));

            Assert.assertEquals(userName.getText(), "name: user" + i);

            i = i + 1;
        }

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
