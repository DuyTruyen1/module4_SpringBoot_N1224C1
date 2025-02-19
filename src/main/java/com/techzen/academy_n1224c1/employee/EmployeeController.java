package com.techzen.academy_n1224c1.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee(UUID.randomUUID(), "Phạm Duy Truyền", LocalDate.of(2003, 8, 2), Gender.MALE, 15000000, "0123456789"), new Employee(UUID.randomUUID(), "Nguyễn Thị Lan", LocalDate.of(1995, 5, 10), Gender.FEMALE, 18000000, "0987654321"), new Employee(UUID.randomUUID(), "Trần Văn Khánh", LocalDate.of(1990, 12, 25), Gender.MALE, 20000000, "0345678912"), new Employee(UUID.randomUUID(), "Lê Hoàng Minh", LocalDate.of(1988, 7, 8), Gender.MALE, 25000000, "0367891234"), new Employee(UUID.randomUUID(), "Hoàng Thị Mai", LocalDate.of(1997, 3, 15), Gender.FEMALE, 17000000, "0398765432")));

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") UUID id) {
        Optional<Employee> employee = employees.stream() // dùng optional để tránh lỗi NullPointerException
                .filter(e -> e.getId().equals(id)).findFirst();
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return (ResponseEntity<Employee>) employees.stream().filter(e -> e.getId().equals(id)).findFirst().map(e -> {
            e.setName(employee.getName());
            e.setDateOfBirth(employee.getDateOfBirth());
            e.setGender(employee.getGender());
            e.setSalary(employee.getSalary());
            e.setPhone(employee.getPhone());
            return ResponseEntity.ok(e);
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") UUID id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst().map(s -> {
            employees.remove(s);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
