package com.zdf.taskmanager.payload.response;

import java.util.ArrayList;
import java.util.List;

import com.zdf.taskmanager.dto.Document;

public class TaskViewResponse {

    private String taskId;
    private String taskName;
    private String decsription;
    private String assignedTo;
    private String startDate;
    private String endDate;
    private String status;
    private List<Document> docs = new ArrayList<>();
    private String responseString;

    public TaskViewResponse() {
    }

    public TaskViewResponse(String taskId, String taskName, String decsription, String assignedTo,
            String startDate, String endDate, String status, List<Document> docs, String responseString) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.decsription = decsription;
        this.assignedTo = assignedTo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.docs = docs;
        this.responseString = responseString;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
