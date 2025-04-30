package com.task_manager.service;

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
