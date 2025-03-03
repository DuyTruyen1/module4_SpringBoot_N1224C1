package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.modal.Department;
import com.techzen.academy_n1224c1.util.JsonResponse;
import com.techzen.academy_n1224c1.exception.ApiException;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final List<Department> departmentList = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Management"),
                    new Department(2, "Marketing"),
                    new Department(3, "Account")
            )
    );

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        return JsonResponse.ok(departmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") int id) {
        return departmentList.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_EXISTS));
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        department.setId((int) (Math.random() * 1000));
        departmentList.add(department);
        return JsonResponse.ok(departmentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Department department) {
        return departmentList.stream()
                .filter(d -> d.getId() == id) // Lọc  theo ID
                .findFirst() // Lấy phần tử đầu tiên tìm thấy
                .map(d -> {
                    d.setName(department.getName());
                    return JsonResponse.ok(d);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTS)); // Nếu không tìm thấy, ném ngoại lệ
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return departmentList.stream()
                .filter(d -> d.getId() == id) // Lọc  theo ID
                .findFirst() // Lấy phần tử đầu tiên tìm thấy
                .map(d -> {
                    departmentList.remove(d); // Xóa
                    return JsonResponse.noContent(); // Trả về phản hồi
                })
                .orElseThrow(() -> new ApiException(ErrorCode.DEPARTMENT_NOT_EXISTS)); // Nếu không tìm thấy, ném ngoại lệ
    }
}
