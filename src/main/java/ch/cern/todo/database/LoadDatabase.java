package ch.cern.todo.database;


import ch.cern.todo.entity.Task;
import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.repository.TaskRepository;
import ch.cern.todo.service.TaskCategoryService;
import ch.cern.todo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TaskCategoryService taskCategoryService, TaskService taskService) {

        return args -> {
            if(taskCategoryService.findAll().isEmpty()) {
                TaskCategory tc = taskCategoryService.save(new TaskCategory("TaskCategoriesCreatedPerDefault", "Task Categories created per default"));
                log.info("Preloading " + tc);
            }

            if(taskService.findAll().isEmpty()) {
                Date randomFutureDate = new Date((long) ((new Date().getTime()) + Math.random() * 100000000));
                TaskCategory tc = taskCategoryService.findAll().get(0);
                Task task = taskService.save(
                        new Task("TaskCreatedPerDefault", "Task created per default",
                                randomFutureDate, tc));
                log.info("Preloading " + task);
            }
        };
    }

}
