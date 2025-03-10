import { Task } from "../models/Task.js";

export class TaskManager {
  private tasks: Task[] = [];
  private nextId: number = 1;


  addTask(title: string, description: string): Task {
    if (!title || !description) {
      throw new Error("Task title and description cannot be empty.");
    }
    const task = new Task(this.nextId++, title, description);
    this.tasks.push(task);
    return task;
  }


  getAllTasks(): Task[] {
    return this.tasks;
  }


  markTaskAsComplete(id: number): boolean {
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
