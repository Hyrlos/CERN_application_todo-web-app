package ch.cern.todo.controller;

import ch.cern.todo.database.LoadDatabase;
import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.exception.TaskCategoryNotFoundException;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.service.TaskCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskCategoryController {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final TaskCategoryService tcService;

    TaskCategoryController(TaskCategoryService tcService) {
        this.tcService = tcService;
    }

    // Get ALL
    @GetMapping("/taskCategory")
    List<TaskCategory> getAllTaskCategory() {
        log.info("Get all TaskCategory");
        return tcService.findAll();
    }

    // Get category by id
    @GetMapping("/taskCategory/{categoryId}")
    TaskCategory getOneTaskCategory(@PathVariable Long categoryId) {
        TaskCategory tcGet = tcService.findById(categoryId)
                .orElseThrow(() -> new TaskCategoryNotFoundException(categoryId));

        log.info("Get TaskCategory " + categoryId + ": " + tcGet);
        return tcGet;
    }


    // Post to create
    @PostMapping("/taskCategory")
    TaskCategory newTaskCategory(@RequestBody TaskCategory newTaskCategory) {
        TaskCategory tcPosted = tcService.save(newTaskCategory);
        log.info("Post TaskCategory: " + tcPosted);
        return tcPosted;
    }

    // Edit
    @PutMapping("/taskCategory/{categoryId}")
    TaskCategory replaceTaskCategory(@RequestBody TaskCategory newTaskCategory, @PathVariable Long categoryId) {

        return tcService.findById(categoryId)
                .map(taskCategory -> {
                    taskCategory.setCategoryName(newTaskCategory.getCategoryName());
                    taskCategory.setCategoryDescription(newTaskCategory.getCategoryDescription());
                    TaskCategory tcPutted = tcService.save(taskCategory);
                    log.info("Put TaskCategory: " + tcPutted);
                    return tcPutted;
                })
                .orElseGet(() -> { // If taskCategory not found a new taskCategory is created with the provided id
                    TaskCategory tcPutted = tcService.save(newTaskCategory);
                    log.info("Put TaskCategory: " + tcPutted);
                    return tcPutted;
                });
    }

    // Delete one
    @DeleteMapping("/taskCategory/{categoryId}")
    void deleteTaskCategory(@PathVariable Long categoryId) {
        log.info("Delete TaskCategory " + categoryId + ": " + tcService.findById(categoryId));
        tcService.deleteById(categoryId);
    }
}
