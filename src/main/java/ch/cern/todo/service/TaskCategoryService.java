package ch.cern.todo.service;

import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCategoryService implements ITaskCategoryService {
    private final TaskService taskService;
    private final TaskCategoryRepository taskCategoryRepository;

    @Autowired
    public TaskCategoryService(TaskService taskService, TaskCategoryRepository taskCategoryRepository) {
        this.taskService = taskService;
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Override
    public void deleteById(Long id) {
        try {
            taskService.findByCategoryId(id).forEach(task -> taskService.deleteById(task.getTaskId()));
            taskCategoryRepository.deleteById(id);
        } catch (Exception ex) {
            System.out.println("error");
        }
    }

    @Override
    public List<TaskCategory> findAll() {
        return taskCategoryRepository.findAll();
    }

    @Override
    public Optional<TaskCategory> findById(Long categoryId) {
        return taskCategoryRepository.findById(categoryId);
    }

    @Override
    public TaskCategory save(TaskCategory newTaskCategory) {
        return taskCategoryRepository.save(newTaskCategory);
    }
}