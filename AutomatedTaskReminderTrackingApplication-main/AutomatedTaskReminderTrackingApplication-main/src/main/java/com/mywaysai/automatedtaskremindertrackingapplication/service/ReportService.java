package com.mywaysai.automatedtaskremindertrackingapplication.service;



import org.springframework.stereotype.Service;

import com.mywaysai.automatedtaskremindertrackingapplication.repository.TaskRepository;
import com.mywaysai.automatedtaskremindertrackingapplication.util.CsvUtil;
import com.mywaysai.automatedtaskremindertrackingapplication.entity.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final TaskRepository repo;
    public ReportService(TaskRepository repo) { this.repo = repo; }

    public Map<String, Object> overview() {
        List<Task> all = repo.findAll();
        long total = all.size();
        long completed = all.stream().filter(Task::isCompleted).count();
        long pending = total - completed;
        Map<String,Object> m = new HashMap<>();
        m.put("total", total);
        m.put("completed", completed);
        m.put("pending", pending);
        return m;
    }

    public String exportAllTasksCsv() throws Exception {
        List<Task> all = repo.findAll();
        return CsvUtil.tasksToCsv(all);
    }
}
