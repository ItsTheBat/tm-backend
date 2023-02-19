package com.zdf.taskmanager.payload.response;

public class TaskData {

    private String taskName;
    private String taskId;
    private String createDate;
    private String assignee;
    private String status;

    public TaskData(String taskName, String taskId, String createDate, String assignee, String status) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.createDate = createDate;
        this.assignee = assignee;
        this.status = status;
    }

    public TaskData() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
