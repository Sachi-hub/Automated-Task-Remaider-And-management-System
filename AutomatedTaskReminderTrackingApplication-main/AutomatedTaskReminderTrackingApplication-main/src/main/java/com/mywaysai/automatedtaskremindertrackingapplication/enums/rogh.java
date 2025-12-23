//package com.mywaysai.automatedtaskremindertrackingapplication.enums;
//



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


















//
//1. Create Task
//POST /tasks/add
//
//Body (raw / JSON):
//
//{
//  "title": "Finish Monthly Report",
//  "description": "Prepare and upload the CSV report",
//  "dueDate": "2025-11-28T15:00:00",
//  "reminderEmail": "user@example.com",
//  "reminderMinutesBefore": 30
//}
//
//
//Response:
//
//{
//  "id": 1,
//  "title": "Finish Monthly Report",
//  "description": "Prepare and upload the CSV report",
//  "dueDate": "2025-11-28T15:00:00",
//  "completed": false,
//  "completedAt": null,
//  "reminderEmail": "user@example.com",
//  "reminderMinutesBefore": 30,
//  "visits": 0
//}
//
//2. List All Tasks
//GET /tasks/list
//
//Response:
//
//[
//  {
//    "id": 1,
//    "title": "Finish Monthly Report",
//    "completed": false
//  }
//]
//
//3. Get Single Task
//GET /tasks/1
//
//Response:
//
//{
//  "id": 1,
//  "title": "Finish Monthly Report",
//  "description": "Prepare and upload the CSV report",
//  "dueDate": "2025-11-28T15:00:00",
//  "completed": false
//}
//
// 4. Update Task
//PUT /tasks/update
//
//Body:
//
//{
//  "id": 1,
//  "title": "Finish Monthly Report - UPDATED",
//  "description": "Updated description",
//  "dueDate": "2025-11-28T17:00:00",
//  "reminderEmail": "admin@example.com",
//  "reminderMinutesBefore": 45,
//  "visits": 10
//}
//
//
//Response:
//
//{
//  "id": 1,
//  "title": "Finish Monthly Report - UPDATED"
//}
//
// 5. Mark Task Completed
//PUT /tasks/complete/1
//
//Response:
//
//{
//  "id": 1,
//  "completed": true,
//  "completedAt": "2025-11-27T19:00:00"
//}
//
//6. Delete Task
//DELETE /tasks/delete/1
//
//Response:
//
//204 No Content
//
// 7. Schedule Reminder
//POST /schedule/set/1
//
//Response:
//
//Scheduled (or rescheduled) reminder for task 1
//
// 8. Overview Report
//GET /reports/overview
//
//Response:
//
//{
//  "total": 10,
//  "completed": 4,
//  "pending": 6
//}
//
// 9. Export CSV
//POST /reports/export