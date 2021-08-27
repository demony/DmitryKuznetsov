Feature: Check elements on Different Elements page

  Scenario: Different Elements Page test
    Given I open JDI GitHub site
    And I login as user "Roman Iovlev"
    When I click on "Service" button in Header
    And I click on "Different Elements" button in Service dropdown
    Then "Different Elements" page should be opened