package ch.cern.todo.database;


import ch.cern.todo.entities.TaskCategories;
import ch.cern.todo.repositories.TaskCategoriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TaskCategoriesRepository tcRepository) {

        return args -> {
            log.info("Preloading " + tcRepository.save( new TaskCategories("testCategories", "categorie test")));
        };
    }
}
