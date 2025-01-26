package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class HomePage {
	WebDriver driver;
ExtentReports extent;
   ExtentTest test;
	   


	@BeforeTest
	@Parameters("browser")
	public void setUp(@Optional("chrome") String browser) {
		ExtentSparkReporter spark = new ExtentSparkReporter("TestReport.html");
        extent = new ExtentReports();
      extent.attachReporter(spark);
		
		if (browser.equalsIgnoreCase("chrome")) 
		{

			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		driver.manage().window().maximize();
		driver.get("https://westfloridaahec.org/");
	}

	/*---------------	
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://westfloridaahec.org/");

		// Initialize ExtentReports and ExtentSparkReporter
		//        ExtentSparkReporter spark = new ExtentSparkReporter("target/TestReport.html");
		//        extent = new ExtentReports();
		//        extent.attachReporter(spark);
	}
------------------------------------------	*/

	@Test(priority =1)
	public void verifyHomePageSections() {
		test = extent.createTest("Verify Home Page Sections");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    Actions actions = new Actions(driver);

	    // Define locators
	    By aboutLocator = By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='About']");
	    By programsLocator = By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='PROGRAMS']");
	    By servicesLocator = By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='SERVICES']");
	    By cprLocator = By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='CPR']");
	    By contactUsLocator = By.xpath("//a[@class='fusion-bar-highlight']//span[text()='CONTACT US']");
	    By newsLocator = By.xpath("//a[@class='fusion-bar-highlight']//span[text()='NEWS']");
	    By myAccountLocator = By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='My Account']");

	    // Dropdown locators
	    By aboutDropdownLocator = By.xpath("//li[@id='menu-item-616']//ul[@class='sub-menu']");
	    By programsDropdownLocator = By.xpath("//li[@id='menu-item-264']//ul[@class='sub-menu']");
	    By servicesDropdownLocator = By.xpath("//li[@id='menu-item-331']//ul[@class='sub-menu']");
	    By cprDropdownLocator = By.xpath("//li[@id='menu-item-467']//ul[@class='sub-menu']");

	    // Verify About dropdown
	    interactWithDropdown(wait, actions, aboutLocator, aboutDropdownLocator, "ABOUT");

	    // Verify Programs dropdown
	    interactWithDropdown(wait, actions, programsLocator, programsDropdownLocator, "PROGRAMS");

	    // Verify Services dropdown
	    interactWithDropdown(wait, actions, servicesLocator, servicesDropdownLocator, "SERVICES");

	    // Verify CPR dropdown
	    interactWithDropdown(wait, actions, cprLocator, cprDropdownLocator, "CPR");

	    // Click Contact Us and navigate back
	    WebElement contactUs = wait.until(ExpectedConditions.elementToBeClickable(contactUsLocator));
	    contactUs.click();
	    driver.navigate().back();

	    // Click News and navigate back
	    WebElement news = wait.until(ExpectedConditions.elementToBeClickable(newsLocator));
	    news.click();
	    driver.navigate().back();

	    // Hover over My Account
	    WebElement myAccount = wait.until(ExpectedConditions.presenceOfElementLocated(myAccountLocator));
	    actions.moveToElement(myAccount).perform();

	    // Assertions
	    assertSectionIsDisplayed(wait, aboutLocator, "About");
	    assertSectionIsDisplayed(wait, programsLocator, "Health Programs");
	    assertSectionIsDisplayed(wait, servicesLocator, "Services");
	    assertSectionIsDisplayed(wait, cprLocator, "CPR");
	    assertSectionIsDisplayed(wait, contactUsLocator, "Contact Us");
	    assertSectionIsDisplayed(wait, newsLocator, "News");
	    assertSectionIsDisplayed(wait, myAccountLocator, "My Account");
	}

	// Helper method for dropdown interactions
	private void interactWithDropdown(WebDriverWait wait, Actions actions, By menuLocator, By dropdownLocator, String menuName) {
	    WebElement menuElement = wait.until(ExpectedConditions.presenceOfElementLocated(menuLocator));
	    actions.moveToElement(menuElement).perform();
	    List<WebElement> dropdownItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownLocator));
	    System.out.println("Displaying all the dropdown list of " + menuName + ":");
	    for (WebElement item : dropdownItems) {
	        System.out.println(item.getText());
//	        item.click();
//	        String url_check=driver.getCurrentUrl();
//	        if()
	    }
	    System.out.println();
	}

	// Helper method for assertions
	private void assertSectionIsDisplayed(WebDriverWait wait, By locator, String sectionName) {
	    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    Assert.assertTrue(element.isDisplayed(), sectionName + " section not displayed");
	    test.pass("Home Page sections verified successfully.");
	}
	/*---------------------------------------
	  public void verifyHomePageSections()  {
	 
		//test = extent.createTest("Verify Home Page Sections");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement about = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='About']")));
		WebElement healthPrograms = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='PROGRAMS']")));
		WebElement services = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='SERVICES']")));
		WebElement cpr = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='CPR']")));
		WebElement contact_us =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' ]//span[text()='CONTACT US']")));
		WebElement news = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' ]//span[text()='NEWS']")));
		WebElement my_account = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true' ]//span[text()='My Account']")));

		Actions a = new Actions(driver);
		a.moveToElement(about).perform();
		List<WebElement> about_dropdown =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='menu-item-616' and @data-item-id='616']//ul[@class='sub-menu']"))); // Update the locator
		//List<WebElement> options =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu-item-616"))); // Update the locator
		System.out.println("1. Displaying all the dropdown list of ABOUT:");
		// Print all the dropdown options
		for (WebElement abouts :  about_dropdown) {
			System.out.println(abouts.getText());
		}
		System.out.println();

		//Program dropdown
		a.moveToElement(healthPrograms).perform();
		List<WebElement> programs_dropdown =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='menu-item-264' and @data-item-id='264']//ul[@class='sub-menu']"))); // Update the locator
		//List<WebElement> options =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu-item-616"))); // Update the locator
		System.out.println("2. Displaying all the dropdown list of PROGRAMS:");
		// Print all the dropdown options
		for (WebElement program : programs_dropdown) {
			System.out.println(program.getText());
		}
		System.out.println();
		//Services dropdown
		a.moveToElement(services).perform();
		List<WebElement> services_dropdown =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='menu-item-331' and @data-item-id='331']//ul[@class='sub-menu']"))); // Update the locator
		//List<WebElement> options =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu-item-616"))); // Update the locator
		System.out.println("3. Displaying all the dropdown list of SERVICES:");
		// Print all the dropdown options
		for (WebElement service : services_dropdown) {
			System.out.println(service.getText());
		}
		System.out.println();

		//CPR dropdown
		a.moveToElement(cpr).perform();
		List<WebElement> cpr_dropdown =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='menu-item-467' and @data-item-id='467']//ul[@class='sub-menu']"))); // Update the locator
		//List<WebElement> options =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu-item-616"))); // Update the locator
		System.out.println("4. Displaying all the dropdown list of CPR:");
		// Print all the dropdown options
		for (WebElement cprs : cpr_dropdown) {
			System.out.println(cprs.getText());
		}
		System.out.println();



		contact_us.click();
		driver.navigate().back();
		news.click();
		driver.navigate().back();
		a.moveToElement(my_account).perform();



		Assert.assertTrue(about.isDisplayed(), "About section not displayed");
		Assert.assertTrue(healthPrograms.isDisplayed(), "Health Programs section not displayed");
		Assert.assertTrue(services.isDisplayed(), "Services section not displayed");
		Assert.assertTrue(cpr.isDisplayed(), "CPR section not displayed");
		Assert.assertTrue(contact_us.isDisplayed(), "Contact US section not displayed");
		Assert.assertTrue(news.isDisplayed(), "News section not displayed");
		Assert.assertTrue(my_account.isDisplayed(), "My Account section not displayed");
		// test.pass("Home page sections loaded correctly");
	}
	---------------------------*/
	@Test(priority = 2)
	public void testSearchBarFunctionality() {
		// Test the search bar functionality on the Home Page
		test = extent.createTest("Test Search Bar Functionality");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search' and @placeholder='Search...' and @class='s' ]"))); // Adjust the selector as per actual ID
		searchBar.click();
		searchBar.sendKeys("programs");
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit' and @class='fusion-search-submit searchsubmit']")));
		searchButton.click();

		//WebElement searchResults = driver.findElement(By.xpath("/html/body/div[8]/div/main/div/section"));
		WebElement searchResults = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='entry-title']")));
		Assert.assertTrue(searchResults.isDisplayed(), "Search results not displayed.");
		driver.navigate().back();
		test.pass("Search functionality verified successfully.");
	}
	//    @Test(priority = 3)
	//    public void testContactUsFormSubmission() {
	//        // Validate the form submission process on the Contact Us page
	//
	//        WebElement contactUsLink = driver.findElement(By.linkText("Contact Us"));
	//        contactUsLink.click();
	//
	//        WebElement nameField = driver.findElement(By.id("name"));
	//        WebElement emailField = driver.findElement(By.id("email"));
	//        WebElement messageField = driver.findElement(By.id("message"));
	//        WebElement submitButton = driver.findElement(By.id("submit"));
	//
	//        nameField.sendKeys("Test User");
	//        emailField.sendKeys("testuser@example.com");
	//        messageField.sendKeys("This is a test message.");
	//        submitButton.click();
	//
	//        WebElement confirmationMessage = driver.findElement(By.id("/html/body/div[8]/div/main/div/section/div/div/div/div[1]/div/div"));
	//        Assert.assertTrue(confirmationMessage.isDisplayed(), "Form submission failed.");
	//    }
	@Test(priority = 3)
	public void testUserRegistration()  {
		test = extent.createTest("Test User Registration");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Automate user registration process
		WebElement my_account = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true' ]//span[text()='My Account']")));
//		Actions a = new Actions(driver);
//		a.moveToElement(my_account).perform();
//		WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fusion-menu-login-box-register']")));
//		registerLink.click();
		my_account.click();
		WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='woocommerce-Input woocommerce-Input--text input-text' and @id='reg_username']")));
		WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='woocommerce-Input woocommerce-Input--text input-text' and @id='reg_email']")));
		WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='woocommerce-Input woocommerce-Input--text input-text' and @id='reg_password']")));
		
		WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='woocommerce-Button woocommerce-button button woocommerce-form-register__submit' and @type='submit']")));

		usernameField.sendKeys("vaibhavKumar1111ddd89");
		passwordField.sendKeys("PassworD@122983223");
		emailField.sendKeys("vaibhavk11k11kk@gmail.com");
		//String email_str= emailField.getText();
		registerButton.click();

		//Scenario if already registered with the same email id
		WebElement already_exist = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='wc-block-components-notice-banner__content']//strong[text()='Error:']")));
		String already_exist_str=already_exist.getText();
		WebElement email_exit=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='woocommerce-Input woocommerce-Input--text input-text' and @id='reg_email']")));
		String email_exit_str=email_exit.getAttribute("value");
		// System.out.println("check11111111 :"+email_exit_str);
		if(already_exist_str.equals("Error:"))
		{
			System.out.println("Already Registered with email id and username :"+email_exit_str);
			Assert.assertEquals(email_exit_str,"vaibhavk11k11kk@gmail.com", "Registration failed: inside if Text does not match.");

		}
		else {
			//        	WebElement registrationConfirmation = driver.findElement(By.cssSelector("#post-381 > div > div > div.avada-myaccount-user > div.avada-myaccount-user-column.username > span.hello > strong"));
			//        	String registrationConfirmation_str=registrationConfirmation.getText();
			//        	System.out.println("else print"+registrationConfirmation_str);
			//            Assert.assertTrue(registrationConfirmation.isDisplayed(), "Registration failed: Confirmation element is not displayed.");
			//
			//            
			//           

			WebElement registrationConfirmation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='avada-myaccount-user-column username']//span[@class='hello']//strong")));
			// Retrieve the text and compare it with the expected value
			String actualText = registrationConfirmation.getText();
			Assert.assertEquals(actualText, "vaibhavKumar1111ddd89", "Registration failed: Text does not match.");
		}

		//      


		test.pass("Test User Registration verified successfully.");

	}

	@Test(priority=4)
	public void testUserLogin() {
		//driver.navigate().back();
		//test = extent.createTest("Test User Login");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement my_account = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true' ]//span[text()='My Account']")));
		my_account.click();
		WebElement loginUsername = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='woocommerce-Input woocommerce-Input--text input-text' and @id='username']"))); // Updated XPath
		WebElement loginPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='woocommerce-Input woocommerce-Input--text input-text' and @id='password']")));


		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit' and @type='submit']"))); // Updated XPath

		loginUsername.sendKeys("vaibhavKumar1111ddd89");
		loginPassword.sendKeys("PassworD@122983223");
		loginButton.click();

		WebElement loginSuccess = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='hello']//strong"))); // Updated XPath
		Assert.assertTrue(loginSuccess.isDisplayed(), "Login failed");
		test.pass("Test User Login verified successfully.");
	}
	
	@Test(priority=5)
	public void testPasswordRecovery() {
		test = extent.createTest("Test Password Recovery");
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement already_loggedin = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div[1]/span[1]/strong")));
		if(already_loggedin.isDisplayed())
		{
			WebElement signout_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div[1]/span[2]/a")));
			signout_button.click();
			WebElement confirm_signout_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-381\"]/div/div/div[2]/div/div/div/a")));
			confirm_signout_button.click();
		}
		WebElement my_account = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true' ]//span[text()='My Account']")));
		my_account.click();
		WebElement lost_password = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customer_login']/div[1]/form/p[4]/a")));
		lost_password.click();
		WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='user_login']")));
		username.sendKeys("vaibhavKumar1111ddd89");
		WebElement reset_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='post-381']/div/div/form/p[3]/button")));
		reset_button.click();
		WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='post-381']/div/div/div/div")));
		Assert.assertEquals(message.getText(), "Password reset email has been sent.","Password email not sent");
		test.pass("Test Password Recovery verified successfully.");
	}

	@Test(priority=6)
	public void testNavigationToHealthPrograms()  {
		test = extent.createTest("Test Navigation To HealthPrograms");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions a = new Actions(driver);

		// Navigate to 'Tobacco'
		WebElement healthPrograms = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='PROGRAMS']")));
		a.moveToElement(healthPrograms).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menu-item-344\"]/a/span"))).click();
		String tobacco_url = driver.getCurrentUrl();
		String actual_tobacco_url = "https://westfloridaahec.org/tobacco/";
		Assert.assertEquals(actual_tobacco_url, tobacco_url, "Tobacco Url does not match");
		driver.navigate().refresh();
		
		// Navigate to 'AHEC Scholars'
		healthPrograms = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='PROGRAMS']")));
		a.moveToElement(healthPrograms).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fusion-bar-highlight']//span[text()='AHEC Scholars']"))).click();
		String aHEC_Scholars_url = driver.getCurrentUrl();
		String actual_aHEC_Scholars_url = "https://westfloridaahec.org/ahec-scholars/";
		Assert.assertEquals(actual_aHEC_Scholars_url, aHEC_Scholars_url, "AHEC Scholars Url does not match");
		driver.navigate().refresh();
		
		// Navigate to 'Healthy Aging'
		healthPrograms = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='PROGRAMS']")));
		a.moveToElement(healthPrograms).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fusion-bar-highlight']//span[text()='Healthy Aging']"))).click();
		String healthy_Aging_url = driver.getCurrentUrl();
		String actual_healthy_Aging_url = "https://westfloridaahec.org/healthy-aging/";
		Assert.assertEquals(actual_healthy_Aging_url, healthy_Aging_url, "Healthy Aging Url does not match");
		driver.navigate().refresh();
		
		// Navigate to 'Covering Florida'
		healthPrograms = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='PROGRAMS']")));
		a.moveToElement(healthPrograms).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fusion-bar-highlight']//span[text()='Covering Florida']"))).click();
		String covering_Florida_url = driver.getCurrentUrl();
		String actual_covering_Florida_url = "https://westfloridaahec.org/navigators/";
		Assert.assertEquals(actual_covering_Florida_url, covering_Florida_url, "Covering Florida Url does not match");
		test.pass("Test Navigation To HealthPrograms verified successfully.");
	}

	@Test(priority =7)
	public void testNavigationToServices() {
		test = extent.createTest("Test Navigation To Services");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions a = new Actions(driver);

		// Navigate to 'Community Based Student Education and Training'
		WebElement services = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='SERVICES']")));
		a.moveToElement(services).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-item-440\"]/a/span"))).click();
		String community_base_url = driver.getCurrentUrl();
		String actual_community_base_url = "https://westfloridaahec.org/community-based-student-education-and-training/";
		Assert.assertEquals(actual_community_base_url, community_base_url, "Community Based Student Education and Training Url does not match");

		// Navigate to 'Continuing Education Services'
		services = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='SERVICES']")));
		a.moveToElement(services).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-item-330\"]/a/span"))).click();
		String continuingEducation_url = driver.getCurrentUrl();
		String actual_continuingEducation_url = "https://westfloridaahec.org/continuing-education-services/";
		Assert.assertEquals(actual_continuingEducation_url, continuingEducation_url, "Continuing Education Services Url does not match");

	test.pass("Test Navigation To Services verified successfully.");

	}
	@Test(priority=8)
	public void testFormSubmmission() {
		test = extent.createTest("Test Form Submission ");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Navigate to 'Form'
				 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fusion-bar-highlight' and @aria-haspopup='true']//span[text()='PROGRAMS']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fusion-bar-highlight']//span[text()='Healthy Aging']"))).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='fusion-text fusion-text-2']//a")));
				
				WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='First' and @title='First']")));
				firstName.clear();
				firstName.sendKeys("Vaibhav");
				
				WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Last'and @title='Last']")));
				lastName.clear();
				lastName.sendKeys("Kumar");
				
				WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cog-1\"]")));
				phoneNumber.clear();
				phoneNumber.sendKeys("9394893893");
				
				WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cog-2\"]")));
				email.clear();
				email.sendKeys("vaibhav@gmail.com");
				
				WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label//span[2]//div[normalize-space(text())='Walton']")));
    	        dropdown.click();
				
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body"))).sendKeys(Keys.PAGE_DOWN);
    	        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cog-button__text']")));
    	        submitButton.click();
				
    	        
    	        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cog-confirmation__message cog-content cog-html cog-input' and @role='alert']")));
            	String actualMessage = messageElement.getText();
            	//System.out.println("v---------------"+actualMessage);
            	
            	
            	Assert.assertEquals(actualMessage,"Thank you for filling out the form. Your response has been recorded.","Message mismatch!");
            	test.pass("Test Form Submission verified successfully.");
	} 

	@AfterTest

	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		        if (extent != null) {
		            extent.flush();
		        }
	}


	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		Runnable chromeTest = () -> {
			HomePage test = new HomePage();
			test.setUp("chrome");
			test.testNavigationToServices();
			test.tearDown();

		};

		Runnable firefoxTest = () -> {

			HomePage test = new HomePage();
			test.setUp("firefox");
			test.testNavigationToServices();
			test.tearDown();

		};

		Runnable edgeTest = () -> {
			HomePage test = new HomePage();
			test.setUp("edge");
			test.testNavigationToServices();
			test.tearDown();

		};

		executor.submit(chromeTest);
		executor.submit(firefoxTest);
		executor.submit(edgeTest);

		executor.shutdown();
	}
}

