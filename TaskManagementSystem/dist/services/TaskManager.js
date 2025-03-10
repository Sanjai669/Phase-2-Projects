import { Task } from "../models/Task.js";
export class TaskManager {
    constructor() {
        this.tasks = [];
        this.nextId = 1;
    }
    addTask(title, description) {
        if (!title || !description) {
            throw new Error("Task title and description cannot be empty.");
        }
        const task = new Task(this.nextId++, title, description);
        this.tasks.push(task);
        return task;
    }
    getAllTasks() {
        return this.tasks;
    }
    markTaskAsComplete(id) {
        const task = this.tasks.find(task => task.id === id);
        if (!task) {
            throw new Error(`Task with ID ${id} not found.`);
        }
        if (task.isCompleted) {
            return false;
        }
        task.markComplete();
        return true;
    }
}
