package com.zdf.taskmanager.payload.request;

import java.util.List;

public class TaskListRequest {

    private String employeeId;
    private List<String> roles;
    private String searchKey;

    public TaskListRequest() {
    }

    public TaskListRequest(String employeeId, List<String> roles, String searchKey) {
        this.employeeId = employeeId;
        this.roles = roles;
        this.searchKey = searchKey;
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

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
