# KistlerAuthorChallenge Presentation
This is a simple API that handles the following use cases:
* User can add a message
* User can subscribe to other users
* Display all the messages posted by the user, including those posted by other users whom the user has subscribed to.
  ## Diagram class
  The API follow this diagram class where :
  * User can subscribe to 0 or many users
  * User can publish 0 or many messages
  * Message is published by a single user
    
![Diagram class](./src/main/resources/static/KistlerAuthorChallengeClassDiagram.jpg)
## Technologies
This API was developed with :
* SpringBoot 2.7.12
* MySql Data Base
## Screenshots of the project
* OverView of the database structure :
![Diagram class](./src/main/resources/static/DataBaseSnap.jpg)

* Usecase "Add a message" example :
![Diagram class](./src/main/resources/static/AddMessageSnap.jpg)

* Usecase "Get All Messages" example :
![Diagram class](./src/main/resources/static/GetAllMessageSnap.jpg)
