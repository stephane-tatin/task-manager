package com.task_manager.entity;

import com.task_manager.service.TaskDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    @NotNull
    private String title;
    private String description;
    private int index;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime targetTime;
    @ManyToOne()
    @JoinColumn(name="assigned_to", referencedColumnName = "id")
    private AppUser assignedTo;
    @ManyToOne()
    @JoinColumn(name="status_column", referencedColumnName = "id")
    private Column statusColumn;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Task(String title, String description, LocalDateTime targetTime) {
        this.title = title;
        this.description = description;
        this.targetTime = targetTime;
    }


    public Task(TaskDTO taskDTO, AppUser assignedTo, Column statusColumn) {
        this.id = taskDTO.getId();
        this.title = taskDTO.getTitle();
        this.description = taskDTO.getDescription();
        this.index = taskDTO.getIndex();
        this.priority = taskDTO.getPriority();
        this.targetTime = taskDTO.getTargetTime();
        this.assignedTo = assignedTo;
        this.statusColumn = statusColumn;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public Column getStatusColumn() {
        return statusColumn;
    }

    public void setStatusColumn(Column statusColumn) {
        this.statusColumn = statusColumn;
    }

    public AppUser getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(AppUser assignedTo) {
        this.assignedTo = assignedTo;
    }


    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Task() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(LocalDateTime targetTime) {
        this.targetTime = targetTime;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }
}
