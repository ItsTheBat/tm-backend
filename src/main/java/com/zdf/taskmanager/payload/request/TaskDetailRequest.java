package com.zdf.taskmanager.payload.request;

import java.util.List;

public class TaskDetailRequest {

    private String taskId;
    private List<String> roles;

    public TaskDetailRequest() {
    }

    public TaskDetailRequest(String taskId, List<String> roles) {
        this.taskId = taskId;
        this.roles = roles;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
