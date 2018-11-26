package com.example.service.impl;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer addUser(User user) {
        try {
            User newUser = userRepository.save(user);
            return newUser.getId();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<User> getUSer(int id) {
        Optional<User> returnUser = userRepository.findById(id);
        return returnUser;
    }


    @Override
    public Integer changeStatus(int id, Boolean newStatus) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            User userForChange = optionalUser.get();
            userForChange.setStatus(newStatus);
            userRepository.save(userForChange);
            return userForChange.getId();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public Iterable<User> getUsersByStatus(Boolean status) {
        if (status == null) {
            return userRepository.findAll();
        } else {
            return userRepository.findByStatus(status);
        }
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());

        User updateUser = optionalUser.isPresent() ? optionalUser.get() : null;
        if (updateUser != null) {

            updateUser.setStatus(user.getStatus());
            updateUser.setEmail(user.getEmail());
            updateUser.setFileURL(user.getFileURL());
            updateUser.setName(user.getName());
            updateUser.setPassword(user.getPassword());
            userRepository.save(updateUser);

        }

        return updateUser;
    }


}
