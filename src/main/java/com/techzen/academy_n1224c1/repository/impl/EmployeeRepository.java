package com.techzen.academy_n1224c1.repository.impl;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.modal.Employee;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import com.techzen.academy_n1224c1.enums.Gender;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository implements IEmployeeRepository {
    @Override
    public List<Employee> search(EmployeeSearchRequest request) {
        Session session = ConnectionUtil.sessionFactory.openSession();

        // Sử dụng HQL
        String hql = "FROM Employee e LEFT JOIN FETCH e.department WHERE "
                + "(:name IS NULL OR lower(e.name) LIKE CONCAT('%', :name, '%')) "
                + "AND (:dobFrom IS NULL OR e.dateOfBirth >= :dobFrom) "
                + "AND (:dobTo IS NULL OR e.dateOfBirth <= :dobTo) "
                + "AND (:gender IS NULL OR e.gender = :gender) "
                + "AND (:phone IS NULL OR e.phone LIKE CONCAT('%', :phone, '%')) "
                + "AND (:departmentId IS NULL OR e.department.id = :departmentId) ";

        // Xử lý salary range
        if (request.getSalaryRange() != null) {
            hql += "AND ("; // Mở đầu điều kiện salary range
            switch (request.getSalaryRange()) {
                case "lt5":
                    hql += "e.salary < 5000000 ";
                    break;
                case "5-10":
                    hql += "e.salary >= 5000000 AND e.salary < 10000000 ";
                    break;
                case "10-20":
                    hql += "e.salary >= 10000000 AND e.salary < 20000000 ";
                    break;
                case "gt20":
                    hql += "e.salary > 20000000 ";
                    break;
            }
            hql += ")";
        }

        Query<Employee> query = session.createQuery(hql, Employee.class);

        // Đặt giá trị tham số
        query.setParameter("name", request.getName());
        query.setParameter("dobFrom", request.getDobFrom());
        query.setParameter("dobTo", request.getDobTo());
        query.setParameter("gender", request.getGender());
        query.setParameter("phone", request.getPhone());
        query.setParameter("departmentId", request.getDepartmentId());

        return query.getResultList();
    }


    @Override
    public Optional<Employee> findById(UUID id) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Employee employee = session.get(Employee.class, id);
            return Optional.ofNullable(employee);
        }
    }

    public Employee save(Employee employee) {
        Transaction transaction = null;
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            if (employee.getId() == null) {
                employee.setId(UUID.randomUUID()); // Tạo UUID nếu chưa có
                session.save(employee);
            } else {
                session.update(employee);
            }

            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void deleteById(UUID id) {
        Transaction transaction = null;
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
