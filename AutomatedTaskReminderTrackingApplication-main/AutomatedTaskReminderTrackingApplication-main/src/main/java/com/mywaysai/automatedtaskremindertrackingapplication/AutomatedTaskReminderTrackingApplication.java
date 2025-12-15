package com.mywaysai.automatedtaskremindertrackingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutomatedTaskReminderTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatedTaskReminderTrackingApplication.class, args);
	}

}




//sample for postman request
//{
//	  "title": "Finish Monthly Report================ORACLE BANKING",
//	  "description": "Prepare and upload the CSV report    *************92ORACLE BANKING",
//	  "dueDate": "2025-11-25T07:56:00",
//	  "reminderEmail": "madhuritakekar1@gmail.com",
//	  "reminderMinutesBefore": 30
//	}

//{
//	  "title": "Finish Monthly Report================ORACLE BANKING",
//	  "description": "Prepare and upload the CSV report    *************92ORACLE BANKING",
//	  "dueDate": "2025-11-25T07:56:00",
//	  "reminderEmail": "madhuritakekar2@gmail.com",
//	  "reminderMinutesBefore": 30
//	}