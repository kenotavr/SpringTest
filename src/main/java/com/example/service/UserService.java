package com.example.service;

import com.example.domain.User;

import java.util.Optional;

public interface UserService {

    public Integer addUser(User user);
    public Optional<User> getUSer(int id);
    public Integer changeStatus(int id, Boolean newStatus);
    public Iterable<User> getUsersByStatus(Boolean status);
    public User updateUser(User user);

}
