package com.zdf.taskmanager.payload.response;

import java.util.ArrayList;
import java.util.List;

public class TaskDataResponse {

    private String responseString;
    private String role;
    private List<TaskData> taskData = new ArrayList<>();

    public TaskDataResponse(String responseString, String role, List<TaskData> taskData) {
        this.responseString = responseString;
        this.role = role;
        this.taskData = taskData;
    }

    public TaskDataResponse() {
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

    public List<TaskData> getTaskData() {
        return taskData;
    }

    public void setTaskData(List<TaskData> taskData) {
        this.taskData = taskData;
    }

}
