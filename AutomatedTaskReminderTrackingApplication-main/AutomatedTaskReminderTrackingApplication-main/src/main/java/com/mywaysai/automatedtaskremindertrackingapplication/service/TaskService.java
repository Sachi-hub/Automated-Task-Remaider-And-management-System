package com.mywaysai.automatedtaskremindertrackingapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mywaysai.automatedtaskremindertrackingapplication.entity.Task;
import com.mywaysai.automatedtaskremindertrackingapplication.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repo;
    private final SchedulerService schedulerService;
    private final EmailService emailService;   // ✅ Added

    // ✅ Updated constructor with EmailService
    public TaskService(TaskRepository repo, SchedulerService schedulerService, EmailService emailService) {
        this.repo = repo;
        this.schedulerService = schedulerService;
        this.emailService = emailService;
    }
 // ✅ Added

    public Task addTask(Task t) {
        Task saved = repo.save(t);

        // 1️⃣ Send instant email
        if (saved.getReminderEmail() != null && !saved.getReminderEmail().isBlank()) {
            
            String subject = "Task Created: " + saved.getTitle();
            String body = "Your task has been created.\n\n" +
                    "Title: " + saved.getTitle() + "\n" +
                    "Description: " + saved.getDescription() + "\n" +
                    "Due Date: " + saved.getDueDate() + "\n" +
                    "Reminder Before: " + saved.getReminderMinutesBefore() + " mins";

            // 🔥 DIRECT email service call — cleaner
            emailService.sendSimpleMail(saved.getReminderEmail(), subject, body);
        }

        // 2️⃣ Schedule future reminder
        schedulerService.scheduleReminderForTask(saved);

        return saved;
    }
//    public Task addTask(Task t) {
//        Task saved = repo.save(t);
//        // schedule reminder
//        schedulerService.scheduleReminderForTask(saved);
//        return saved;
//    }
//
//    public Task addTask(Task t) {
//        Task saved = repo.save(t);
//
//        // 1️⃣ Send email immediately
//        if (saved.getReminderEmail() != null && !saved.getReminderEmail().isBlank()) {
//            String subject = "Task Created: " + saved.getTitle();
//            String body = "Your task has been created.\n\n" +
//                          "Title: " + saved.getTitle() + "\n" +
//                          "Description: " + saved.getDescription() + "\n" +
//                          "Due Date: " + saved.getDueDate() + "\n" +
//                          "Reminder Before: " + saved.getReminderMinutesBefore() + " mins";
//
//            // send instant mail
//            schedulerService.sendInstantEmail(saved.getReminderEmail(), subject, body);
//        }
//
//        // 2️⃣ Also schedule future reminder
//        schedulerService.scheduleReminderForTask(saved);
//
//        return saved;
//    }

    public List<Task> listAll() {
        return repo.findAll();
    }

    public Optional<Task> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public Task updateTask(Task updated) {
        Task t = repo.findById(updated.getId()).orElseThrow();
        t.setTitle(updated.getTitle());
        t.setDescription(updated.getDescription());
        t.setDueDate(updated.getDueDate());
        t.setReminderEmail(updated.getReminderEmail());
        t.setReminderMinutesBefore(updated.getReminderMinutesBefore());
        t.setVisits(updated.getVisits());
        Task saved = repo.save(t);
        schedulerService.scheduleReminderForTask(saved);
        return saved;
    }

    @Transactional
    public Task markCompleted(Long id) {
        Task t = repo.findById(id).orElseThrow();
        t.setCompleted(true);
        t.setCompletedAt(java.time.LocalDateTime.now());
        repo.save(t);
        // cancel future reminders if any
        schedulerService.cancelReminderForTask(id);
        return t;
    }

    public void delete(Long id) {
        repo.deleteById(id);
        schedulerService.cancelReminderForTask(id);
    }

    public List<Task> pendingTasks() {
        return repo.findByCompletedFalse();
    }
}
