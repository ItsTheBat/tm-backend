package com.zdf.taskmanager.payload.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zdf.taskmanager.dto.Document;

public class TaskViewResponse {

    private String taskId;
    private String taskName;
    private String decsription;
    private String assignedTo;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Document> docs = new ArrayList<>();
    private String responseString;
    private String role;

    public TaskViewResponse() {
    }

    public TaskViewResponse(String taskId, String taskName, String decsription, String assignedTo,
            LocalDateTime startDate, LocalDateTime endDate, List<Document> docs, String responseString, String role) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.decsription = decsription;
        this.assignedTo = assignedTo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.docs = docs;
        this.responseString = responseString;
        this.role = role;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDecsription() {
        return decsription;
    }

    public void setDecsription(String decsription) {
        this.decsription = decsription;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Document> getDocs() {
        return docs;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
