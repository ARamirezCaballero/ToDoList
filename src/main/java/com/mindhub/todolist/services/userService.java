package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
    void updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
