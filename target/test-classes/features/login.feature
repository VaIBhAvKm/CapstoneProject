Feature: User Login

Scenario: Successful Login with Valid Credentials
  Given the user is on the login page
  When the user enters "validuser" and "validpassword"
  And clicks the login button
  Then the user should see the dashboard
