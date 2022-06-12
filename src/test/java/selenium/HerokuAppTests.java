package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class HerokuAppTests {
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
    public void addRemoveElements() throws  InterruptedException{
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        List<WebElement> elementContainerChildren = driver.findElements(By.xpath("//div[@id='elements']/descendant::*"));
        Assert.assertTrue(elementContainerChildren.isEmpty());

        WebElement addElementButton = driver.findElement(By.xpath("//button[@onclick='addElement()']"));
        for (int i = 0; i < 3; i++) {
            addElementButton.click();
        }
        Thread.sleep(1500);

        elementContainerChildren = driver.findElements(By.xpath("//div[@id='elements']/descendant::*"));

        Assert.assertEquals(elementContainerChildren.size(), 3);
        Thread.sleep(1500);

    }

    @Test
    public void checkboxes()  throws  InterruptedException{
        Thread.sleep(1500);
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));

        boolean checkboxState1 = checkbox1.isSelected();
        boolean checkboxState2 = checkbox2.isSelected();

        if (checkboxState1) {
            checkbox1.click();
       }
        assertEquals(checkboxState1,checkbox1.isSelected());



        if (checkbox1.isSelected())
        {
            checkbox1.click();
            Assert.assertTrue(!checkbox1.isSelected());
        }
        else
        {
            checkbox1.click();
            Assert.assertTrue(checkbox1.isSelected());
        }

        if (checkbox2.isSelected())
        {
            checkbox2.click();
            Assert.assertTrue(!checkbox2.isSelected());
        }
        else
        {
            checkbox2.click();
            Assert.assertTrue(checkbox2.isSelected());
        }

        Thread.sleep(1500);

    }


    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
