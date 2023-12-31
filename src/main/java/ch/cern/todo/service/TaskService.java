package ch.cern.todo.service;

import ch.cern.todo.entity.Task;
import ch.cern.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final MetricsService metricsService;

    @Autowired
    public TaskService(TaskRepository taskRepository, MetricsService metricsService) {
        this.taskRepository = taskRepository;
        this.metricsService = metricsService;
    }

    @Override
    public List<Task> findByCategoryId(Long categoryId) {
        return this.findAll().stream().filter(task -> (task.getTaskCategory().getCategoryId().equals(categoryId)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task save(Task newTask) {
        metricsService.incrementTaskAddedCounter();
        return taskRepository.save(newTask);
    }

    @Override
    public void deleteById(Long taskId) {
        metricsService.incrementTaskDeletedCounter();
        taskRepository.deleteById(taskId);
    }
}
