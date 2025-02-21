package com.techzen.academy_n1224c1.employee;

import com.techzen.academy_n1224c1.employee.exception.ApiException;
import com.techzen.academy_n1224c1.employee.exception.ErrorCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final List<Employee> employees = new ArrayList<>
            (Arrays.asList(new Employee(UUID.randomUUID(), "Phạm Duy Truyền", LocalDate.of(2003, 8, 2), Gender.MALE, 15000000, "0123456789",1),
                    new Employee(UUID.randomUUID(), "Nguyễn Thị Lan", LocalDate.of(1995, 5, 10), Gender.FEMALE, 18000000, "0987654321",2),
                    new Employee(UUID.randomUUID(), "Trần Văn Khánh", LocalDate.of(1990, 12, 25), Gender.MALE, 20000000, "0345678912",1),
                    new Employee(UUID.randomUUID(), "Lê Hoàng Minh", LocalDate.of(1988, 7, 8), Gender.MALE, 25000000, "0367891234",3),
                    new Employee(UUID.randomUUID(), "Hoàng Thị Mai", LocalDate.of(1997, 3, 15), Gender.FEMALE, 17000000, "0398765432",2)));

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dobFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(value = "dobTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "salaryRange", required = false) String salaryRange,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "departmentId", required = false) Integer departmentId) {

        List<Employee> filteredEmployees = employees.stream()
                // Lọc theo tên
                .filter(e -> name == null || e.getName().toLowerCase().contains(name.toLowerCase()))
                // Lọc theo ngày sinh
                .filter(e -> dobFrom == null || e.getDateOfBirth().isBefore(dobFrom))
                .filter(e -> dobTo == null || e.getDateOfBirth().isAfter(dobTo))
                // Lọc theo giới tính
                .filter(e -> gender == null || e.getGender().equals(gender))
                // Lọc theo số điện thoại
                .filter(e -> phone == null || e.getPhone().contains(phone))
                // Lọc theo phòng ban
                .filter(e -> departmentId == null || Objects.equals(e.getDepartmentId(), departmentId))
                // Lọc theo mức lương
                .filter(e -> {
                    if (salaryRange == null) {
                        return true; // Không có filter mức lương
                    }
                    return switch (salaryRange) {
                        case "5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() < 10000000;
                        case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "gt20" -> e.getSalary() > 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());

        return JsonResponse.ok(filteredEmployees);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") UUID id) {
        return employees.stream() // dùng optional để tránh lỗi NullPointerException
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_EXISTS));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return JsonResponse.created(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setDateOfBirth(employee.getDateOfBirth());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    return JsonResponse.ok(e);
                }).orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_EXISTS));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId()
                        .equals(id)).findFirst()
                .map(s -> {
            employees.remove(s);
            return JsonResponse.noContent();
        }).orElseThrow(() -> new ApiException(ErrorCode.EMPLOYEE_NOT_EXISTS));
    }
}
