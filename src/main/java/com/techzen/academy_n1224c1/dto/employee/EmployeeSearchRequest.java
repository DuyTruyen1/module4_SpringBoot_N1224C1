package com.techzen.academy_n1224c1.dto.employee;

import com.techzen.academy_n1224c1.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {
    String name;

    LocalDate dobFrom;

    LocalDate dobTo;

    Gender gender;
    String salaryRange;
    String phone;
    Integer departmentId;
}
