package com.task_manager.service;

import com.task_manager.entity.AppUser;
import com.task_manager.entity.Column;
import com.task_manager.entity.Priority;
import com.task_manager.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TaskDTO {
    private UUID id;
    private String title;
    private String description;
    private int index;
    private Priority priority;
    private LocalDateTime targetTime;
    private UUID assignedToId;
    private UUID statusColumnId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public TaskDTO() {

    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.index = task.getIndex();
        this.priority = task.getPriority();
        this.targetTime = task.getTargetTime();
        this.assignedToId = task.getAssignedTo().getId();
        this.statusColumnId = task.getStatusColumn().getId();
        this.createdAt = task.getCreatedAt();
        this.modifiedAt = task.getModifiedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(LocalDateTime targetTime) {
        this.targetTime = targetTime;
    }

    public UUID getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(UUID assignedToId) {
        this.assignedToId = assignedToId;
    }

    public UUID getStatusColumnId() {
        return statusColumnId;
    }

    public void setStatusColumnId(UUID statusColumnId) {
        this.statusColumnId = statusColumnId;
    }
}
