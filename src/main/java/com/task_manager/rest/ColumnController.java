package com.task_manager.rest;

import com.task_manager.entity.Column;
import com.task_manager.service.ColumnService;
import com.task_manager.service.ColumnWithTasksDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping
    public Column saveColumn(@RequestBody Column column) {
        return columnService.saveColumn(column);
    }

    @DeleteMapping("/{id}")
    public void deleteColumn(@PathVariable UUID id) {
        columnService.deletedColumn(id);
    }
}
