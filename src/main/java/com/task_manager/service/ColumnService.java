package com.task_manager.service;

import com.task_manager.entity.Column;
import com.task_manager.entity.Task;
import com.task_manager.exception.TaskNotFoundException;
import com.task_manager.repository.ColumnRepository;
import com.task_manager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ColumnService {
    @Autowired
    private TaskService taskService;


    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<Column> getAllUsers(Sort sort) {
        return columnRepository.findAll(sort);
    }

    public Column saveColumn(Column column) {
        return columnRepository.save(column);
    }

    public Column findById(UUID id) {
        return columnRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("User with id" + id + "not found"));
    }

    public Column updateColumn(Column column) {
        return columnRepository.save(column);
    }

    @Transactional
    public void deletedColumn(UUID id) {
        Column column = findById(id);
        taskRepository.deleteByStatusColumn(column);
        columnRepository.delete(column);
    }

    public List<ColumnWithTasksDTO> getAllColumnsWithTasks() {
        List<Column> columns = columnRepository.findAll();
        List<Task> tasks = taskRepository.findAll();

        return columns.stream().map(column -> new ColumnWithTasksDTO(
                column.getId(),
                column.getTitle(),
                tasks.stream()
                        .filter(task -> task.getStatusColumn().getId().equals(column.getId()))
                        .sorted(Comparator
                                .comparing(Task::getIndex, Comparator.nullsLast(Comparator.naturalOrder()))
                                .thenComparing(Task::getCreatedAt))
                        .map((task) -> taskService.toTaskDTO(task))
                        .toList()
        )).toList();
    }

}
