package com.ismael.taskmanager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		TaskManager taskManager = new TaskManager("tasks.txt");
		
		try {
		    taskManager.loadTaskFile("tasks.txt");
		    System.out.println("Previous tasks loaded!");
		} catch (NoSuchElementException e) {
		    System.out.println("No previous tasks found. Starting fresh.");
		}
		
		byte option = 0;
		
		do {
			System.out.println("1. Add task.");
			System.out.println("2. Mark task as completed.");
			System.out.println("3. List all tasks.");
			System.out.println("4. Save file.");
			System.out.println("5. Load file.");
			System.out.println("6. Delete task.");
			System.out.println("7. Filter by status.");
			System.out.println("8. Filter by priority.");
			System.out.println("0. Exit");
			option = scan.nextByte();
			scan.nextLine();  
			
			switch(option){
				case 0 -> {
					taskManager.saveTaskFile(taskManager.filename);
					System.out.println("Closing application.");
					System.exit(0);
				}
			
				case 1 -> {
					try {
				        System.out.println("DESCRIPTION: ");
				        String description = scan.nextLine();
				        System.out.println("PRIORITY (LOW/MEDIUM/HIGH): ");
				        String priorityText = scan.nextLine();
				        System.out.println("STATUS (PENDING/IN_PROGRESS/COMPLETED): ");
				        String statusText = scan.nextLine();

				        priorityText = priorityText.replace(" ", "_").toUpperCase();
				        
				        Priority priority = Priority.valueOf(priorityText.toUpperCase());
				        Status status = Status.valueOf(statusText.toUpperCase());

				        taskManager.addTask(description, priority, status);
				        System.out.println("Task added successfully!");
				        
				    } catch (IllegalArgumentException e) {
				        System.out.println("ERROR: Invalid priority or status!");
				    }
				}
				
				case 2 -> {
				    try{
				        System.out.println("Enter task description OR index (-1 to skip): ");
				        String description = scan.nextLine();
				        
				        taskManager.markCompleted(description, -1);
				        System.out.println("Task marked as completed!");
				    }
				    catch(NoSuchElementException e) {
				        System.out.println("ERROR: " + e.getMessage());
				    }
				}
				
				case 3 -> {
					try {
						taskManager.listAllTasks();
					}
					catch (NoSuchElementException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
				
				case 4 -> {
					taskManager.saveTaskFile(taskManager.filename);
					System.out.println("Tasks saved successfully!");
				}
				
				case 5 -> {
					try {
						 taskManager.saveTaskFile(taskManager.filename);
						 System.out.println("Tasks loaded successfully!");
					}
					catch (NoSuchElementException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
				
				case 6 -> {
					try {
						System.out.println("DESCRIPTION: ");
						String description = scan.nextLine();
						taskManager.deleteTask(description);
					}
					catch(NoSuchElementException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
				
				case 7 -> {
					try {
						System.out.println("STATUS: ");
						String status =  scan.nextLine();
						taskManager.filterByStatus(status);
					}
					catch (NoSuchElementException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
				
				case 8 -> {
					try {
						System.out.println("PRIORITY: ");
						String priority =  scan.nextLine();
						taskManager.filterByPriority(priority);
					}
					catch (NoSuchElementException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}	
				
				default -> {
					System.out.println("Invalid Option!");
				}
			}
			
		} while(option != 0);
		scan.close();
	}

}
