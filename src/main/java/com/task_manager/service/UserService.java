package com.task_manager.service;

import com.task_manager.entity.AppUser;
import com.task_manager.exception.TaskNotFoundException;
import com.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public List<AppUser> getAllUsers(Sort sort) {
        return userRepository.findAll(sort);
    }

    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public AppUser findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("User with id" + id + "not found"));
    }

    public AppUser updateUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public void deletedUser(UUID id) {
        AppUser appUser = findById(id);
        userRepository.delete(appUser);
    }

}
