package com.techzen.academy_n1224c1.repository.impl;

import com.techzen.academy_n1224c1.modal.Student;
import com.techzen.academy_n1224c1.repository.IStudentRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.techzen.academy_n1224c1.repository.impl.BaseRepository.getConnection;

@Repository
@Getter
@Setter
// hạt đậu là bean , nhiềm vụ là team nó vào
public class StudentRepository implements IStudentRepository {


    public List<Student> findByName(String name) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        List<Student> students = null;

        try {
            students = session.createQuery("FROM Student WHERE name = :name")
                    .setParameter("name",name)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return students;
    }

    public Student findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Student student = null;
        try {
            student = (Student) session.createQuery("FROM Student WHERE id = :id")
                    .setParameter("id",id)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return student;
    }


    public Student save(Student student) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.saveOrUpdate(student);
                transaction.commit();
            }catch (Exception e) {
                if(transaction != null) {
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return student;
    }
}