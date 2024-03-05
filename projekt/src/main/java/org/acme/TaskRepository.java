package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import java.util.List;


public class TaskRepository implements PanacheRepository<Task> {

    public List<Task> findAllTasks() {
        return listAll(Sort.by("id"));
    }

    public Task findTaskById(Long id) {
        Task task = findById(id);
        return task;
    }

    @Transactional
    public void createTask(Task task) {
       persist(task);
    }

    @Transactional
    public void updateTask(Task task) {
        Task taskToBeUpdated = findTaskById(task.id);
        taskToBeUpdated.title = task.title;
        taskToBeUpdated.description = task.description;
        taskToBeUpdated.completed = task.completed;
    }

    @Transactional
    public void deleteTask(Long taskId) {
        Task taskToBeDeleted = findTaskById(taskId);
        delete(taskToBeDeleted);
    }
}