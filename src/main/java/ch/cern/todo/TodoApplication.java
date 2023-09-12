package ch.cern.todo;

import ch.cern.todo.entities.TaskCategories;
import ch.cern.todo.repositories.TaskCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}


	@Bean
	public CommandLineRunner run(TaskCategoryRepository taskCategoryRepository) throws Exception {
		System.out.println("CommandLineRunner");
		return (String[] args) -> {
			TaskCategories tc1 = new TaskCategories("testCategorie", "categorie test");
			taskCategoryRepository.save(tc1);
			taskCategoryRepository.findAll().forEach(System.out::println);
		};
    }
}
