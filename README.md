# FlightOffersSubscrption
This is a web application which allows users to subscribe to flight offers.

Below are the scenarios coverd by the application:

If user is subsribing for the flight origin which does not exists in the database then showing message to the user "There is no offer exists for the flight origin <origin> and destination <destination>. We will send offer when found.". Also the confirmation mail is sent to user.
  
If there were no offers in the database when user subscribed then regardless of users subscription, if the offer is saved by Crawller for the same origin and destination (first time), the email with offer details is sent to the subscribed user.

When user subscribes and there are multiple offers existing for the same origin and destination in the database then application gets the best priced offer and checks if the date is in future then send the first offer mail to user.

If user goes to UI and subscibes for the second time, then confirmation mail is not send as user was already present in the database.
