package com.task_manager.service;

import com.task_manager.entity.AppUser;
import com.task_manager.entity.Priority;
import com.task_manager.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ColumnWithTasksDTO {
    private UUID id;
    private String title;
    private List<TaskDTO> tasks;

    ColumnWithTasksDTO(UUID id, String title, List<TaskDTO> tasks) {
        this.id = id;
        this.title = title;
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
