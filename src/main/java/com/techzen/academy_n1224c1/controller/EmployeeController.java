package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.exception.ApiException;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.modal.Employee;
import com.techzen.academy_n1224c1.enums.Gender;
import com.techzen.academy_n1224c1.service.IEmployeeService;
import com.techzen.academy_n1224c1.util.JsonResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/employees")
public class EmployeeController {
    IEmployeeService employeeService;



    @GetMapping
    public ResponseEntity<?> getAll(EmployeeSearchRequest employeeSearchRequest) {
        return JsonResponse.ok(employeeService.search(employeeSearchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") UUID id) {
        return employeeService.findById(id)
                .map(JsonResponse::ok)
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_EXISTS));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        return JsonResponse.created(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        employeeService.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_EXISTS));

        employee.setId(id);
        return JsonResponse.ok(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        employeeService.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_EXISTS));

        employeeService.deleteById(id);
        return JsonResponse.noContent();
    }

}
