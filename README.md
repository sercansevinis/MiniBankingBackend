Spring Boot Application
Overview
This is a Spring Boot application designed for managing transactions and accounts in a banking context. It includes functionality for user management, account operations, and transaction operations, with features like JWT-based authentication (Commented out, not implemented fully), pagination, and more.

Prerequisites
Before you can run the application, ensure you have the following installed:

Java 1.8 or higher
Maven 3.6.3 or higher
Git (optional, for cloning the repository)
Getting Started
1. Clone the Repository
If you haven't done so already, clone the repository from GitHub:

git clone https://github.com/username/repo-name.git
cd repo-name
2. Configure the Application
Application Properties:

Navigate to src/main/resources/application.properties.
You can find the url for h2 database and check db from that endpoint
Example:

3. Build the Application
Use Maven to build the project:

mvn clean install
This command will compile the code, run tests, and package the application into a .jar file.

4. Run the Application
You can start the application using the following Maven command:

mvn spring-boot:run
Alternatively, if you want to run the compiled .jar file directly:

java -jar target/application-name.jar
5. Access the Application
Once the application is running, you can access it via:

Localhost URL: http://localhost:8095

mvn test
7. Common Endpoints
User Management:

POST /users - Create a new user
GET /users/{id} - Get user details
Account Operations:

POST /accounts - Create a new account
GET /accounts/{id} - Get account details
Transaction Operations:

POST /transactions - Create a new transaction
GET /transactions/{id} - Get transaction details
GET /transactions/history - Get transaction history with pagination (page and size parameters)
