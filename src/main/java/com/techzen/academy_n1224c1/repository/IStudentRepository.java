package com.techzen.academy_n1224c1.repository;

import com.techzen.academy_n1224c1.modal.Student;

import java.util.List;

public interface IStudentRepository  {
    List<Student> findByName(String name);

    Student findById(int id);

    Student save(Student student);
}
