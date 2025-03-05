package com.techzen.academy_n1224c1.service;

import com.techzen.academy_n1224c1.modal.Department;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDepartmentService {
    List<Department> findByAttributes(String name);
}
