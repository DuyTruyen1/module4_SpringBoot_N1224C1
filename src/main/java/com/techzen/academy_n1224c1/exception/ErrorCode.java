package com.techzen.academy_n1224c1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import lombok.AccessLevel;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    GROUND_NOT_EXISTS(40401, "Ground not exists", HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_EXISTS(40402, "Employee not exists", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXISTS(40403, "Deparment not exists", HttpStatus.NOT_FOUND),
    STUDENT_NOT_EXISTS(40404, "Student not exists", HttpStatus.NOT_FOUND);


    final int code;
    final String message;
    final HttpStatus status;
}
