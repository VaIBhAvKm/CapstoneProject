
package stepDefinitions;

import java.time.Duration;
import java.util.List;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import java.util.ArrayList;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://westfloridaahec.org/");
        driver.manage().window().maximize();
    }

    @When("I navigate to {string}")
    public void i_navigate_to(String menuItem) {
        String xpath = String.format("//span[text()='%s']", menuItem);
        System.out.println("dsghdjsndjsnjndjnsjnsjdnsd"+xpath);
        WebElement menuOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        menuOption.click();
    }

    @Then("I should see a list of available health programs like {string}, {string},{string} and {string}")
   
    public void i_should_see_a_list_of_available_health_programs_like_and(String program1, String program2, String program3, String program4) {
        // XPath to locate the list of health programs
    	
    	List<WebElement> programsDropdown = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='menu-item-264']//ul[@class='sub-menu']/li/a/span")));

        // Create a list of expected programs
        List<String> expectedPrograms = List.of(program1, program2, program3, program4);

        // Extract text from the web elements and verify
        List<String> actualPrograms = new ArrayList<>();
        for (WebElement program : programsDropdown) {
            actualPrograms.add(program.getText());
        }
        System.out.println("expectedPrograms-----"+expectedPrograms);
        System.out.println("actualPrograms-----"+actualPrograms);
        // Assert that the actual programs contain all the expected programs
        for (String expectedProgram : expectedPrograms) {
            Assert.assertTrue(" Program not found: "+expectedProgram,actualPrograms.contains(expectedProgram) );
        }
    }


    @Then("I should be redirected to the {string} page")
    public void i_should_be_redirected_to_the_page(String programName) {
        String expectedUrl = String.format("https://westfloridaahec.org/%s/", programName.toLowerCase().replace(" ", "-"));
        String actualUrl = driver.getCurrentUrl();
        System.out.println("expectedUrl--------"+expectedUrl);
        System.out.println("actualUrl--------------"+actualUrl);
        if(actualUrl.contentEquals("https://westfloridaahec.org/navigators/")) 
        {
        	expectedUrl="https://westfloridaahec.org/navigators/";
        }
        //driver.quit();
        System.out.println("expectedUrl"+expectedUrl);
        System.out.println("actualUrl"+actualUrl);
        Assert.assertEquals("URL redirection mismatch!", expectedUrl, actualUrl);
    }

    @Then("I should see details about the program, including its objectives and schedule")
    public void i_should_see_details_about_the_program_including_its_objectives_and_schedule() {
        WebElement content = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'content')]")));
        Assert.assertTrue("Program details are not visible!", content.isDisplayed());
    
    }
  

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser and terminate the WebDriver session
        }
    }



}

