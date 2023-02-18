package com.zdf.taskmanager.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.zdf.taskmanager.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

    @Query(value = "{taskId: '?0'}")
    Task findTaskByTaskId(String taskId);

    @Query(value = "{taskName: '?0'}")
    List<Task> findTaskByTaskName(String taskName);

    @Query(value = "{'$and':[{createdBy: '?1'}, {'$or': [{'taskName': {$regex: ?0, $options: 'i' }}, {'taskId': {$regex: ?0, $options: 'i' }}]}]}")
    List<Task> findTaskByTaskNameOrTaskIdAndCreatedBy(String searchParam, String createdBy);

    @Query(value = "{'$and':[{assignedTo: '?1'}, {'$or': [{'taskName': {$regex: ?0, $options: 'i' }}, {'taskId': {$regex: ?0, $options: 'i' }}]}]}")
    List<Task> findTaskByTaskNameOrTaskIdAndAssignedTo(String searchParam, String assignee);

    @Query(value = "{assignedTo: '?0'}")
    List<Task> findTaskByAssignedTo(String assignee);

    @Query(value = "{createdBy: '?0'}")
    List<Task> findTaskByCreatedBy(String createdBy);

}
