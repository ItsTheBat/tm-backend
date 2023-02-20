package com.zdf.taskmanager.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zdf.taskmanager.contants.CommonConstants;
import com.zdf.taskmanager.dto.Document;
import com.zdf.taskmanager.enumfields.TaskStatus;
import com.zdf.taskmanager.model.ERole;
import com.zdf.taskmanager.model.Task;
import com.zdf.taskmanager.payload.request.CreateTaskPayload;
import com.zdf.taskmanager.payload.request.TaskListRequest;
import com.zdf.taskmanager.payload.response.TaskData;
import com.zdf.taskmanager.payload.response.TaskDataResponse;
import com.zdf.taskmanager.payload.response.TaskDeleteResponse;
import com.zdf.taskmanager.payload.response.TaskViewResponse;
import com.zdf.taskmanager.payload.response.UserTaskResponse;
import com.zdf.taskmanager.repository.TaskRepository;
import com.zdf.taskmanager.util.CommonUtil;

@Service
public class TaskManagerServiceImpl {

    private final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private GridFsTemplate template;
    @Autowired
    private GridFsOperations operations;
    @Autowired
    private TaskRepository taskRepo;

    public UserTaskResponse createTask(CreateTaskPayload taskDetails, HttpServletRequest request) {
        Task task = formTask(taskDetails, request);
        if (Objects.nonNull(task)) {
            try {
                task = taskRepo.save(task);
                return formSuccessTaskResponse(task, ERole.ROLE_MANAGER.toString());
            } catch (Exception ex) {
                return formFailTaskResponse("fail_save");
            }
        } else {
            return formFailTaskResponse("fail_attach");
        }
    }

    public TaskViewResponse updateTask(CreateTaskPayload taskDetails, HttpServletRequest request) {
        Task task = formTask(taskDetails, request);
        String role = ERole.ROLE_MANAGER.toString();
        if (Objects.nonNull(task)) {
            try {
                task = taskRepo.save(task);
                if (Objects.nonNull(task)) {
                    return formTaskView(task, role, "success");
                } else {
                    return formTaskView(task, role, "fail_task_not_found");
                }
            } catch (Exception ex) {
                return formTaskView(task, role, "fail_task_not_found");
            }
        } else {
            return formTaskView(task, role, "fail_task_not_found");
        }
    }

    private UserTaskResponse formSuccessTaskResponse(Task task, String role) {
        UserTaskResponse userTaskResponse = new UserTaskResponse();
        userTaskResponse.setAssignee(task.getAssignedTo());
        userTaskResponse.setCreateDate(task.getCreatedAt().format(formatDate));
        userTaskResponse.setResponseString("success");
        userTaskResponse.setTaskId(task.getTaskId());
        userTaskResponse.setTaskName(task.getTaskName());
        userTaskResponse.setRole(role);
        return userTaskResponse;
    }

    private UserTaskResponse formFailTaskResponse(String failReason) {
        UserTaskResponse failResponse = new UserTaskResponse();
        failResponse.setResponseString(failReason);
        return failResponse;
    }

