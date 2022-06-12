package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Dropdown {
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
    public void dropdown() throws InterruptedException{
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdownButton = driver.findElement(By.id("dropdown"));
        dropdownButton.click();
        Select dropdownOptions = new Select(dropdownButton);
        dropdownOptions.selectByValue("1");

        WebElement option1 = driver.findElement(By.xpath("//option[@value='1']"));
        Assert.assertEquals(option1.getText(),"Option 1");

        Thread.sleep(1500);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
