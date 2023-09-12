package ch.cern.todo.repositories;

import ch.cern.todo.entities.TaskCategories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends CrudRepository<TaskCategories, Long> {
}
