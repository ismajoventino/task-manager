package com.ismael.taskmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TaskManager {
	
	ArrayList<Task> tasks;
	String filename;
	
	public TaskManager(String filename) {
		this.tasks = new ArrayList<>();
		this.filename = filename;
	}

	public void addTask(String description, Priority priority, Status status) {
		Task newTask = new Task(description, priority, status);
		this.tasks.add(newTask);
		
	}
	
	public void markCompleted(String descriptionText, int index) throws NoSuchElementException{
		for(Task t : tasks) {
			if(t.getDescription().equalsIgnoreCase(descriptionText) || tasks.indexOf(t) == index) {
				t.setStatus(Status.COMPLETED);
				return;
			}
		}
		
		throw new NoSuchElementException("Task not found.");
	}
	
	public void listAllTasks() throws NoSuchElementException {
		if(tasks.isEmpty()) {
			throw new NoSuchElementException("No tasks found in the system.");
		}
		for(Task t : tasks) {
			System.out.println(t.toString());
		}
	}
	
	public void saveTaskFile() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename))){
			for (Task t : tasks) {
				writer.write(t.toString() + "\n");
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadTaskFile(String filename) throws NoSuchElementException{
		File file = new File(filename);
		if(!file.exists()) {
			throw new NoSuchElementException (filename + " not found.");
		}
		
		tasks.clear();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split(";");
				Priority priority = Priority.valueOf(parts[1]);
				Status status = Status.valueOf(parts[2]);
				
				Task task = new Task(parts[0], priority, status);
				
				this.tasks.add(task);
			}
		}
		catch(IOException | IllegalArgumentException e) {
			System.out.println("ERROR:: " + e.getMessage());
		}
		
	}
	
	public void deleteTask(String descriptionText) throws NoSuchElementException{
	    boolean removed = tasks.removeIf(t -> t.getDescription().equalsIgnoreCase(descriptionText));

	    if (!removed) {
	        throw new NoSuchElementException("Task not found: " + descriptionText);
	    }
	}
	
	public void filterByStatus(String statusText) throws NoSuchElementException{
		int flag = 0;
		Status status = Status.valueOf(statusText.toUpperCase());
		
		for(Task t : tasks) {
			if(t.getStatus() == status) {
				System.out.println(t.toString());
				flag++;
			}
		}
		
		if(flag == 0) {
			throw new NoSuchElementException ("No tasks found with status: " + status);
		}
		
		return;
	}
	
	public void filterByPriority(String priorityText) throws NoSuchElementException{
		int flag = 0;
		Priority priority = Priority.valueOf(priorityText.toUpperCase());
		
		for(Task t : tasks) {
			if(t.getPriority() == priority) {
				System.out.println(t.toString());
				flag++;
			}
		}
		
		if(flag == 0) {
			throw new NoSuchElementException ("No tasks found with priority: " + priority);
		}
		
		return;
	}
	
}
