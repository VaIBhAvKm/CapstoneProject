Feature: User Account Management

  As a user
  I want to register, log in, and manage my profile
  So that I can access health programs and events
   #Scenario for User Registration
  Scenario: User registers for the program
    Given I am on the registration page
    When I fill in "<Username>" "<Email address>" and "<Password>"
    And I click on "Register"
  Then I should see Hello "<Username>"

   
    
     Examples:
      | Email address    | Username        				| Password      |
      | vk@batch12222222.com    | VaibhavKumar_Batch12@2222    | Correct_@16157|
      


		
  # Scenario for User Login(Logging in with Email Address)
  Scenario: User logs in with valid email
     Given I am on the registration page
    When I fill in  "<Email address>" and "<Password>"
    And I click on "Login"
Then I should see "<Username>"

    Examples:
      | Email address    | Username        				| Password      |
      | vk@batch12.com    | VaibhavKumar_Batch12    | Correct_@16157|
#
# Scenario for User Login(With Username)
  Scenario: User logs in with valid username
 Given I am on the registration page
    When I fill in  "<Username>" and "<Password>"
    And I click on "Login"
    Then I should see "<Username>"

    Examples:
      | Email address    | Username        				| Password      |
      | vk@batch12.com    | VaibhavKumar_Batch12    | Correct_@16157|
  
  # Scenario for Profile Management(Updates email address)
  #Scenario: User views and updates their profile with new email
  #Given  User logs in with valid credentials
 #		 When I navigate to the "Account details" page
    #Then I fill in "<account_first_name>" and "<account_last_name>" 
    #When I update my "<Email address>" 
    #And I click on "save_account_details"
    #Then I should see "Account details changed successfully."
    #
    #Examples:
      #| Email address    | Username        				| Password      |
      #| new.vk@batch1.com    | VaibhavKumar_Batch12    | Correct_@16157|
      #
      #
      #------------------------------------------------------------
    #Given I am logged in as "VaibhavKumar_Batch1"
    #When I navigate to the "Account details" page
    #Then I should enter "First name: V" and "Last name: k"
    #Then I should see "Display name: VaibhavKumar_Batch1" and "Email address: vk@batch1.com"
    #When I update my "Email address" to "new.vk@batch1.com"
    #And I click on "Save Changes"
    #Then I should see "Account details changed successfully"
    #And I should see "Email: new.vk@batch1.com" on the profile page
    #
     #Scenario for Profile Management(Updates new password)
  #Scenario: User views and updates their profile
    #Given I am logged in as "VaibhavKumar_Batch1"
    #When I navigate to the "Account details" page
    #Then I should enter "First name: V" and "Last name: k"
    #Then I should see "Display name: VaibhavKumar_Batch1" and "Email address: vk@batch1.com"
    #When I update my "Email address" to "new.vk@batch1.com"
    #And I click on "Save Changes"
    #Then I should see "Account details changed successfully"
    #And I should see "Email: new.vk@batch1.com" on the profile page
