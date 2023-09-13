package ch.cern.todo.controller;

import ch.cern.todo.database.LoadDatabase;
import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.exception.TaskCategoryNotFoundException;
import ch.cern.todo.repository.TaskCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskCategoryController {
        private final TaskCategoryRepository tcRepository;
        private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

        TaskCategoryController(TaskCategoryRepository tcRepository) {
                this.tcRepository = tcRepository;
        }

        // Get ALL
        @GetMapping("/taskCategory")
        List<TaskCategory> getAllTaskCategory() {
                log.debug("Get all TaskCategory");
                return tcRepository.findAll();
        }

        // Get category by id
        @GetMapping("/taskCategory/{categoryId}")
        TaskCategory getOneTaskCategory(@PathVariable Long categoryId) {
                TaskCategory tcGet = tcRepository.findById(categoryId)
                        .orElseThrow(() -> new TaskCategoryNotFoundException(categoryId));

                log.debug("Get TaskCategory " + categoryId + ": " + tcGet);
                return tcGet;
        }


        // Post to create
        @PostMapping("/taskCategory")
        TaskCategory newTaskCategory(@RequestBody TaskCategory newTaskCategory) {
                TaskCategory tcPosted = tcRepository.save(newTaskCategory);
                log.debug("Post TaskCategory: " + tcPosted);
                return tcPosted;
        }

        // Edit
        @PutMapping("/taskCategory/{categoryId}")
        TaskCategory replaceTaskCategory(@RequestBody TaskCategory newTaskCategory, @PathVariable Long categoryId) {

                return tcRepository.findById(categoryId)
                        .map(taskCategory -> {
                                taskCategory.setCategoryName(newTaskCategory.getCategoryName());
                                taskCategory.setCategoryDescription(newTaskCategory.getCategoryDescription());
                                TaskCategory tcPutted = tcRepository.save(taskCategory);
                                log.debug("Put TaskCategory: " + tcPutted);
                                return tcPutted;
                        })
                        .orElseGet(() -> { // If taskCategory not found a new taskCategory is created with the provided id
                                TaskCategory tcPutted = tcRepository.save(newTaskCategory);
                                log.debug("Put TaskCategory: " + tcPutted);
                                return tcPutted;
                        });
        }

        // Delete one
        @DeleteMapping("/taskCategory/{categoryId}")
        void deleteTaskCategory(@PathVariable Long categoryId) {
                log.debug("Delete TaskCategory " + categoryId + ": " + tcRepository.findById(categoryId));
                tcRepository.deleteById(categoryId);
        }
}
