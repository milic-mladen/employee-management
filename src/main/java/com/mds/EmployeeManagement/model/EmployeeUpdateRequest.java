package com.mds.EmployeeManagement.model;

public record EmployeeUpdateRequest(
        String firstName,
        String lastName,
        String username,
        String email,
        double salary
) {}

