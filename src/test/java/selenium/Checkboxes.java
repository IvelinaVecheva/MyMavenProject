package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class Checkboxes {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void checkboxes()  throws  InterruptedException{
        Thread.sleep(1500);
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));

        boolean checkboxState1 = checkbox1.isSelected();
        boolean checkboxState2 = checkbox2.isSelected();

        if (!checkboxState1) {
            checkbox1.click();
        }
        Assert.assertTrue(checkbox1.isSelected());

        if (!checkboxState2) {
            checkbox2.click();
        }
        Assert.assertTrue(checkbox2.isSelected());


        Thread.sleep(1500);

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
