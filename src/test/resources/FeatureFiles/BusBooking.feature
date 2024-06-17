Feature: BusBooking

Background: Application Launch
Given Launch the browser and appilication "https://www.redbus.in"

Scenario: Book a bus in RedBus application
When User enters the value in from place with one dim list
|Chennai|Hyderabad|Bangalore|Vijayawada|
And User enters the value in to place with one dim map
|location1|Hyderabad|
|location2|Delhi|
|location3|Mumbai|
|location4|Bangalore|
And User selects a data  in the Date DropDown
And User clicks on search button
Then Validate the bus displayed in the UI

Scenario: Book a bus in RedBus application
When User enters the value in from place with one dim list
|Hyderabad|chennai|Bangalore|Vijayawada|
And User enters the value in to place with one dim map
|location1|Bangalore|
|location2|chennai|
|location3|Mumbai|
|location4|Delhi|
And User selects a data  in the Date DropDown
And User clicks on search button
Then Validate the bus displayed in the UI

Scenario: Book a bus in RedBus application
When User enters the value in from place with one dim list
|Bangalore|Hyderabad|chennai|Vijayawada|
And User enters the value in to place with one dim map
|location1|Mumbai|
|location2|Delhi|
|location3|chennai|
|location4|Bangalore|
And User selects a data  in the Date DropDown
And User clicks on search button
Then Validate the bus displayed in the UI

Scenario: Book a bus in RedBus application
When User enters the value in from place with one dim list
|Vijayawada|Hyderabad|Bangalore|chennai|
And User enters the value in to place with one dim map
|location1|Bangalore|
|location2|Delhi|
|location3|Mumbai|
|location4|chennai|
And User selects a data  in the Date DropDown
And User clicks on search button
Then Validate the bus displayed in the UI