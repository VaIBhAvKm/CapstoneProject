
package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import java.time.Duration;
import junit.framework.Assert;

public class FormSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the {string} form page")
    public void i_am_on_the_form_page(String formName) {
        // Initialize WebDriver and navigate to the form page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.cognitoforms.com/WestFloridaAHEC/signupforhealthyagingclasses");
        driver.manage().window().maximize();
    }

    @When("I fill {string} with {string}")
    public void i_fill_with(String field, String value) {
        // Wait for the field to be visible and enter the value
    	if(field.contentEquals("Email"))
    	{
    		WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cog-2\"]")));
    		inputField.clear();
            inputField.sendKeys(value);
    	}
    	else if(field.contentEquals("Phone Number"))
    	{
    		WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cog-1\"]")));
    		inputField.clear();
            inputField.sendKeys(value);
    	}
    	else {
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='"+ field+"'and @title='" + field + "']")));
        inputField.clear();
        inputField.sendKeys(value);
    	}
    	
    }

    @When("I select a {string}")
    public void i_select_a(String value) {
        // Wait for the dropdown to be visible, select the value
    	if(!value.isBlank()) {
    		 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label//span[2]//div[normalize-space(text())='"+value+"']")));
    	        dropdown.click();
    	}
    	
    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonText) {
        // Wait for the submit button to be clickable and click it
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body"))).sendKeys(Keys.PAGE_DOWN);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cog-button__text']")));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body"))).sendKeys(Keys.PAGE_UP);
    }

    @Then("Message should be {string}")
    public void message_should_be(String expectedMessage) {
        // Wait for the success message or error message to appear
       // WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), '" + expectedMessage + "')]")));
        
        //first name
       
                                                                                                       //div[@class='cog-error-message cog-error-message--container-footer']
        
        
        //div[@class='cog-error-message cog-error-message--container-footer']
        try {
            // Code that may throw an exception
        	WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cog-confirmation__message cog-content cog-html cog-input']")));
        	String actualMessage = messageElement.getText();
            
            // Assert that the expected message is displayed
            Assert.assertEquals("Message mismatch!", expectedMessage, actualMessage);
        } catch (Exception e) {
            // Handling the exception
        	 WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cog-error-message cog-error-message--container-footer'and text()='"+expectedMessage+"']")));
        	 String actualMessage = messageElement.getText();
             
             // Assert that the expected message is displayed
             Assert.assertEquals("Message mismatch!", expectedMessage, actualMessage);
        }
        
        
    }

    @After
  public void tearDown() {
      if (driver != null) {
          driver.quit(); // Close the browser and terminate the WebDriver session
      }
  }
}
