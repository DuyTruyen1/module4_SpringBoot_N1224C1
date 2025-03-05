package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.enums.Gender;
import com.techzen.academy_n1224c1.modal.Employee;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import com.techzen.academy_n1224c1.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService implements IEmployeeService {

    IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findByAttribute(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.findByAttributes(employeeSearchRequest.getName(),
                employeeSearchRequest.getDobFrom(),
                employeeSearchRequest.getDobTo(),
                employeeSearchRequest.getGender() != null ? Gender.valueOf(employeeSearchRequest.getGender().toString()) : null,
                employeeSearchRequest.getSalaryRange(),
                employeeSearchRequest.getPhone(),
                employeeSearchRequest.getDepartmentId());
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
    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }

}

