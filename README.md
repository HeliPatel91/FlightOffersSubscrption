# FlightOffersSubscription
This is a web application which allows users to subscribe to flight offers.

Below are the scenarios coverd by the application:

If user is subsribing for the flight origin which does not exists in the database then showing message to the user "There is no offer exists for the flight origin <origin> and destination <destination>. We will send offer when found.". Also the confirmation mail is sent to user.
  
If there were no offers in the database when user subscribed then regardless of users subscription, if the offer is saved by Crawller for the same origin and destination (first time), the email with offer details is sent to the subscribed user.

When user subscribes and there are multiple offers existing for the same origin and destination in the database then application gets the best priced offer and checks if the date is in future then send the first offer mail to user.

If user goes to UI and subscibes for the second time, then confirmation mail is not send as user was already present in the database.


Below are the limitation of application:

If the user has subscribed for monthly and he received the best offer when subscribed, post one month duration the best offer is not found from Crawler then offer mail will not be shared to user.

Below are the assumptions made:

Crawler is sending all the required details.
Crawler is sending the offers everyday by calling save API.

Below are the major functionalities handles by the application:
1) UI to provide the subscription details
2) Appropriate messages on UI.
3) All the fields are not provided in the form then showing appropriate message.
4) Swagger to fire the API requests
5) Get and post request for crawler
6) All the save request from crawler saves the offer details and check if this offer is the best offer. If this offer is best offer then it checks for all the users subscribed to this origin and destination and send mail according to the user subscribed frequency
