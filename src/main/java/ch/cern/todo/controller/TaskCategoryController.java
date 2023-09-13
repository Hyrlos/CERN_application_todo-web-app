package ch.cern.todo.controller;

import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.exception.TaskCategoryNotFoundException;
import ch.cern.todo.repository.TaskCategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskCategoryController {
        private final TaskCategoryRepository tcRepository;

        TaskCategoryController(TaskCategoryRepository tcRepository) {
                this.tcRepository = tcRepository;
        }

        // Get ALL
        @GetMapping("/taskCategory")
        List<TaskCategory> all() {
                return tcRepository.findAll();
        }

        // Get category by id
        @GetMapping("/taskCategory/{categoryId}")
        TaskCategory one(@PathVariable Long categoryId) {

                return tcRepository.findById(categoryId)
                        .orElseThrow(() -> new TaskCategoryNotFoundException(categoryId));
        }


                // Post to create
        @PostMapping("/taskCategory")
        TaskCategory newTaskCategory(@RequestBody TaskCategory newTaskCategory) {
                return tcRepository.save(newTaskCategory);
        }

        // Edit
        @PutMapping("/taskCategory/{categoryId}")
        TaskCategory replaceEmployee(@RequestBody TaskCategory newTaskCategory, @PathVariable Long categoryId) {

                return tcRepository.findById(categoryId)
                        .map(taskCategory -> {
                                taskCategory.setCategoryName(newTaskCategory.getCategoryName());
                                taskCategory.setCategoryDescription(newTaskCategory.getCategoryDescription());
                                return tcRepository.save(taskCategory);
                        })
                        .orElseGet(() -> { // If taskCategory not found a new taskCategory is created with the provided id
                                return tcRepository.save(newTaskCategory);
                        });
        }

        // Delete one
        @DeleteMapping("/taskCategory/{categoryId}")
        void deleteTaskCategory(@PathVariable Long categoryId) {
                tcRepository.deleteById(categoryId);
        }
}
