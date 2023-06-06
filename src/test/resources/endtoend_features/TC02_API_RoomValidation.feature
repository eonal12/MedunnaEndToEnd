@e2e @api
Feature: Get and Validate Room

  Scenario: Get Request and Assert
    Given send get request
    Then validate body