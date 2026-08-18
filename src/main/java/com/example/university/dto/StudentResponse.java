package com.example.university.dto;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Long departmentId,
        String departmentName
) {}
