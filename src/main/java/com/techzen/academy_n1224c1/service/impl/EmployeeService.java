package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.modal.Employee;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import com.techzen.academy_n1224c1.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> search(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.search(employeeSearchRequest);
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);
    }
}
