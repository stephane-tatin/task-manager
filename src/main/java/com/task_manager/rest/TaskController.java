package com.task_manager.rest;

import com.task_manager.entity.Task;
import com.task_manager.service.ColumnWithTasksDTO;
import com.task_manager.service.TaskDTO;
import com.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/id")
    public Task getTaskById(@PathVariable UUID id) {
        return taskService.findById(id);
    }

    @PostMapping
    public Task saveTask(@RequestBody TaskDTO task) {
        if(   taskService.existsById(task.getId())) {
            return taskService.updateTask(task);
            }
        return taskService.saveTask(task);
    }

    @PostMapping("/move")
    public ResponseEntity<Void> changeIndex(@RequestBody TaskDTO task) {

         taskService.changeIndex(task);
         return ResponseEntity.ok().build();
    }


    @DeleteMapping("/id")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deletedTask(id);
    }
}
