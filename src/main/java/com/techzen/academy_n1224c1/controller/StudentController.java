package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.service.IStudentService;
import com.techzen.academy_n1224c1.modal.Student;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import com.techzen.academy_n1224c1.exception.ApiException;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // chỉ những cái nào final mới tiêm , còn không thì k tiêm
@RequestMapping("/students")
@Builder
@Scope("singleton") // có 5 scope
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    // có 3 thuộc tính teem : qua autowried , constructor , setter
    // nên tiêm theo constructor vì nó mặc định là vậy
    // cách 1 cách 3 thì nhiều thuộc tính thì sẽ gây lặp lại vì tiêm nhiều lần
    @Autowired // tiêm sự phụ thuộc
            IStudentService studentService;
//  IStudentService studentService = new StudentService();

    @GetMapping
    public ResponseEntity<?> getStudents(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(ApiResponse.<List<Student>>builder()
                .data(studentService.findByName(name))
                .build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new ApiException(ErrorCode.STUDENT_NOT_EXISTS);
        }
        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .data(student)
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student students) {
        Student student = studentService.save(students);
//        students.setId((int) (Math.random() * 100));
//        this.students.add(students);

        //return ResponseEntity.status(201).body(students); HƠI THÔ NÊN SÀI CACHS DƯỚI
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Student>builder().data(studentService.save(students)).build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> update(@PathVariable("id") int id, @RequestBody Student student) {
        student.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<Student>builder()
                        .data(studentService.save(student)).build());
        //return ResponseEntity.notFound().build();
    }
}

