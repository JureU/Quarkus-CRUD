# Quarkus-CRUD

Backend application is created with Java na Quarkus framework: https://quarkus.io/
It also uses PostgreSQL. It allowes users to perform basic CRUD operations on tasks. There is implemented entity called Task with fields: "id", "title", "description" and "completed". Some basic validation is also implemented. There are also some tests for CRUD operations.

How to run and test the application:
  1. Create a new database for our Tasks.
      Server [localhost]
      Database [postgres]
      Port [5432]
      Username [postgres]
      Password [geslo]
  2. Run the program by typing: mvn quarkus:dev - if using maven or if you are using quarkus cli type: quarkus dev. You can see it on localhost:8080
  3. Test with: mvn test
