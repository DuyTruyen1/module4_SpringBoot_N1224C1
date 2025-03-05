package com.techzen.academy_n1224c1.service;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.enums.Gender;
import com.techzen.academy_n1224c1.modal.Employee;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService {


    List<Employee> findByAttribute(EmployeeSearchRequest employeeSearchRequest);

    Optional<Employee> findById(UUID id);

    Employee save(Employee employee);

    void delete(UUID id);
}

