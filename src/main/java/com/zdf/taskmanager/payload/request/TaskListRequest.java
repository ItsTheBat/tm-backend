package com.zdf.taskmanager.payload.request;

import java.util.List;

public class TaskListRequest {

    private String employeeId;
    private List<String> roles;

    public TaskListRequest() {
    }

    public TaskListRequest(String employeeId, List<String> roles) {
        this.employeeId = employeeId;
        this.roles = roles;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
