Feature: Check elements on Different Elements page

  Scenario: Different Elements Page test
    Given I open JDI GitHub site
    When I login as 'Roman' and password 'Jdi1234'
    Then User should be logged in
    When I click on 'Service' button in Header and click on submenu 'Different Elements' in dropdown
    Then 'Different Elements' page should be opened
    When I select checkboxes 'Water, Wind'
    Then Checkboxes 'Water, Wind' should be checked
    When I select radio button 'Selen'
    Then Radio button 'Selen' should be checked
    When I select dropdown 'Yellow'
    Then Dropdown 'Yellow' should be selected
    And Log rows are displayed and corresponded to text:
      | Colors: value changed to Yellow  |
      | metal: value changed to Selen    |
      | Wind: condition changed to true  |
      | Water: condition changed to true |
