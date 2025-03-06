Feature: Shopping Cart Management
  As a user
  I want to be able to delete items from my shopping cart
  So that I can manage my purchases before checkout

  Scenario: Delete an item from the shopping cart
    Given I am on the DemoBlaze home page
    And I sign up and login with a new user
    And I add "Nokia lumia 1520" to my cart
    And I add "Nexus 6" to my cart
    When I navigate to my cart
    And I delete "Nokia lumia 1520" from my cart
    Then my cart should contain 1 item
    And my cart should contain "Nexus 6"