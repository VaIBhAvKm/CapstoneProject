Feature: Healthy Aging Classes Signup Form
  As a user, I want to sign up for healthy aging classes by filling out the form 
  So that I can participate in the program.

  Background:
    Given I am on the "Sign Up for Healthy Aging Classes" form page

  Scenario Outline: Form submission with various inputs
    When I fill "First" with "<FirstName>"
    And I fill "Last" with "<LastName>"
    And I fill "Email" with "<Email>"
    And I fill "Phone Number" with "<PhoneNumber>"
    And I select a "<Country>"
    And I click the "Submit" button
    Then Message should be "<ExpectedMessage>"

 Examples:
 | FirstName | LastName | Email               | PhoneNumber    | Country	 | ExpectedMessage                                           |
 | John      | Doe      | john.doe@example.com| 123-456-7890   | Escambia	 | Thank you for filling out the form. Your response has been recorded.|
 |           | Doe      | john.doe@example.com| 123-456-7890   | Okaloosa  | First is required.                                  |
 | John      |          | john.doe@example.com| 123-456-7890   | Walton    | Last is required.                                   |
 | John      | Doe      | invalid-email       | 123-456-7890   | Okaloosa  | Email must be formatted as name@address.xyz.                      |
 | John      | Doe      | john.doe@example.com| 123            | Walton    | Phone must be formatted as ###-###-#### x####.                       |
 | John      | Doe      | john.doe@example.com| 123-456-7890   |           | Which county do you live in?  is required.       |

																																						