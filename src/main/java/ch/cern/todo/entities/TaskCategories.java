package ch.cern.todo.entities;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@EnableAutoConfiguration
@Table(name = "TASK_CATEGORIES")
public class TaskCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_Id", nullable = false)
    private Long categoryId;

    // Mandatory
    @Column(nullable = false, length = 200)
    private String categoryName;

    @Column(length = 500)
    private String categoryDescription;

    public TaskCategories(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public TaskCategories() {
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

    @Override
    public String toString() {
        return "TaskCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}
