package ch.cern.todo.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TASKS")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(nullable = false, length = 100)
    private String taskName;

    @Column(length = 500)
    private String taskDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date deadline;

 //   @OneToOne(mappedBy = "categoryId")
    @ManyToOne
    @JoinColumn(name = "category_id_category_id")
    private TaskCategories categoryId;

    public Tasks(String taskName, String taskDescription, Date deadline, TaskCategories categoryId) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.categoryId = categoryId;
    }

    public Tasks() {

    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskCategories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(TaskCategories categoryId) {
        this.categoryId = categoryId;
    }
}
