package com.task_manager.service;

import com.task_manager.entity.*;
import com.task_manager.exception.TaskNotFoundException;
import com.task_manager.repository.ColumnRepository;
import com.task_manager.repository.TaskRepository;
import com.task_manager.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @PostConstruct
    public void init() {
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

    public void changeIndex(Task task) {
        System.out.println("moving task" + task.getIndex() + task.getTitle());
        // Sorted tasks within the column
        List<Task> allTasks = taskRepository.findAll().stream()
                .filter(t -> task.getStatusColumn().getId().equals(t.getStatusColumn().getId()))
                .sorted(Comparator
                        .comparing(Task::getIndex, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(Task::getCreatedAt))
                .collect(Collectors.toList());

        // Remove the moved task from the list if present
        allTasks.removeIf(t -> t.getId().equals(task.getId()));

        // Ensure new index is within bounds
        int newIndex = Math.max(0, Math.min(task.getIndex(), allTasks.size()));

        allTasks.add(newIndex, task);

        for (int i = 0; i < allTasks.size(); i++) {
            allTasks.get(i).setIndex(i);
        }

        taskRepository.saveAll(allTasks);
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
        AppUser user3 = new AppUser("Ada", "Wong", "AWG", "password", Role.MANAGER);
        this.userRepository.save(user3);
        AppUser user4 = new AppUser("Hermann", "Admin", "HAN", "password", Role.ADMIN);
        this.userRepository.save(user4);

        Column column1 = new Column("To-Do");
        this.columnRepository.save(column1);

        Column column2 = new Column("Done");
        this.columnRepository.save(column2);

        // Create fake tasks
        Task task1 = new Task("Prepare Report", "Prepare the annual financial report.",
                LocalDateTime.now().plusDays(7));
        task1.setPriority(Priority.CRITICAL);
        task1.setAssignedTo(user1);
        task1.setIndex(1);
        task1.setStatusColumn(column1);

        Task task2 = new Task("Team Meeting", "Organize a team meeting to discuss project updates.",
                LocalDateTime.now().plusDays(3));
        task2.setPriority(Priority.HIGH);
        task2.setAssignedTo(user2);
        task2.setIndex(0);
        task2.setStatusColumn(column1);

        Task task4 = new Task("Clean", "Refactor some code.", LocalDateTime.now().plusDays(5));
        task4.setPriority(Priority.HIGH);
        task4.setAssignedTo(user2);
        task4.setIndex(2);
        task4.setStatusColumn(column1);

        Task task3 = new Task("Code Review", "Review pull requests for the new feature.",
                LocalDateTime.now().plusDays(1));
        task3.setPriority(Priority.LOW);
        task3.setAssignedTo(user1);
        task2.setIndex(0);
        task3.setStatusColumn(column2);

        // Save the tasks in the repository
        this.taskRepository.save(task1);
        this.taskRepository.save(task2);
        this.taskRepository.save(task3);
        this.taskRepository.save(task4);
    }
}
