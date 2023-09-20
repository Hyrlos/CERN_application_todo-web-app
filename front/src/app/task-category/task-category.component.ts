import {Component, Injectable, OnInit} from '@angular/core';
import {TaskCategory} from './ITaskCategory';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
@Component({
  selector: 'app-task-category',
  templateUrl: './task-category.component.html',
  styleUrls: ['./task-category.component.css']
})
export class TaskCategoryComponent implements OnInit {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  urlTaskCategory: string = "http://localhost:8080/taskCategory";
  taskCategoryList: TaskCategory[] = [];

  constructor(
    private http: HttpClient) {
  }


  deleteTaskCategory(id: number): Observable<TaskCategory> {
    const url = `${this.urlTaskCategory}/${id}`;
    return this.http.delete<TaskCategory>(url, this.httpOptions);
  }

  getTaskCategory(): void {
    this.http.get<TaskCategory[]>(this.urlTaskCategory).subscribe(data =>
      this.taskCategoryList = data
    );
  }

  ngOnInit() {
    this.getTaskCategory();
  }

  add(categoryName: string, categoryDescription: string): void {
    categoryName = categoryName.trim();
    categoryDescription = categoryDescription.trim();
    let categoryId = 0;
    if (!categoryName) {
      return;
    }
    if (!categoryDescription) {
      return;
    }

    this.addTaskCategory({categoryId, categoryName, categoryDescription} as TaskCategory)
      .subscribe(taskCategory => {
        this.taskCategoryList.push(taskCategory);
      });
  }

  delete(taskCategory: TaskCategory) {
    this.taskCategoryList = this.taskCategoryList.filter(tc => tc !== taskCategory);
    this.deleteTaskCategory(taskCategory.categoryId).subscribe();
  }

  private addTaskCategory(taskCategory: TaskCategory): Observable<TaskCategory> {
    console.log(taskCategory)
    return this.http.post<TaskCategory>(this.urlTaskCategory, taskCategory, this.httpOptions);
  }
}
