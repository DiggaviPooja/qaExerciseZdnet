
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;


public class Test {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        //WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://www.google.co.uk/");
        driver.manage().window().maximize();

        WebElement input = driver.findElement(By.name("q"));
//        input.sendKeys("list of cloud platforms");
//        Thread.sleep(2000);
//        input.click();
        input.sendKeys("list of cloud platforms" + Keys.ENTER);

        Thread.sleep(5000);
        //driver.quit();
    }
}
