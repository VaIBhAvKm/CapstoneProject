
//----------------------FINAL_____________________________________________________
package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import io.cucumber.java.After;
import io.cucumber.java.en.And;

public class UserManagementSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(600));
        driver.get("https://westfloridaahec.org/my-account/");
        driver.manage().window().maximize();
    }

    @When("I fill in {string} {string} and {string}")
    public void i_fill_in_and(String username, String email, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_username")));
        usernameField.sendKeys(username);
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_email")));
        emailField.sendKeys(email);
        
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_password")));
        passwordField.sendKeys(password);
    }


    @When("I fill in  {string} and {string}")
    public void i_fill_in_and(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    	 System.out.println("USername"+string);
         System.out.println("password"+string2);
     	WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']")));
         usernameField.sendKeys(string);

         WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password']")));
         passwordField.sendKeys(string2);
    }






    @When("I click on {string}")
    public void i_click_on(String action) {
        String button = action.toLowerCase();
        System.out.println("name button:"+button);
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(button)));
        buttonElement.click();
    }

//    @Then("I should see {string}")
//    public void i_should_see(String expectedMessage) {
//        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='" + expectedMessage + "']")));
//        String actualMessage = successMessage.getText();
//        Assert.assertEquals("Message mismatch!", expectedMessage, actualMessage);
//    }
    @Then("I should see {string}")
    public void i_should_see(String string) {
//    String text;
//    	if(string.contentEquals("Account details changed successfully.")) {
//    		WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='post-381']/div/div/div[2]/div/div/div")));
//    	text=successMessage.getText();
//    	}
//    	else {
//    	WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='avada-myaccount-user-column username']//span[@class='hello']//strong")));
//    	text=successMessage.getText();
//    	}
//    	System.out.println("TExt:----------"+text);
//    	Assert.assertTrue("String matched :",text.contains(string));
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    	
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='post-381']/div/div/div[1]/div[1]/span[1]/strong")));
        System.out.println("-------------------------sdsfdsdfdf"+string);
        
        String actual_successMessage=successMessage.getText();
        System.out.println("-------------------------actual_successMessage"+actual_successMessage);
        Assert.assertEquals("String message mismatch!", string, actual_successMessage);
        //Assert.assertEquals(successMessage.isDisplayed());
    }
    
    
    @Then("I should see Hello {string}")
    public void i_should_see_hello(String string) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	String alreadyregistered_str = "";
    	try {
    	    alreadyregistered_str = (wait.until(ExpectedConditions.presenceOfElementLocated(
    	        By.xpath("//*[@id='post-381']/div/div/div[1]/div/div")))).getText();
    	    if(!alreadyregistered_str.isBlank()) {
       		System.out.println("User is already Registered with the same credentials");
       		Assert.assertTrue(alreadyregistered_str, true);
    	    }
    	} catch (Exception e) {
    	    System.out.println("User is new ...");
    	    
    	    
    	    WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='post-381']/div/div/div[1]/div[1]/span[1]/strong")));
    	    System.out.println("-------------------------sdsfdsdfdf"+string);
    	    
    	    String actual_successMessage=successMessage.getText();
    	    System.out.println("-------------------------actual_successMessage"+actual_successMessage);
    	    Assert.assertEquals("String message mismatch!", string, actual_successMessage);
    	}
    }

    
//    	String alreadyregistered_str=(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='post-381']/div/div/div[1]/div/div")))).getText();
//    	//for alreadyregistered 
//    	if(!alreadyregistered_str.isBlank()) {
//    		System.out.println("User is already Registered with the same credentials");
//    		Assert.assertTrue(alreadyregistered_str, false);
//    		
//    		
//    	}
//    	else {
//    		WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='post-381']/div/div/div[1]/div[1]/span[1]/strong")));
//    	    System.out.println("-------------------------sdsfdsdfdf"+string);
//    	    
//    	    String actual_successMessage=successMessage.getText();
//    	    System.out.println("-------------------------actual_successMessage"+actual_successMessage);
//    	    Assert.assertEquals("String message mismatch!", string, actual_successMessage);
//    	}
//    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser and terminate the WebDriver session
        }
    }
}
