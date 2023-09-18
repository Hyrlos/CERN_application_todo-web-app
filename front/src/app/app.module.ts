import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {TaskCategoryComponent} from './task-category/task-category.component';
import {AppRoutingModule} from "./app-routing.module";
import {TaskComponent} from './task/task.component';
import {HttpClientModule} from '@angular/common/http';
import {DateFormatPipe} from "./utils/date-format.pipe";

@NgModule({
    declarations: [
        TaskCategoryComponent,
        TaskComponent,
        DateFormatPipe,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [TaskCategoryComponent, TaskComponent]

})
export class AppModule {
}
