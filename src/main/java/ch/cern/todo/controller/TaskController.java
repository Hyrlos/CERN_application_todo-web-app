package ch.cern.todo.controller;

import ch.cern.todo.database.LoadDatabase;
import ch.cern.todo.entity.Task;
import ch.cern.todo.exception.TaskNotFoundException;
import ch.cern.todo.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final TaskRepository taskRepository;

    TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get ALL
    @GetMapping("/task")
    List<Task> getAllTask() {
        log.debug("Get all Task");
        return taskRepository.findAll();
    }

    // Get task by id
    @GetMapping("/task/{taskId}")
    Task getOneTask(@PathVariable Long taskId) {
        Task taskGet = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        log.debug("Get Task " + taskId + ": " + taskGet);
        return taskGet;
    }


    // Post to create
    @PostMapping("/task")
    Task newTask(@RequestBody Task newTask) {
        Task taskPosted = taskRepository.save(newTask);
        log.debug("Post Task: " + taskPosted);
        return taskPosted;
    }

    // Edit
    @PutMapping("/task/{taskId}")
    Task replaceTask(@RequestBody Task newTask, @PathVariable Long taskId) {

        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setTaskName(newTask.getTaskName());
                    task.setTaskDescription(newTask.getTaskDescription());
                    task.setDeadline(newTask.getDeadline());
                    task.setCategoryId(newTask.getCategoryId());
                    Task tcPutted = taskRepository.save(task);
                    log.debug("Put Task: " + tcPutted);
                    return tcPutted;
                })
                .orElseGet(() -> { // If task not found a new task is created with the provided id
                    Task taskPutted = taskRepository.save(newTask);
                    log.debug("Put Task: " + taskPutted);
                    return taskPutted;
                });
    }

    // Delete one
    @DeleteMapping("/task/{taskId}")
    void deleteTask(@PathVariable Long taskId) {
        log.debug("Delete Task " + taskId + ": " + taskRepository.findById(taskId));
        taskRepository.deleteById(taskId);
    }
}
