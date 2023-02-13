package com.zdf.taskmanager.payload.response;

public class UserTaskResponse {

    private String taskName;
    private String taskId;
    private String createDate;
    private String assignee;
    private String responseString;
    private String role;

    public UserTaskResponse() {
    }

    public UserTaskResponse(String responseString, String taskName, String taskId, String createDate,
            String assignee, String role) {
        this.responseString = responseString;
        this.taskName = taskName;
        this.taskId = taskId;
        this.createDate = createDate;
        this.assignee = assignee;
        this.role = role;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
