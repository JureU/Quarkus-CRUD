package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(TaskResource.class)
public class TaskResourceTest {

    @Test
    @Order(1)
    public void createTask_createTwoTasks() {

        JsonObject task1 = Json.createObjectBuilder()
                .add("title", "Domaca naloga")
                .add("description", "Dokoncaj jo")
                .add("completed", "Dokoncano").build();

                JsonObject task2 = Json.createObjectBuilder()
                .add("title", "Projekt za sluzbo")
                .add("description", "Zadnja faza")
                .add("completed", "Ni Še").build();

        // Test POST
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(task1.toString())
                .when()
                .post("create")
                .then()
                .statusCode(201);

        // Test POST 2
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(task2.toString())
                .when()
                .post("create")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    public void FetchTaskByName() {
        //TEST GET
        Task task = given()
                .accept(MediaType.APPLICATION_JSON)
                .when().get("title/{name}","Domaca naloga")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Task.class);
    }

    @Test
    @Order(3)
    public void updateTask() {
        // TEST UPDATE
        Task task = given()
                .accept(MediaType.APPLICATION_JSON)
                .when().get("title/{name}","Domaca naloga")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Task.class);

        JsonObject taskToBeUpdated = Json.createObjectBuilder()
                .add("title", "Domaca naloga 2")
                .add("description", "Druga naloga")
                .add("completed", "Ni se").build();

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskToBeUpdated.toString())
                .when().put("update/{id}",task.id)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void deleteTask() {
 
        Task task = given()
                .accept(MediaType.APPLICATION_JSON)
                .when().get("title/{name}","Projekt za sluzbo")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Task.class);

        // Test DELETE
        given()
                .when().delete("delete/{id}", task.id)
                .then()
                .statusCode(200);
        }

}