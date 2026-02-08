# Task Manager CLI

A command-line task management system with file persistence built in Java.

## Features

- Add tasks with description, priority, and status
- Mark tasks as completed
- List all tasks
- Filter tasks by status or priority
- Delete tasks
- Persistent storage (save/load from file)
- Auto-save on exit

## Technologies

- Java 8+
- File I/O (BufferedReader/BufferedWriter)
- ArrayList
- Enums (Priority, Status)
- Exception handling

## How to Run
```bash
javac -d bin src/com/ismael/taskmanager/*.java
java -cp bin com.ismael.taskmanager.Main
```

## Project Structure
```
src/
└── com/ismael/taskmanager/
    ├── Main.java
    ├── Task.java
    ├── TaskManager.java
    ├── Priority.java (enum)
    └── Status.java (enum)
```

## What I Learned

- Working with enums for fixed value sets
- File persistence (reading/writing data)
- Exception handling with custom messages
- ArrayList manipulation and filtering
- Try-with-resources for file operations

## Author

Ismael - [GitHub](https://github.com/ismajoventino)
