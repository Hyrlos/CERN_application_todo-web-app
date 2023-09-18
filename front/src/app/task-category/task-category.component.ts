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
    urlTask: string = "http://localhost:8080/taskCategory";
    taskCategoryList: TaskCategory[] = [];

    constructor(
        private http: HttpClient) {
    }

    getTaskCategory(): void {
        this.http.get<TaskCategory[]>(this.urlTask).subscribe(data =>
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

    private addTaskCategory(taskCategory: TaskCategory): Observable<TaskCategory> {
        console.log(taskCategory)
        return this.http.post<TaskCategory>(this.urlTask, taskCategory, this.httpOptions);
    }
}
