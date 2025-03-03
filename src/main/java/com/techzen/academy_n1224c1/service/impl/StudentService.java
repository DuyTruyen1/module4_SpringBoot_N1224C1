package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.repository.IStudentRepository;
import com.techzen.academy_n1224c1.modal.Student;
import com.techzen.academy_n1224c1.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService implements IStudentService {
    @Autowired // dùng để tiêm đối tượng repository từ StudentRepo vào , thay thế cho từ khoá new
    private final IStudentRepository studentRepository ;

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {

        return studentRepository.save(student);
    }
}
