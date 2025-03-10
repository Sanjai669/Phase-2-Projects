import { TaskManager } from "./services/TaskManager.js";
import readline from "readline";

const taskManager = new TaskManager();
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});


function showMenu() {
  console.log("\nTask Manager Menu:");
  console.log("1. Add Task");
  console.log("2. View All Tasks");
  console.log("3. Mark Task as Complete");
  console.log("4. Exit");
  rl.question("Enter your choice: ", handleUserInput);
}


function handleUserInput(choice: string) {
  switch (choice.trim()) {
    case "1":
      rl.question("Enter Task Title: ", (title) => {
        rl.question("Enter Task Description: ", (description) => {
          try {
            taskManager.addTask(title, description);
            console.log("Task added successfully!");
          } catch (error) {
            console.error((error as Error).message);
          }
          showMenu();
        });
      });
      break;

    case "2":
      console.log("\nAll Tasks:");
      console.table(taskManager.getAllTasks());
      showMenu();
      break;

    case "3":
      rl.question("Enter Task ID to mark as complete: ", (id) => {
        const taskId = parseInt(id);
        if (isNaN(taskId)) {
          console.error("Invalid Task ID.");
        } else {
          try {
            if (taskManager.markTaskAsComplete(taskId)) {
              console.log(`Task ID ${taskId} marked as complete.`);
            } else {
              console.warn(`Task ID ${taskId} is already completed.`);
            }
          } catch (error) {
            console.error((error as Error).message);
          }
        }
        showMenu();
      });
      break;

    case "4":
      console.log("Exiting Task Manager...");
      rl.close();
      break;

    default:
      console.log("Invalid choice. Please enter a valid option.");
      showMenu();
  }
}
showMenu();
