# language: en
Feature: Basic addition

  Scenario: Empty input
    Given a new calculator
    When entering ""
    Then the result is 0

  Scenario: Single term
    Given a new calculator
    When entering "42"
    Then the result is 42

  Scenario: Two terms
    Given a new calculator
    When entering "1+2"
    Then the result is 3

  Scenario: Multiple terms
    Given a new calculator
    When entering "10 + 8+1"
    Then the result is 19
