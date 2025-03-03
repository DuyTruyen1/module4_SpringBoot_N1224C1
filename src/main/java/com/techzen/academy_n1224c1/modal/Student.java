package com.techzen.academy_n1224c1.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // mặc điịnh các thuộc tính là private
@JsonInclude(JsonInclude.Include.NON_NULL) /// loại bỏ các phần tử null và k hiển thị lên
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double score;

    @ManyToOne
    Clazz clazz;
}
