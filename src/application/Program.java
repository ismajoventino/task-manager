package application;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
    	
    	TaskManager taskManager = new TaskManager();
    	
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("DESCRIPTION: ");
        String description = scan.nextLine();
        System.out.println("PRIORITY (LOW/MEDIUM/HIGH): ");
        String priorityText = scan.nextLine();
        System.out.println("STATUS (PENDING/IN_PROGRESS/COMPLETED): ");
        String statusText = scan.nextLine();
        
        priorityText = priorityText.replace(" ", "_").toUpperCase();
        
        Priority priority = Priority.valueOf(priorityText.toUpperCase());
        Status status = Status.valueOf(statusText.toUpperCase());
        
        Task task = new Task(description, priority, status);
        
        taskManager.addTask(task);
       
    }
}