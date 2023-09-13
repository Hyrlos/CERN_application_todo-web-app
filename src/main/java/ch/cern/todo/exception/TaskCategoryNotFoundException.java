package ch.cern.todo.exception;

public class TaskCategoryNotFoundException extends RuntimeException {
    public TaskCategoryNotFoundException(Long id){
        super("Category not found " + id);
    }
}
