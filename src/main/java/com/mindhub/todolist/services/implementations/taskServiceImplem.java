package com.mindhub.todolist.services.implementations;

import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.entities.Task;
import com.mindhub.todolist.entities.UserEntity;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.repositories.TaskRepository;
import com.mindhub.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all tasks
    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            throw new RuntimeException("No tasks found");
        }
        return tasks.stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getUser().getId()))
                .collect(Collectors.toList());
    }

    // Get task by ID
    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task with ID " + id + " not found"));
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getUser().getId());
    }

    // Create a new task
    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        if (taskDTO.getDescription() == null || taskDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        if (taskDTO.getStatus() == null) {
            throw new IllegalArgumentException("Task status must be provided");
        }
        if (taskDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID must be provided for the task");
        }

        // Buscar el usuario asociado al userId
        UserEntity user = userRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + taskDTO.getUserId()));

        // Crear la tarea con el usuario asociado
        Task task = new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getStatus(), user);
        taskRepository.save(task);

        // Devolver el DTO de la tarea creada
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), user.getId());
    }

    // Update an existing task
    @Override
    public void updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task with ID " + id + " not found"));
        if (taskDTO.getTitle() != null && !taskDTO.getTitle().isEmpty()) {
            task.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null && !taskDTO.getDescription().isEmpty()) {
            task.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getStatus() != null) {
            task.setStatus(taskDTO.getStatus());
        }
        taskRepository.save(task);
    }

    // Delete a task by ID
    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task with ID " + id + " not found");
        }
        taskRepository.deleteById(id);
    }
}
