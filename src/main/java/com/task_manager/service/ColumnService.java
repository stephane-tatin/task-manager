package com.task_manager.service;

import com.task_manager.entity.AppUser;
import com.task_manager.entity.Column;
import com.task_manager.entity.Role;
import com.task_manager.entity.Task;
import com.task_manager.exception.TaskNotFoundException;
import com.task_manager.repository.ColumnRepository;
import com.task_manager.repository.TaskRepository;
import com.task_manager.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {

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

    public Column findById(Long id) {
        return columnRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("User with id" + id + "not found"));
    }

    public Column updateColumn(Column column) {
        return columnRepository.save(column);
    }

    public void deletedColumn(Long id) {
        Column appUser = findById(id);
        columnRepository.delete(appUser);
    }

    public List<ColumnWithTasksDTO> getAllColumnsWithTasks() {
        List<Column> columns = columnRepository.findAll();
        List<Task> tasks = taskRepository.findAll();

        return columns.stream().map(column -> new ColumnWithTasksDTO(
                column.getId(),
                column.getName(),
                tasks.stream().filter(task -> task.getStatusColumn().getId() == column.getId()).toList()
        )).toList();
    }

}
