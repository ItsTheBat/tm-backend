package com.zdf.taskmanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zdf.taskmanager.payload.request.CreateTaskPayload;
import com.zdf.taskmanager.payload.request.TaskDetailRequest;
import com.zdf.taskmanager.payload.request.TaskListRequest;
import com.zdf.taskmanager.payload.response.TaskDataResponse;
import com.zdf.taskmanager.payload.response.TaskDeleteResponse;
import com.zdf.taskmanager.payload.response.TaskViewResponse;
import com.zdf.taskmanager.payload.response.UserTaskResponse;
import com.zdf.taskmanager.service.BaseResponseBuilder;
import com.zdf.taskmanager.service.TaskManagerServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskManagerServiceImpl taskManagerSvc;
    @Autowired
    private BaseResponseBuilder responseBuilder;

    @PostMapping("/createTask")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> createTask(@RequestBody CreateTaskPayload taskDetails, HttpServletRequest request) {
        UserTaskResponse createdTask = taskManagerSvc.createTask(taskDetails, request);
        return new ResponseEntity<String>(responseBuilder.buildBaseResponse(createdTask), HttpStatus.OK);
    }

    @PostMapping("/deleteTask")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> deleteTask(@RequestBody TaskDetailRequest userTask) {
        TaskDeleteResponse deleteResponse = taskManagerSvc.deleteTask(userTask.getTaskId(), userTask.getRoles());
        return new ResponseEntity<String>(responseBuilder.buildBaseResponse(deleteResponse), HttpStatus.OK);
    }

    @PostMapping("/updateTask")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> updateTask(@RequestBody CreateTaskPayload taskDetails, HttpServletRequest request) {
        TaskViewResponse updatedTask = taskManagerSvc.updateTask(taskDetails, request);
        return new ResponseEntity<String>(responseBuilder.buildBaseResponse(updatedTask), HttpStatus.OK);
    }

    @PostMapping("/getUserTasks")
    @PreAuthorize("hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<String> getUserTasks(@RequestBody TaskListRequest userTask) {
        TaskDataResponse taskDetails = taskManagerSvc.getUserTasks(userTask);
        return new ResponseEntity<String>(responseBuilder.buildBaseResponse(taskDetails), HttpStatus.OK);
    }

    @PostMapping("/getTaskDetail")
    @PreAuthorize("hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity<String> getTaskDetail(@RequestBody TaskDetailRequest userTask) {
        TaskViewResponse taskDetail = taskManagerSvc.fetchTask(userTask.getTaskId(), userTask.getRoles());
        return new ResponseEntity<String>(responseBuilder.buildBaseResponse(taskDetail), HttpStatus.OK);
    }

}
