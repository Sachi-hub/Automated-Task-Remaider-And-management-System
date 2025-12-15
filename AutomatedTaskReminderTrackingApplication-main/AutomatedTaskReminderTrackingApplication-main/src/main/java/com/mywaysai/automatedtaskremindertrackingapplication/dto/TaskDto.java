package com.mywaysai.automatedtaskremindertrackingapplication.dto;



import java.time.LocalDateTime;

public class TaskDto {
    public Long id;
    public String title;
    public String description;
    public LocalDateTime dueDate;
    public boolean completed;
    public LocalDateTime completedAt;
    public String reminderEmail;
    public Integer reminderMinutesBefore;
    public Integer visits;
}


//
//automated-task-reminder/
//├─ pom.xml
//├─ src/main/java/com/example/taskreminder/
//│  ├─ TaskReminderApplication.java
//│  ├─ config/
//│  │  ├─ SchedulerConfig.java
//│  │  └─ MailConfig.java
//│  ├─ controller/
//│  │  ├─ TaskController.java
//│  │  ├─ ScheduleController.java
//│  │  └─ ReportController.java
//│  ├─ dto/
//│  │  ├─ CreateTaskRequest.java
//│  │  └─ TaskDto.java
//│  ├─ entity/
//│  │  └─ Task.java
//│  ├─ repository/
//│  │  └─ TaskRepository.java
//│  ├─ service/
//│  │  ├─ TaskService.java
//│  │  ├─ SchedulerService.java
//│  │  ├─ ReportService.java
//│  │  └─ EmailService.java
//│  └─ util/
//│     └─ CsvUtil.java
//├─ src/main/resources/
//│  ├─ application.yml
//│  └─ data.sql   (optional sample data)
//└─ README.md
