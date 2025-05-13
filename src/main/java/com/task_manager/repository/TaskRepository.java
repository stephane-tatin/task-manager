package com.task_manager.repository;

import com.task_manager.entity.Column;
import com.task_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    void deleteByStatusColumn(Column statusColumn);
}
