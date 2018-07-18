# language: en
Feature: Addition and subtraction
  Adding and subtracting number

  Scenario: Basic subtraction
    Given a new calculator
    When entering "7 - 3"
    Then the result is 4

  Scenario: Mixed addition and subtraction
    Given a new calculator
    When entering "11-7+1"
    Then the result is 5

  Scenario: Negative result
    Given a new calculator
    When entering "10 - 12"
    Then the result is -2
