package ch.cern.todo.controller;

import ch.cern.todo.database.LoadDatabase;
import ch.cern.todo.entity.Task;
import ch.cern.todo.exception.TaskNotFoundException;
import ch.cern.todo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RestControllerEndpoint(id = "task")
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Get ALL
    @GetMapping("/task")
    List<Task> getAllTask() {
        log.info("Get all Task");
        return taskService.findAll();
    }

    // Get task by id
    @GetMapping("/task/{taskId}")
    Task getOneTask(@PathVariable Long taskId) {
        Task taskGet = taskService.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        log.info("Get Task " + taskId + ": " + taskGet);
        return taskGet;
    }


    // Post to create
    @PostMapping("/task")
    Task newTask(@RequestBody Task newTask) {
        log.info("Post  received: " + newTask);
        return taskService.save(newTask);
    }

    // Edit
    @PutMapping("/task/{taskId}")
    Task replaceTask(@RequestBody Task newTask, @PathVariable Long taskId) {

        return taskService.findById(taskId)
                .map(task -> {
                    task.setTaskName(newTask.getTaskName());
                    task.setTaskDescription(newTask.getTaskDescription());
                    task.setDeadline(newTask.getDeadline());
                    task.setTaskCategory(newTask.getTaskCategory());
                    Task tcPutted = taskService.save(task);
                    log.info("Put Task: " + tcPutted);
                    return tcPutted;
                })
                .orElseGet(() -> { // If task not found a new task is created with the provided id
                    Task taskPutted = taskService.save(newTask);
                    log.info("Put Task: " + taskPutted);
                    return taskPutted;
                });
    }

    // Delete one
    @DeleteMapping("/task/{taskId}")
    void deleteTask(@PathVariable Long taskId) {
        log.info("Delete Task " + taskId + ": " + taskService.findById(taskId));
        taskService.deleteById(taskId);
    }
}
