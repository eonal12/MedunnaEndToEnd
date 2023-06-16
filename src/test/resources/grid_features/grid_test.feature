@grid_features
Feature: grid_feature

  @grid_chrome
  Scenario: TC01_running_on_chrome
    Given user goes to app with chrome
    When verify the title is "Blue Rental Cars | Cheap, Hygienic, VIP Car Hire"
    Then close the driver

  @grid_firefox
  Scenario: TC02_running_on_firefox
    Given user goes to app with firefox
    When verify the title is "Blue Rental Cars | Cheap, Hygienic, VIP Car Hire"
    Then close the driver

  @grid_edge
  Scenario: TC02_running_on_edge
    Given user goes to app with edge
    When verify the title is "Blue Rental Cars | Cheap, Hygienic, VIP Car Hire"
    Then close the driver