package org.acme;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/task")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TaskResource {

    @GET
    @Path("all")
    public List<Task> getAllTasks() {
        return Task.listAll();
    }

    @GET
    @Path("{id}")
    public Task findTaskById(@PathParam("id") Long id) {
        return Task.findTaskById(id);
    }

    @GET
    @Path("title/{title}")
    public Task findByTitle(@PathParam("title") String title) {
        return Task.findByTitle(title);
    }

    @POST
    @Path("create")
    public Response createTask(Task task) {
        Task.createTask(task);
        Log.info("Task created:"+task.id);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("update/{id}")
    public Response updateTask(@PathParam("id") Long id, Task task) {
        Task.updateTask(id,task);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        Task.deleteTask(id);
        return Response.status(Response.Status.OK).build();
    }

}  
