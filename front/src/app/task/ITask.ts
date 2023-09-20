import {TaskCategory} from "../task-category/ITaskCategory";

export interface Task {
    taskId: number;
    taskName: string;
    taskDescription: string;
    deadline: number;
    categoryId: TaskCategory;
}
