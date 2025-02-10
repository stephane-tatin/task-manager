package com.task_manager.rest;

import com.task_manager.service.UserService;
import com.task_manager.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<AppUser> getAllUsers(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC: Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        return userService.getAllUsers(sort);
    }

    @GetMapping("/id")
    public AppUser getUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }


    @PostMapping
    public AppUser saveUser(@RequestBody AppUser appUser) {
        return userService.saveUser(appUser);
    }

    @DeleteMapping("/id")
    public void deleteUser(@PathVariable UUID id) {
        userService.deletedUser(id);
    }
}
