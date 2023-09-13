package ch.cern.todo.database;


import ch.cern.todo.entity.Task;
import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.repository.TaskRepository;
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
    CommandLineRunner initDatabase(TaskCategoryRepository tcRepository, TaskRepository taskRepository) {

        return args -> {
            TaskCategory tc = tcRepository.save(new TaskCategory("TaskCategoriesPreloaded", "Task Categories Preloaded"));
            log.info("Preloading " + tc);

            Date randomFutureDate = new Date((long) ((new Date().getTime()) + Math.random() * 100000000));
            Task task = taskRepository.save(
                    new Task("TaskPreloaded", "Task Preloaded Description",
                            randomFutureDate, tc));

            log.info("Preloading " + task);
        };
    }
}
