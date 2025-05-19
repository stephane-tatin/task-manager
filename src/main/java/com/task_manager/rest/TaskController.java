package com.task_manager.rest;

import com.task_manager.entity.Task;
import com.task_manager.service.TaskDTO;
import com.task_manager.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     *
     * Retrieve a task by id
     *
     * @param id The UUID of the task
     * @return The task
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID id) {
        Task task = taskService.findById(id);
        logger.info("Get task with Id: {}", task.getId());

        return ResponseEntity.ok(task);
    }

    /**
     * Create/Update a task
     *
     * @param task the task to be saved/updated
     * @return The saved task
     */
    @PostMapping
    public Task saveTask(@RequestBody TaskDTO task) {
        if(task.getId()!= null && taskService.existsById(task.getId())) {
            logger.info("Updating task with id: {}", task.getId());
            return taskService.updateTask(task);
        }
        logger.info("Creating new task");
        return taskService.saveTask(task);
    }

    /**
     * Change the index of a task inside a column and the index of the other task according to their positions
     *
     * @param task the task to be updated
     */
    @PostMapping("/move")
    public ResponseEntity<Void> changeIndex(@RequestBody TaskDTO task) {
         logger.info("Changing index task with id: {}", task.getId());
         taskService.changeIndex(task);
         return ResponseEntity.ok().build();
    }


    /**
     * Deleted task by id
     *
     * @param id the id of the task to be deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        logger.info("Deleting task with id: {}", id);
         taskService.deletedTask(id);
        return ResponseEntity.ok().build();
    }
}