    private Task formTask(CreateTaskPayload taskDetails, HttpServletRequest request) {
        Task task = new Task();
        if (StringUtils.isNotBlank(taskDetails.getTaskId())) {
            task = taskRepo.findTaskByTaskId(taskDetails.getTaskId());
        } else {
            task.setTaskId(StringUtils.isBlank(taskDetails.getTaskId()) ? CommonUtil.generateTaskId()
                    : taskDetails.getTaskId());
            task.setCreatedAt(LocalDateTime.now());
            task.setCreatedBy(request.getUserPrincipal().getName());
        }
        task.setTaskName(taskDetails.getTaskName());
        task.setDecsription(taskDetails.getTaskDescription());
        task.setAssignedTo(taskDetails.getAssignee());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUpdatedBy(request.getUserPrincipal().getName());
        task.setStartDate(LocalDateTime.parse(taskDetails.getStartDate(),
                DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FMT)));
        task.setEndDate(LocalDateTime.parse(taskDetails.getEndDate(),
                DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FMT)));
        task.setStatus(
                StringUtils.isBlank(taskDetails.getStatus()) ? TaskStatus.NEW.toString() : taskDetails.getStatus());
        task.setDocs(getDocuments(taskDetails.getAttachments()));
        return task;
    }

    private List<String> getDocuments(List<MultipartFile> files) {
        List<String> attachments = files.stream().filter(Objects::nonNull).map(file -> {
            try {
                DBObject metadata = new BasicDBObject();
                metadata.put("fileSize", file.getSize());
                Object fileID = template.store(file.getInputStream(), file.getOriginalFilename(),
                        file.getContentType(), metadata);
                return fileID.toString();
            } catch (Exception ex) {
                return null;
            }
        }).collect(Collectors.toList());
        return attachments.stream().anyMatch(file -> Objects.isNull(file)) ? null : attachments;
    }

    public TaskDeleteResponse deleteTask(String taskId, List<String> roles) {
        try {
            String role = getCurrentUserRole(roles);
            if (role.equalsIgnoreCase("ROLE_MANAGER")) {
                Task task = taskRepo.findTaskByTaskId(taskId);
                if (Objects.nonNull(task)) {
                    taskRepo.delete(task);
                    return new TaskDeleteResponse("success", taskId, true);
                } else {
                    return new TaskDeleteResponse("fail_task_not_found", taskId, false);
                }
            } else {
                return new TaskDeleteResponse("unauthorised_user", taskId, false);
            }
        } catch (Exception ex) {
            return new TaskDeleteResponse("fail_delete", taskId, false);
        }
    }

    public TaskDataResponse getUserTasks(TaskListRequest userTask) {
        String role = getCurrentUserRole(userTask.getRoles());
        if (StringUtils.isNotBlank(role)) {
            if (role.equals("ROLE_MANAGER")) {
                List<Task> tasks = taskRepo.findTaskByTaskNameOrTaskIdAndCreatedBy(userTask.getSearchKey(),
                        userTask.getEmployeeId());
                List<TaskData> taskList = tasks.stream().map(task -> formTaskListResponse(task, "ROLE_MANAGER"))
                        .collect(Collectors.toList());
                return new TaskDataResponse("success", role, taskList);
            } else {
                List<Task> tasks = taskRepo.findTaskByTaskNameOrTaskIdAndAssignedTo(userTask.getSearchKey(),
                        userTask.getEmployeeId());
                List<TaskData> taskList = tasks.stream().map(task -> formTaskListResponse(task, "ROLE_USER"))
                        .collect(Collectors.toList());
                return new TaskDataResponse("success", role, taskList);
            }
        } else {
            return null;
        }
    }

    private String getCurrentUserRole(List<String> roles) {
        String role = null;
        if (CommonUtil.isNotEmpty(roles)) {
            if (roles.stream().anyMatch(r -> r.equals("ROLE_MANAGER"))) {
                role = "ROLE_MANAGER";
            } else {
                role = "ROLE_USER";
            }
        }
        return role;
    }

    public TaskData formTaskListResponse(Task task, String role) {
        TaskData taskData = new TaskData();
        taskData.setAssignee(task.getAssignedTo());
        taskData.setCreateDate(task.getCreatedAt().format(formatDate));
        taskData.setTaskId(task.getTaskId());
        taskData.setTaskName(task.getTaskName());
        taskData.setStatus(task.getStatus());
        return taskData;
    }

    public TaskViewResponse fetchTask(String taskId, List<String> roles) {
        String role = getCurrentUserRole(roles);
        Task task = taskRepo.findTaskByTaskId(taskId);
        if (Objects.nonNull(task)) {
            return formTaskView(task, role, "success");
        } else {
            return formTaskView(task, role, "fail_task_not_found");
        }
    }

    private TaskViewResponse formTaskView(Task task, String role, String responseStr) {
        TaskViewResponse taskView = new TaskViewResponse();
        BeanUtils.copyProperties(task, taskView);
        List<Document> documents = task.getDocs().stream().map(docId -> {
            try {
                Document document = new Document();
                GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(docId)));
                if (Objects.nonNull(gridFSFile) && Objects.nonNull(gridFSFile.getMetadata())) {
                    document.setId(docId);
                    document.setDocumentName(gridFSFile.getFilename());
                    document.setDocumentType(gridFSFile.getMetadata().get("_contentType").toString());
                    document.setSize(gridFSFile.getMetadata().get("fileSize").toString());
                    document.setContent(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
                }
                return document;
            } catch (Exception ex) {
                return null;
            }

        }).collect(Collectors.toList());
        taskView.setDocs(documents);
        taskView.setStartDate(task.getStartDate().format(formatDate));
        taskView.setEndDate(task.getEndDate().format(formatDate));
        taskView.setResponseString(responseStr);
        return taskView;
    }

}
