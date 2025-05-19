package com.task_manager.rest;

import com.task_manager.entity.Column;
import com.task_manager.service.ColumnService;
import com.task_manager.service.ColumnWithTasksDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/columns")
public class ColumnController {

    @Autowired
    private ColumnService columnService;
    private static final Logger logger = LoggerFactory.getLogger(ColumnController.class);

    /**
     * retrieve all the columns with their associated tasks
     *
     * @param sortBy Property to sort the data by
     * @param order Ascending or descending
     * @return List of ColumnWithTasksDTO
     */
    @GetMapping
    public List<ColumnWithTasksDTO> getAllColumnsWithTasks(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String order) {
        logger.info("Fetching all columns with tasks. SortBy: {}, Order: {}", sortBy, order);
        //        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC: Sort.Direction.ASC;
//        Sort sort = Sort.by(direction, sortBy);

        return columnService.getAllColumnsWithTasks();
    }

    /**
     * Create or update a column.
     *
     * @param column The column to be added/updated
     * @return The saved column
     */
    @PostMapping
    public ResponseEntity<Column> saveColumn(@RequestBody Column column) {
        Column savedCol = columnService.saveColumn(column);
        logger.info("Saved column with ID: {}", savedCol.getId());
        return  ResponseEntity.ok(savedCol);
    }

    /**
     * Delete a column by its ID.
     *
     * @param id Id of the column to deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColumn(@PathVariable UUID id) {
        columnService.deletedColumn(id);
        logger.info("Deleted column with ID: {}", id);
        return ResponseEntity.ok().build();
    }
}
