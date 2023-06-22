Objective:
Build a simple RESTful API in Java using Spring Boot, which supports operations on a `User` entity.
Implement the following RESTful API operations:
- `GET /users`: List all users. 
- `GET /users/{id}`: Get a single user by ID.
- `POST /users`: Create a new user.
- `PUT /users/{id}`: Update an existing user.
- `DELETE /users/{id}`: Delete a user.


To run the project:
1) go to the project folder _restful_users_
2) open terminal and run '**docker-compose -f "docker-compose.yml" up -d --build**'

To stop the project
1) run in the terminal '**docker-compose -f "docker-compose.yml" down**'

To test an app you can use Postman, you can find a Postman collection with all required requests in the folder.

Creating this app, I sticked to such clean code principles:
- keep everything simple (KISS principle)
- I used dependency injection
- methods are small, they do one thing, use descriptive names and few arguments.