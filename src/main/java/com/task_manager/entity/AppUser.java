package com.task_manager.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
public class AppUser {
    public AppUser() {

    }

    public AppUser(String firstName, String lastName, String userName, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password= password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull @Size(min = 3, max = 50) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull @Size(min = 3, max = 50) String firstName) {
        this.firstName = firstName;
    }

    public @NotNull @Size(min = 3, max = 50) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull @Size(min = 3, max = 50) String lastName) {
        this.lastName = lastName;
    }

    public @NotNull @Size(min = 3, max = 50) String getUserName() {
        return userName;
    }

    public void setUserName(@NotNull @Size(min = 3, max = 50) String userName) {
        this.userName = userName;
    }

    public @NotNull @Size(min = 8, max = 50) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 8, max = 50) String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Size(min=3, max=50)
    private String firstName;
    @NotNull
    @Size(min=3, max=50)
    private String lastName;
    @NotNull
    @Size(min=3, max=50)
    @Column(unique = true)
    private String userName;
    @NotNull
    @Size(min=8, max=50)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
