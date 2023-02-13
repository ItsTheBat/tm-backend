package com.zdf.taskmanager.payload.response;

public class TaskDeleteResponse {

    private String responseString;
    private String taskId;
    private boolean isDeleted;

    public TaskDeleteResponse(String responseString, String taskId, boolean isDeleted) {
        this.responseString = responseString;
        this.taskId = taskId;
        this.isDeleted = isDeleted;
    }

    public TaskDeleteResponse() {
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
