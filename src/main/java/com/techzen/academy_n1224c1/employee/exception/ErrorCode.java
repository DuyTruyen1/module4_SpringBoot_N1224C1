package com.techzen.academy_n1224c1.employee.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    EMPLOYEE_NOT_EXISTS(40401, "Employee not exists", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXISTS(40402, "Department not exists", HttpStatus.NOT_FOUND);

    final int code;
    final String message;
    final HttpStatus status;
}
