## BibliotecaSpring
BibliotecaSpring is a RESTful API developed with Spring Boot for managing library books. This API allows users to view available books and borrow them when they are available.

## Requirements
Java 17
Maven
JPA / Hibernate

## Clone the Repository
git clone https://github.com/DandaraEmiliano/Biblioteca-Spring.git
cd Biblioteca-Spring

## Build the Project using Maven
To compile and install dependencies, use:
mvn clean install

## Run the Application
To run the application, use:
mvn spring-boot:run

The application will be available at http://localhost:8080.

## H2 Database
This project uses an in-memory H2 database for development and testing purposes. You can access the H2 console to inspect data.

- Go to: http://localhost:8080/h2-console
- Use the following credentials:
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: password

## H2 Console Configuration
After logging into the H2 console, you can run SQL queries to check the data. For example:

SELECT * FROM LIVRO WHERE DISPONIVEL = TRUE;

## Endpoints
List Available Books:

GET /livros
Retrieves a list of books that are currently available for borrowing.

## Borrow a Book
POST /emprestar
Allows borrowing a book by title.

## Parameters:
titulo (String): The title of the book to be borrowed.
Example: /emprestar?titulo=O%20Senhor%20dos%20Anéis
