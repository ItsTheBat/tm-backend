package com.zdf.taskmanager.enumfields;

public enum TaskStatus {

    NEW("New"),
    INPROGRESS("In Progress"),
    RESOLVED("Resolved"),
    CLOSED("Closed"),
    REOPENED("Reopned"),
    PAUSED("Paused");

    private String status;

    private TaskStatus() {
    }

    private TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.getStatus();
    }

}
