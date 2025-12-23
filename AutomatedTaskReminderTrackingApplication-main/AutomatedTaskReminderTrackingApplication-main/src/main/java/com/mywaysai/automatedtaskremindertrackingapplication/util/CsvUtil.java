package com.mywaysai.automatedtaskremindertrackingapplication.util;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.mywaysai.automatedtaskremindertrackingapplication.entity.Task;

import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class CsvUtil {

    public static String tasksToCsv(List<Task> tasks) throws Exception {
        StringWriter out = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id","title","description","dueDate","completed","completedAt","visits"))) {
            DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            for (Task t : tasks) {
                printer.printRecord(
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getDueDate() == null ? "" : f.format(t.getDueDate()),
                    t.isCompleted(),
                    t.getCompletedAt() == null ? "" : f.format(t.getCompletedAt()),
                    t.getVisits()
                );
            }
        }
        return out.toString();
    }
}
