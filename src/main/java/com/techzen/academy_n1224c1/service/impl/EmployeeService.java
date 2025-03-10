package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.enums.Gender;
import com.techzen.academy_n1224c1.modal.Employee;
import com.techzen.academy_n1224c1.repository.EmployeeSpecification;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import com.techzen.academy_n1224c1.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService implements IEmployeeService {

    IEmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findByAttribute(EmployeeSearchRequest employeeSearchRequest, Pageable pageable) {
        Specification<Employee> specification = Specification.where(EmployeeSpecification.hasName(employeeSearchRequest.getName()))
                .and(EmployeeSpecification.hasDobFrom(employeeSearchRequest.getDobFrom()))
                .and(EmployeeSpecification.hasDobTo(employeeSearchRequest.getDobTo()))
                .and(EmployeeSpecification.hasGender(employeeSearchRequest.getGender()))
                .and(EmployeeSpecification.hasPhone(employeeSearchRequest.getPhone()))
                .and(EmployeeSpecification.hasDepartmentId(employeeSearchRequest.getDepartmentId()))
                .and(EmployeeSpecification.hasSalaryInRange(employeeSearchRequest.getSalaryRange()));

        return employeeRepository.findAll(specification, pageable);
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