Feature: Goibibo application -Login

  Scenario: Flight Booking by using goibibo application
    Given user is in login page
    When Click on Flights
    Then enter from value
    Then enter to value
    When select departure date
    Then Click on done btn
    Then Click on adults
    Then Click on children
    Then click on infants
    Then click on done Add people
    Then click on search Flights btn
    Then showing total number flights for departure and return
    Then Get all the flight details
    When Select last flight
    Then list and capture all details for Economy
    Then verify fare summary
    When Verify all error messages in the page
    Then Enter all mandatory fields in the page and click on Proceed
    Then Select 14a, 14b,14c ,14d from the seats
    Then Proceed to Payment
    Then Capture all details from the confirm booking
