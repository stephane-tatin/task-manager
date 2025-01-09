package com.task_manager.service;

import com.task_manager.entity.AppUser;
import com.task_manager.entity.Priority;
import com.task_manager.entity.Role;
import com.task_manager.entity.Task;
import com.task_manager.exception.TaskNotFoundException;
import com.task_manager.repository.TaskRepository;
import com.task_manager.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        System.out.println("init");
        addFakeTasks();
    }

    public List<Task> getAllTasks(Sort sort) {
        return taskRepository.findAll(sort);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id" + id + "not found"));
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deletedTask(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }


    public void addFakeTasks() {
        AppUser user1 = new AppUser("Stéphane", "Tatin", "STE", "password", Role.USER);
        this.userRepository.save(user1);

        AppUser user2 = new AppUser("John", "Doe", "JDE", "password", Role.USER);
        this.userRepository.save(user2);
        AppUser user3 = new AppUser("Ada", "Wong", "AWG","password", Role.MANAGER);
        this.userRepository.save(user3);
        AppUser user4 = new AppUser("Hermann", "Admin", "HAN","password", Role.ADMIN);
        this.userRepository.save(user4);

        // Create fake tasks
        Task task1 = new Task("Prepare Report", "Prepare the annual financial report.", LocalDateTime.now().plusDays(7));
        task1.setPriority(Priority.CRITICAL);
        task1.setAssignedTo(user1);

        Task task2 = new Task("Team Meeting", "Organize a team meeting to discuss project updates.", LocalDateTime.now().plusDays(3));
        task2.setPriority(Priority.HIGH);
        task2.setAssignedTo(user2);

        Task task3 = new Task("Code Review", "Review pull requests for the new feature.", LocalDateTime.now().plusDays(1));
        task3.setPriority(Priority.LOW);
        task3.setAssignedTo(user1);

        // Save the tasks in the repository
        this.taskRepository.save(task1);
        this.taskRepository.save(task2);
        this.taskRepository.save(task3);
    }
}
