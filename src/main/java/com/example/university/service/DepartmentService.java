package com.example.university.service;

import com.example.university.dto.DepartmentRequest;
import com.example.university.dto.DepartmentResponse;
import com.example.university.dto.StudentRequest;
import com.example.university.dto.StudentResponse;
import com.example.university.entity.Department;
import com.example.university.entity.Student;
import com.example.university.exception.BadRequestException;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.repository.DepartmentRepository;
import com.example.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Transactional
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        if (departmentRepository.existsByCode(request.code())) {
            throw new BadRequestException("Code already exists");
        }

        Department department = Department.builder()
                .name(request.name())
                .code(request.code())
                .build();

        Department savedDepartment = departmentRepository.save(department);
        return mapToResponse(savedDepartment);
    }

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private DepartmentResponse mapToResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getCode()
        );
    }
}
