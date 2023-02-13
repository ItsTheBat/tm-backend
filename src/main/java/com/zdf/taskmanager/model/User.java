package com.zdf.taskmanager.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User extends Audit {

    @Transient
    public static final String SEQUENCE_NAME = "user_seq";

    @Id
    private String id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;

    private String companyId;
    private String companyName;
    private String employeeId;
    private String firstName;
    private String lastName;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String createdBy, String updatedBy, LocalDateTime createdAt, LocalDateTime updatedAt, String id,
            @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
            @NotBlank @Size(max = 120) String password, String companyId, String companyName, String description,
            String employeeId, String firstName, String lastName, Set<Role> roles) {
        super(createdBy, updatedBy, createdAt, updatedAt);
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public User(String id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
            @NotBlank @Size(max = 120) String password, String companyId, String companyName, String employeeId,
            String firstName, String lastName, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
