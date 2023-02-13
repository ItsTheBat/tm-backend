package com.zdf.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.zdf.taskmanager.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{employeeId: '?0'}")
    User findUserByEmployeeId(String employeeId);

    @Query(value = "{userName: '?0'}")
    List<User> findUserByUserName(String userName);

    @Query(value = "{'$or':[ {'userName': {$regex: ?0, $options: 'i' }}, {'employeeId': {$regex: ?0, $options: 'i' }} ] }")
    List<User> findByUserNameOrEmployeeId(String searchParam);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
