package com.zdf.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zdf.taskmanager.payload.response.UserSearchResponse;
import com.zdf.taskmanager.service.BaseResponseBuilder;
import com.zdf.taskmanager.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsSvc;
    @Autowired
    private BaseResponseBuilder responseBuilder;

    @GetMapping("/getUsers")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<String> getUserDetails(@RequestParam String searchKey) {
        List<UserSearchResponse> userDetails = userDetailsSvc.getUserDetails(searchKey);
        return new ResponseEntity<String>(responseBuilder.buildBaseResponse(userDetails), HttpStatus.OK);
    }

}
