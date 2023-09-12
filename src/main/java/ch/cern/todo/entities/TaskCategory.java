package ch.cern.todo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TASK_CATEGORIES")
public class TaskCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_Id", nullable = false)
    private Long categoryId;

    // Mandatory
    @Column(nullable = false, length = 200)
    private String categoryName;

    @Column(length = 500)
    private String categoryDescription;

    public TaskCategory(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public TaskCategory() {
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
