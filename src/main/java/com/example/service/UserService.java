package com.example.service;

import com.example.domain.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public int addUser(User user);
    public User getUSer(int id);

}
