package com.techzen.academy_n1224c1.employee;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    private UUID id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private double salary;
    private String phone;
    private Integer departmentId;
}
