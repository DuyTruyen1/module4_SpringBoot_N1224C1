package com.techzen.academy_n1224c1.repository;

import com.techzen.academy_n1224c1.dto.employee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.modal.Department;
import com.techzen.academy_n1224c1.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {

    @Query(value = """
        SELECT d.* FROM department d
        WHERE (:name IS NULL OR d.name LIKE CONCAT('%', :name, '%'))
        """, nativeQuery = true)
    List<Department> findByAttributes(@Param("name") String name);
}

