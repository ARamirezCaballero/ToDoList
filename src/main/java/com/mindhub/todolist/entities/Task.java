package com.mindhub.todolist.entities;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id") // A task belongs to a user
    private UserEntity user;

    // Default constructor for JPA
    public Task() {
    }

    // Constructor to create a Task with specific details
    public Task(String title, String description, TaskStatus status, UserEntity user) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (status == null) { // Task status cannot be null
            throw new IllegalArgumentException("Status must be provided");
        }
        if (user == null) {
            throw new IllegalArgumentException("User id cannot be empty");
        }

        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) { // Ensure the ID is positive
            throw new IllegalArgumentException("ID must be a positive number");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status must be provided");
        }
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("User must be provided for the task");
        }
        this.user = user;
    }
}