package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.modal.Department;
import com.techzen.academy_n1224c1.service.impl.DepartmentService;
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
     DepartmentService departmentService;
    @GetMapping("/search")
    public ResponseEntity<List<Department>> searchDepartments(
            @RequestParam(required = false) String name
    ) {
        List<Department> departments = departmentService.searchDepartments(name);
        return ResponseEntity.ok(departments);
    }
}
