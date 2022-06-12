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

public class DragAndDrop {
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
    public void dragAndDrop() throws InterruptedException{
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement elementA = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement elementB = driver.findElement(By.id("column-b"));

//        actions.moveToElement(elementA).clickAndHold(elementA).moveToElement(elementB).release(elementB).build().perform();
 //       Thread.sleep(1500);
        driver.manage().window().maximize();
        actions.dragAndDrop(elementA,elementB).perform();

        WebElement headerElementA = driver.findElement(By.xpath("//div[@id='column-a']/header"));
        Assert.assertEquals(headerElementA.getText(),"B");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
