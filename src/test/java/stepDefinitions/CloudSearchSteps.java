package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CloudSearchSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("the user opens the ZDNet cloud article")
    public void openZdnetArticle() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://www.zdnet.com/article/the-top-cloud-providers-of-2021-aws-microsoft-azure-google-cloud-hybrid-saas/");

        // Accept cookies
        try {
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("onetrust-accept-btn-handler")));
           // By.xpath("//button[contains(text(), 'Accept Cookies')]")));
            //onetrust-accept-btn-handler
            acceptBtn.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie consent popup not displayed.");
        }
    }

    @Then("the user should see {string} in the article")
    public void validateCloudProvider(String provider) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Expected provider not found: " + provider, bodyText.contains(provider));
    }

    @When("the user clicks the {string} button")
    public void clickAwsButton(String buttonText) {
        System.out.println("check the text" +buttonText);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[@class='c-shortcodeListiclePrecapItem_link'])[1]")));
        //a[contains(text(),'" + buttonText + "')]
        //span[text()='View at AWS']/ancestor::div[contains(@class,'c-shortcodeListiclePrecapItem_button')]/ancestor::a
        //(//a[contains(@href, 'aws.amazon.com')])[1]
        button.click();
    }

    @Then("a new tab should open with URL {string}")
    public void validateNewTab(String expectedUrl) {
        Set<String> handles = driver.getWindowHandles();
        List<String> tabs = new ArrayList<>(handles);
        driver.switchTo().window(tabs.get(1)); // Switch to new tab
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        Assert.assertEquals("URL mismatch", expectedUrl, driver.getCurrentUrl());
    }

    @Then("the page should contain the text {string}")
    public void pageShouldContain(String expectedText) {
        String body = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Expected text not found: " + expectedText, body.contains(expectedText));
        driver.quit();
    }
}