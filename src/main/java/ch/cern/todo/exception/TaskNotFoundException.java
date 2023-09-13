package ch.cern.todo.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Category not found " + id);
    }
}
