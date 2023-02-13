package com.zdf.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdf.taskmanager.model.User;
import com.zdf.taskmanager.repository.UserRepository;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepo;

    // public User insertUser() {

    // }

}