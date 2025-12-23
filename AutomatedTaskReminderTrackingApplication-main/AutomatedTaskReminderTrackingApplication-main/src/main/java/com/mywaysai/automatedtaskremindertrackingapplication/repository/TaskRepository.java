package com.mywaysai.automatedtaskremindertrackingapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mywaysai.automatedtaskremindertrackingapplication.entity.Task;

import java.util.List;
import java.time.LocalDateTime;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompletedFalse();
    List<Task> findByDueDateBefore(LocalDateTime time);
}

