package com.zdf.taskmanager.payload.response;

public class UserSearchResponse {

    private String employeeId;
    private String fullName;
    private String userName;

    public UserSearchResponse() {
    }

    public UserSearchResponse(String employeeId, String fullName, String userName) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.userName = userName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
