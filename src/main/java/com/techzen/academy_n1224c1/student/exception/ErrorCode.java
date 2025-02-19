package com.techzen.academy_n1224c1.student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import lombok.AccessLevel;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    STUDENT_NOT_EXISTS(40401, "Student not exists", HttpStatus.NOT_FOUND),
    TEACHER_NOT_EXISTS(40402, "Teacher not exists", HttpStatus.NOT_FOUND);

    final int code;
    final String message;
    final HttpStatus status;
}
