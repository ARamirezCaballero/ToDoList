package com.mindhub.todolist.dtos;

import com.mindhub.todolist.entities.TaskStatus;

public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long userId;

    // Empty Constructor
    public TaskDTO() {}

    // Constructor with fields
    public TaskDTO(Long id, String title, String description, TaskStatus status, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public TaskStatus getStatus() {

        return status;
    }

    public void setStatus(TaskStatus status) {

        this.status = status;
    }

    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }
}
