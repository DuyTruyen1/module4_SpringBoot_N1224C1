package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.modal.Department;
import com.techzen.academy_n1224c1.repository.IDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final IDepartmentRepository departmentRepository;

    public List<Department> searchDepartments(String name) {
        return departmentRepository.findByAttributes(name);
    }
}

