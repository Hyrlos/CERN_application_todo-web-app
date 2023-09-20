package ch.cern.todo.service;

import ch.cern.todo.entity.TaskCategory;

import java.util.List;
import java.util.Optional;

public interface ITaskCategoryService {
    List<TaskCategory> findAll();

    Optional<TaskCategory> findById(Long categoryId);

    TaskCategory save(TaskCategory newTaskCategory);

    void deleteById(Long categoryId);
}
