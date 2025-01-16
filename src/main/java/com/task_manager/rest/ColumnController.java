package com.task_manager.rest;

import com.task_manager.entity.Task;
import com.task_manager.service.ColumnService;
import com.task_manager.service.ColumnWithTasksDTO;
import com.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/columns")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @GetMapping
    public List<ColumnWithTasksDTO> getAllColumnsWithTasks(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String order) {
//        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC: Sort.Direction.ASC;
//        Sort sort = Sort.by(direction, sortBy);

        return columnService.getAllColumnsWithTasks();
    }
//
//    @GetMapping("/id")
//    public Task getTaskById(@PathVariable long id) {
//        return taskService.findById(id);
//    }

    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        return columnService.sa(task);
    }

    @DeleteMapping("/id")
    public void deleteTask(@PathVariable long id) {
        taskService.deletedTask(id);
    }
}
