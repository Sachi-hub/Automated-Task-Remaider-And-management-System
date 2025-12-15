package com.mywaysai.automatedtaskremindertrackingapplication.dto;



import java.time.LocalDateTime;

public class CreateTaskRequest {
    public String title;
    public String description;
    public LocalDateTime dueDate;
    public String reminderEmail;
    public Integer reminderMinutesBefore;
}
