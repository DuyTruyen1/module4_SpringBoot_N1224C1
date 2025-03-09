package com.techzen.academy_n1224c1.service;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.modal.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService {


    Page<Employee> findByAttribute(EmployeeSearchRequest employeeSearchRequest , Pageable pageable);

    Optional<Employee> findById(UUID id);

    Employee save(Employee employee);

    void delete(UUID id);
}

