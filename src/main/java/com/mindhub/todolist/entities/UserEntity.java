package com.mindhub.todolist.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)// A user can have many tasks
    private List<Task> tasks = new ArrayList<>();

    // Default constructor for JPA
    public UserEntity() {
    }

    // Constructor to create a UserEntity object with specific details
    public UserEntity(String username, String password, String email) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.length() < 6) { // Minimum password length check
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        if (email == null || email.isEmpty() || !email.contains("@")) { // Simple email validation
            throw new IllegalArgumentException("Email must be a valid email address");
        }

        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) { // Ensuring valid ID value
            throw new IllegalArgumentException("ID must be a positive number");
        }
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) { // For passwords to be short
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email must be a valid email address");
        }
        this.email = email;
    }

    // Getter for tasks list
    public List<Task> getTasks() {
        return tasks;
    }

    // Setter for tasks list
    public void setTasks(List<Task> tasks) {
        if (tasks == null) {
            throw new IllegalArgumentException("Tasks list cannot be null");
        }
        this.tasks = tasks;
    }
}
