package com.mywaysai.automatedtaskremindertrackingapplication.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 4000)
    private String description;

    private LocalDateTime dueDate;
    private boolean completed;
    private LocalDateTime completedAt;
    private String reminderEmail; // email to send reminder to
    private int reminderMinutesBefore = 60; // default 60 min before

    // visits or tracking count (per doc)
    private Integer visits = 0;

    // constructors, getters, setters
    public Task(){}
  
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public String getReminderEmail() { return reminderEmail; }
    public void setReminderEmail(String reminderEmail) { this.reminderEmail = reminderEmail; }
    public int getReminderMinutesBefore() { return reminderMinutesBefore; }
    public void setReminderMinutesBefore(int reminderMinutesBefore) { this.reminderMinutesBefore = reminderMinutesBefore; }
    public Integer getVisits() { return visits; }
    public void setVisits(Integer visits) { this.visits = visits; }
}
