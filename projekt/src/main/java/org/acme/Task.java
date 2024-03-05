package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Entity
public class Task extends PanacheEntity  {

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public String completed;

    public Task() {}

    public static Task findTaskById(Long id) {
        return findById(id);
    }

    public static Task findByTitle(String title){
        return find("title", title).firstResult();  }

    @Transactional
    public static void deleteTask(Long id) {
        findById(id).delete();
    }

    @Transactional
    public static void deleteAllTasks() {
        deleteAll();
    }

    @Transactional
    public static void updateTask(Long id, Task newTask) {
        Task taskToBeUpdated = findTaskById(id);
        taskToBeUpdated.title = newTask.title;
        taskToBeUpdated.description = newTask.description;
        taskToBeUpdated.completed = newTask.completed;
    }

    @Transactional
    public static void createTask(Task task) {
        task.persist();
    }

}