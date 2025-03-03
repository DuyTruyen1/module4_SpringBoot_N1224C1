package com.techzen.academy_n1224c1.service;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.modal.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService {
    // Tìm kiếm theo thuộc tính từ request
    List<Employee> search(EmployeeSearchRequest employeeSearchRequest);

    // Tìm theo ID
    Optional<Employee> findById(UUID id);

    // Lưu hoặc cập nhật Employee
    Employee save(Employee employee);


    // Xóa theo ID (cần nếu không có entity sẵn)
    void deleteById(UUID id);
}
