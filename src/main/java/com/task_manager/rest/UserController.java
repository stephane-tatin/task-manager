package com.task_manager.rest;

import com.task_manager.service.UserService;
import com.task_manager.entity.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     *  Retrieve all the users
     *
     * @param sortBy Sort by id
     * @param order Ascending/Descending
     * @return the Users
     */
    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC: Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        logger.info("Fetching all users. SortBy: {}, Order: {}", sortBy, order);
        return ResponseEntity.ok(userService.getAllUsers(sort));
    }

    /**
     * Retrieve user by id
     *
     * @param id id of the user
     * @return The user
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppUser>  getUserById(@PathVariable UUID id) {
        AppUser user = userService.findById(id);
        logger.info("Fetching user by id :{}", id);
        return ResponseEntity.ok(user);
    }


    /**
     * Save new user
     *
     * @param appUser The user to be saved
     * @return The saved user
     */
    @PostMapping
    public ResponseEntity<AppUser>  saveUser(@RequestBody AppUser appUser) {
        AppUser user = userService.saveUser(appUser);
        logger.info("Saving new user");
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * Delete the user by id
     *
     * @param id the id of the user
     */
    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deletedUser(id);
        logger.info("Deleting user by id :{}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @GetMapping("/logged")
//    public String getLoggedInUser() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        return "Logged-in user: " + username;
//    }
}
