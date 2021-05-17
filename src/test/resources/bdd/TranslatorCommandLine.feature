@mandatory
Feature: Translation acceptance tests working at string/commmand line public interface

  Scenario: Simple single number sequence translation
    Given sequence starts at 1
    And sequence ends at 10
    And magic number 2 translates to fizz
    When run translation
    Then output should match
    """
    1
    fizz
    3
    fizz
    5
    fizz
    7
    fizz
    9
    fizz
    """

  Scenario: Two number sequence translation
    Given sequence starts at 1
    And sequence ends at 10
    And magic number 2 translates to fizz
    And magic number 4 translates to Buzz
    When run translation
    Then output should match
    """
    1
    fizz
    3
    fizzBuzz
    5
    fizz
    7
    fizzBuzz
    9
    fizz
    """

  Scenario: Two number sequence translation with no common factors
    Given sequence starts at 1
    And sequence ends at 10
    And magic number 2 translates to fizz
    And magic number 7 translates to Buzz
    When run translation
    Then output should match
    """
    1
    fizz
    3
    fizz
    5
    fizz
    Buzz
    fizz
    9
    fizz
    """

  Scenario: Two number sequence translation with ENABLE_MATCH_NUMBER enabled
    Given sequence starts at 1
    And sequence ends at 30
    And magic number 4 translates to Buzz
    And magic number 2 translates to Fizz
    And feature ENABLE_MATCH_NUMBER is enabled
    When run translation
    Then output should match
    """
    1
    Fizz
    3
    FizzBuzz
    5
    Fizz
    7
    FizzBuzz
    9
    Fizz
    11
    FizzBuzz
    13
    FizzBuzz
    15
    FizzBuzz
    17
    Fizz
    19
    FizzBuzz
    Fizz
    Fizz
    Fizz
    FizzBuzz
    Fizz
    Fizz
    Fizz
    FizzBuzz
    Fizz
    Fizz
    """