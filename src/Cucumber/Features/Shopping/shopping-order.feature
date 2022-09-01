Feature: Shopping Order
  Scenario: user can buy an item
    Given an opened site
    And correct email and password is entered and submitted
    And button 'CLOTHES' is clicked
    And choose correct size
    And choose correct quantity
    And add item to cart
    And go to checkout
    And confirm address
    And choose shipping method
    And choose payment method
    And click agreement checkbox and obligation to pay button
    Then take a screenshot



