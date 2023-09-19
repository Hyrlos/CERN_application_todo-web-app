import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Task} from './ITask';
import {Observable} from "rxjs";
import {TaskCategory} from "../task-category/ITaskCategory";

@Injectable({
  providedIn: 'root',
})
@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  urlTask: string = "http://localhost:8080/task";
  urlTaskCategory: string = "http://localhost:8080/taskCategory";

  taskList: Task[] = [];
  taskCategoryList: TaskCategory[] = [];

  selectedTaskCategoryId: number = -1; // Initialize as -1

  constructor(
    private http: HttpClient) {
  }

  getTask(): void {
    this.http.get<Task[]>(this.urlTask).subscribe(data =>
      this.taskList = data
    );
  }

  deleteTask(id: number): Observable<Task> {
    const url = `${this.urlTask}/${id}`;
    return this.http.delete<Task>(url, this.httpOptions);
  }

  ngOnInit() {
    this.getTask();
    this.getTaskCategory();
  }

  getTaskCategory(): void {
    this.http.get<TaskCategory[]>(this.urlTaskCategory).subscribe(data =>
      this.taskCategoryList = data
    );
  }

  delete(task: Task) {
    this.taskList = this.taskList.filter(t => t !== task);
    this.deleteTask(task.taskId).subscribe();
  }

  add(taskName: string, taskDescription: string, taskDeadline: string) {
    taskName = taskName.trim();
    taskDescription = taskDescription.trim();
    const deadline = Date.parse(taskDeadline.trim());
    const taskCategory = this.taskCategoryList.find(category => category.categoryId == this.selectedTaskCategoryId);
    let taskId = 0;

    let newTask = {taskId, taskName, taskDescription, deadline, taskCategory} as Task;
    this.addTask(newTask)
      .subscribe(task => {
        this.taskList.push(task);
      });

  }

  private addTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.urlTask, task, this.httpOptions);
  }
}
