package com.zdf.taskmanager.payload.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CreateTaskPayload {

    private String taskId;
    private String taskName;
    private String taskDescription;
    private String assignee;
    private String startDate;
    private String endDate;
    private String status;
    private List<MultipartFile> attachments = new ArrayList<>();

    public CreateTaskPayload() {
    }

    public CreateTaskPayload(String taskId, String taskName, String taskDescription, String assignee, String status,
            String startDate, String endDate, List<MultipartFile> attachments) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.assignee = assignee;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.attachments = attachments;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
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

    public List<MultipartFile> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MultipartFile> attachments) {
        this.attachments = attachments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
