package com.mds.EmployeeManagement.model;

public record EmployeeCreationRequest(
        String firstName,
        String lastName,
        String username,
        String email,
        double salary
) {}
