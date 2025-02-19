package com.techzen.academy_n1224c1.student;

import com.techzen.academy_n1224c1.student.dto.ApiResponse;
import com.techzen.academy_n1224c1.student.exception.ApiException;
import com.techzen.academy_n1224c1.student.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Truyền", 5.5),
                    new Student(2, "Huy", 9.5)
            )
    );

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getStudents(@RequestParam(defaultValue = "") String name) {
        List<Student> studentSearch = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().contains(name)) {
                studentSearch.add(student);
            }
        }
        return ResponseEntity.ok(ApiResponse.<List<Student>>builder()
                .data(studentSearch)
                .build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return ResponseEntity.ok(ApiResponse.<Student>builder()
                        .data(student)
                        .build());
            }
        }

        return ResponseEntity.status(ErrorCode.STUDENT_NOT_EXISTS.getStatus())
                .body(ApiResponse.<Student>builder()
                        .code(ErrorCode.STUDENT_NOT_EXISTS.getCode())  // Không còn null
                        .message(ErrorCode.STUDENT_NOT_EXISTS.getMessage()) // Không còn null
                        .build());
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student student) {
        student.setId((int) (Math.random() * 100000));
        students.add(student);
        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .data(student)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> update(@PathVariable("id") int id, @RequestBody Student student) {
        for (Student stud : students) {
            if (stud.getId() == id) {
                stud.setName(student.getName());
                stud.setScore(student.getScore());
                return ResponseEntity.ok(ApiResponse.<Student>builder()
                        .data(stud)
                        .build());
            }
        }
        throw new ApiException(ErrorCode.STUDENT_NOT_EXISTS);

//        return ResponseEntity.status(ErrorCode.STUDENT_NOT_EXISTS.getStatus()).
//                body(ApiResponse.<Student>builder()
//                        .code(ErrorCode.STUDENT_NOT_EXISTS.getCode())
//                        .message(ErrorCode.STUDENT_NOT_EXISTS.getMessage())
//                        .build());
    }
}