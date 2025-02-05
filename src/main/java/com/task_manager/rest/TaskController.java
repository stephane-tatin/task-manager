package com.task_manager.rest;

import com.task_manager.entity.Task;
import com.task_manager.service.ColumnWithTasksDTO;
import com.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

//    @GetMapping
//    public List<Task> getAllTasks(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String order) {
//        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC: Sort.Direction.ASC;
//        Sort sort = Sort.by(direction, sortBy);
//
//        return taskService.getAllTasks(sort);
//    }



    @GetMapping("/id")
    public Task getTaskById(@PathVariable long id) {
        return taskService.findById(id);
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }


    @PostMapping("/move")
    public ResponseEntity<Void> changeIndex(@RequestBody Task task) {

         taskService.changeIndex(task);
         return ResponseEntity.ok().build();
    }


    @DeleteMapping("/id")
    public void deleteTask(@PathVariable long id) {
        taskService.deletedTask(id);
    }
}
