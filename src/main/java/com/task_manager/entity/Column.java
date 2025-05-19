package com.task_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Size(max=50)
    private String title;

    public Column() {

    }

    @Override
    public String toString() {
        return "Column{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Column(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull @Size(max = 50) String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(max = 50) String title) {
        this.title = title;
    }
}
