package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO);
    TaskDTO getTaskById(Long id);
    void deleteTask(Long id);
    void updateTask(Long id, TaskDTO taskDTO);
    List<TaskDTO> getAllTasks();
}
