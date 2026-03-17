package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import db.DB;

public class TaskManager {
	
	public TaskManager() {
	}

	public void addTask(Task task) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
					"INSERT INTO tasks "
					+ "(task_description, priority, task_status) "
					+ "VALUES (?, ?, ?)");
			
			ps.setString(1, task.getDescription());
			ps.setString(2, task.getPriority().name());
			ps.setString(3, task.getStatus().name());
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
        		System.out.println("Done! Rows affected: " + rowsAffected);
        	} else {
        		System.out.println("No rows affected!");
        	}
		}
		catch(SQLException e) {
			throw new RuntimeException("Error: Failed to insert the new task into the database. " + e.getMessage());
		}
		finally{
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}
	
	public boolean markCompleted(String descriptionText){
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
					"UPDATE tasks "
					+ "SET task_status = ? "
					+ "WHERE task_description = ?"
					);
			
			ps.setString(1, Status.COMPLETED.name() );
			ps.setString(2, descriptionText);
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
        		return true;
        	}
		}
		catch(SQLException e) {
			throw new RuntimeException("Error: Failed to update/delete the task. " + e.getMessage());
		}
		finally{
			DB.closeStatement(ps);
			DB.closeConnection();
		}
		
		return false;
	}
	
	public ArrayList<Task> listAllTasks() throws NoSuchElementException {
		
		ArrayList<Task> tasks = new ArrayList<>();
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tasks");
			
			while (rs.next()) {
				String description = rs.getString("task_description");
				Priority priority = Priority.valueOf(rs.getString("priority").toUpperCase());
				Status status = Status.valueOf(rs.getString("task_status").toUpperCase());
				
				Task task = new Task(description, priority, status);
				
				tasks.add(task);
			}
			
		}
		catch(SQLException e) {
			throw new RuntimeException("Error: Failed to fetch tasks from the database. " + e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		
		return tasks;
		
	}
	
	public boolean deleteTask(String descriptionText) {
	   Connection conn = null;
	   PreparedStatement ps = null;
	   
	   try {
		   conn = DB.getConnection();
		   ps = conn.prepareStatement(
				   "DELETE FROM taks "
				   + "WHERE task_description = ?"
				   );
		   
		   ps.setString(1, descriptionText);
		   
		   int rowsAffected = ps.executeUpdate();
		   
		   if(rowsAffected > 0) {
			   return true;
		   }
		
	   }
	   catch(SQLException e) {
		   throw new RuntimeException("Error: Failed to update/delete the task. " + e.getMessage());
	   }
	   finally {
		   DB.closeStatement(ps);
		   DB.closeConnection();
	   }
	   
	   return false;
	}
	
	public ArrayList<Task> filterByStatus(String statusText) throws NoSuchElementException{
		ArrayList<Task> filteredTasks = new ArrayList<Task>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("SELECT * FROM tasks "
					+ "WHERE task_status = ?");
			
			
			ps.setString(1, statusText);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String description = rs.getString("task_description");
				Priority priority = Priority.valueOf(rs.getString("priority").toUpperCase());
				Status status = Status.valueOf(rs.getString("task_status").toUpperCase());
				
				Task task = new Task(description, priority, status);
				
				filteredTasks.add(task);
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Error: Failed to fetch tasks from the database. " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
			DB.closeConnection();
		}
		
		return filteredTasks;
	}
	
	public ArrayList<Task> filterByPriority(String priorityText) throws NoSuchElementException{
		ArrayList<Task> filteredTasks = new ArrayList<Task>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("SELECT * FROM tasks "
					+ "WHERE priority = ?");
			
			
			ps.setString(1, priorityText);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String description = rs.getString("task_description");
				Priority priority = Priority.valueOf(rs.getString("priority").toUpperCase());
				Status status = Status.valueOf(rs.getString("task_status").toUpperCase());
				
				Task task = new Task(description, priority, status);
				
				filteredTasks.add(task);
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Error: Failed to fetch tasks from the database. " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
			DB.closeConnection();
		}
		
		return filteredTasks;
	}
	
}
