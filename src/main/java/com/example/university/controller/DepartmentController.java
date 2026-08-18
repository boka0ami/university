package com.example.university.controller;

import com.example.university.dto.DepartmentRequest;
import com.example.university.dto.DepartmentResponse;
import com.example.university.dto.StudentRequest;
import com.example.university.dto.StudentResponse;
import com.example.university.entity.Department;
import com.example.university.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(request));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllStudents() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}
