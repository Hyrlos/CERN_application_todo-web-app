package ch.cern.todo.service;

import ch.cern.todo.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    List<Task> findAll();

    List<Task> findByCategoryId(Long categoryId);

    Optional<Task> findById(Long taskId);

    Task save(Task newTask);

    void deleteById(Long taskId);
}
