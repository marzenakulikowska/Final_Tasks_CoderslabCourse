Feature: Address fill
  Scenario Outline: user can fill address details
    Given an opened site
    And correct email and password is entered and submitted
    And button 'Addresses' is clicked
    And button 'Create new address' is clicked
    And new address form is filled with "<alias>", "<address>", "<city>", "<postcode>", "<phone>"
    And button 'SAVE is clicked'
    Then check address with "<alias>", "<address>", "<city>", "<postcode>", "<phone>"

    Examples:
      | alias | address     | city     | postcode | phone     |
      | New   | Urokliwa 10 | Warszawa | 01-120   | 123456789 |