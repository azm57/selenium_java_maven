
Feature: Craftsvilla Application

#Scenario: User Registration

#Given Launch Browser and navigate to application
	#And Read Test Data
	#When Go to registration and enter all the data and click register
	#Then Verify successful registration

@smoke
Scenario: Add Products To Cart and Verify

	Given Launch Browser and navigate to application
	And Read Test Data
	When User Login to Application with valid Credentials
	#And User navigates to Watchlist Page and Verifies the rows

Scenario: Repeat - Add Products To Cart and Verify

	Given Launch Browser and navigate to application
	And Read Test Data
	When User Login to Application with valid Credentials
	And User navigates to Watchlist Page and Verifies the rows
