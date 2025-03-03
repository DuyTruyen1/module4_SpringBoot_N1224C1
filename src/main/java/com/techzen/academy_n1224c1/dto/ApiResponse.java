package com.techzen.academy_n1224c1.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//generic , bên ngoài tr vào cais gì thì dùng cái đó
public class ApiResponse<T> {
    Integer code;
    String message;
    T data;
}
