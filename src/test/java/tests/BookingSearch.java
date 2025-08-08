package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class BookingSearch {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver138.exe");
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchBooking() throws InterruptedException {
        //Destinacija
        driver.get("https://www.booking.com/");
        driver.findElement(By.cssSelector("[data-destination='1']"));
        driver.findElement(By.cssSelector("[data-destination='1']")).sendKeys("Kopaaaonik");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[data-testid='autocomplete-icon-default']")).click();


        //pretraga datuma
        driver.findElement(By.cssSelector("[data-testid='date-display-field-start']"));
        driver.findElement(By.cssSelector("[data-date='2025-08-10']")).click();
        driver.findElement(By.cssSelector("[data-date='2025-08-16']")).click();
        Thread.sleep(2000);

        //pretraga soba
        driver.findElement(By.cssSelector("[data-testid='occupancy-config-icon']")).click();
        driver.findElement(By.xpath("//*[@id=\":ri:\"]/div/div[1]/div[2]/button[1]")).click();
        Thread.sleep(2000);

        //Pretraga smestaja
        driver.findElement(By.cssSelector("[type='submit']")).click();
        Thread.sleep(2000);

        //Asertacija pretrage
        Assert.assertEquals(driver.findElement(By.cssSelector("[aria-live='assertive']")).getText(),"Kopaonik: 602 properties found");


    }

}
