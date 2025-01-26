Feature: Navigation and Access to Health Programs

  As a user,
  I want to navigate to different health programs and access relevant content,
  So that I can learn about and participate in various health initiatives provided by West Florida AHEC.

   #Scenario for Navigating to the Health Programs Home Page
  Scenario: User navigates to the Programs 
    Given I am on the homepage
    When I navigate to "PROGRAMS"
    Then I should see a list of available health programs like "Tobacco", "AHEC Scholars","Healthy Aging" and "Covering Florida"
		
		
   #Scenario for Navigating to a Tobacco  Program
  Scenario: User navigates to a Tobacco  program
    Given I am on the homepage
    When I navigate to "PROGRAMS"
    Then I should see a list of available health programs like "Tobacco", "AHEC Scholars","Healthy Aging" and "Covering Florida"
    And I navigate to "Tobacco"
    Then I should be redirected to the "Tobacco" page
    And I should see details about the program, including its objectives and schedule

      #Scenario for Navigating to a AHEC Scholars  Program
  Scenario: User navigates to a AHEC Scholars  program
    Given I am on the homepage
    When I navigate to "PROGRAMS"
    Then I should see a list of available health programs like "Tobacco", "AHEC Scholars","Covering Florida" and "Healthy Aging"
    And I navigate to "AHEC Scholars"
    Then I should be redirected to the "AHEC Scholars" page
    And I should see details about the program, including its objectives and schedule

       #Scenario for Navigating to a Covering Florida  Program
  Scenario: User navigates to a Covering Florida  program
    Given I am on the homepage
    When I navigate to "PROGRAMS"
    Then I should see a list of available health programs like "Tobacco", "AHEC Scholars","Covering Florida" and "Healthy Aging"
    And I navigate to "Covering Florida"
    Then I should be redirected to the "Covering Florida" page
    And I should see details about the program, including its objectives and schedule
 		
   #Scenario for Navigating to a Healthy Aging  Program
  Scenario: User navigates to a Healthy Aging  program
    Given I am on the homepage
    When I navigate to "PROGRAMS"
    Then I should see a list of available health programs like "Tobacco", "AHEC Scholars","Covering Florida" and "Healthy Aging"
    And I navigate to "Healthy Aging"
    Then I should be redirected to the "Healthy Aging" page
    And I should see details about the program, including its objectives and schedule
#--------------------------------------------------------------------------------------
  # Scenario for Accessing Program Content
  #Scenario: User accesses content of a health program
    #Given I am on the "Tobacco" program page
    #When I click on "Program Materials"
    #Then I should see a list of available content such as articles, videos, and downloadable resources
#
#
# Scenario for Navigating to the  Services 
  #Scenario: User navigates to the Services Home Page
    #Given I am on the homepage
    #When I click on "Services"
    #Then I should see a list of available health programs like "Community Based Student Education and Training" and "Continuing Education Services"
 #
  # Scenario for Accessing CPR Classes
  #Scenario: User accesses CPR classes
    #Given I am on the homepage
    #When I click on "CPR Classes"
    #Then I should be redirected to the CPR classes page
    #And I should see available CPR courses and registration options
#
  # Scenario for Registering for a Health Program
  #Scenario: User registers for a health program
    #Given I am on the "AHEC Scholars" program page
    #When I click on "Register Now"
    #And I enter my details in the registration form
    #And I click on "Submit"
    #Then I should see a confirmation message "Registration successful!"
    #And I should receive a confirmation email for the program
#
  # Scenario for Accessing Program Events or Webinars
  #Scenario: User accesses a program webinar
    #Given I am on the "Tobacco" program page
    #When I click on "Upcoming Events"
    #And I select the "Quit Tobacco Webinar" event
    #Then I should be redirected to the webinar registration page
    #And I should see details about the event, including date, time, and speakers
