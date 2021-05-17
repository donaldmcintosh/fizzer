## Fizz Project

This project iterates over a sequence of numbers and converts certain
'magic numbers' to configured text alternatives.  

## Executing

To run using the minitest config do the following command:

	mvn spring-boot:run -Dspring-boot.run.profiles=minitest

This configuration is held in resources/application-minitest.yml, and for minitest it
has the following settings:

* Iterates from 1 to 100
* Magic numbers of 3 & 5
* If number is divisible by a magic number it generates Fizz for 3 and Buzz for 5
* If the number simply contains either magic number it will also translate to
  text 

And you will see output something like this:

	1
	2
	Fizz
	4
	Buzz
	Fizz
	7
	8
	Fizz
	Buzz
	11
	Fizz
	Fizz
	14
	FizzBuzz
	...

## Tests

To run all tests run 

	mvn clean test

This will run the unit tests and acceptance Cucumber tests.

Normally the Cucumber tests would sit outside the core service/implementation
but for convenience I have embedded them here.

## Configuration

Here is an example configuration file:

	fizz:
	  start: 1
	  max: 100
	  magicNumbers:
	    3: "Fizz"
	    5: "Buzz"
	  features:
	    ENABLE_MATCH_NUMBER


## Features

ENABLE_MATCH_NUMBER is an optional feature where fizzer will not only translate 
when a sequence number has a magic number as factor, but also simply if the
sequence number contains the magic number.

For example, if 21 is not divisible by 2.  If ENABLE_MATCH_NUMBER was disabled,
this would not translate.  If it was enabled, it would because it contains the
number 2.

