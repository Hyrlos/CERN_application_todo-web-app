import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Task} from './ITask';
import {Observable} from "rxjs";

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

    taskList: Task[] = [];

    constructor(
        private http: HttpClient) {
    }

    getTask(): void {
        this.http.get<Task[]>(this.urlTask).subscribe(data =>
            this.taskList = data
        );
    }

    deleteHero(id: number): Observable<Task> {
        const url = `${this.urlTask}/${id}`;
        return this.http.delete<Task>(url, this.httpOptions);
    }

    ngOnInit() {
        this.getTask();
    }

    delete(task: Task) {
        this.taskList = this.taskList.filter(t => t !== task);
        this.deleteHero(task.taskId).subscribe();
    }
}
