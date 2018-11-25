package com.example.service.impl;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public int addUser(User user) {
        try {
            User newUser = userRepository.save(user);
            return newUser.getId();
        }
        catch (Exception e){
            log.info(e.getMessage());
        }
    }

    @Override
    public User getUSer(int id) {
        return null;
    }


}
