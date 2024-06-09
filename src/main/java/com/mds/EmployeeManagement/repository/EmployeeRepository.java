package com.mds.EmployeeManagement.repository;

import com.mds.EmployeeManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(
            value = "SELECT DISTINCT salary " +
                    "FROM employees " +
                    "ORDER BY salary DESC " +
                    "LIMIT 1 OFFSET 2;",
            nativeQuery = true
    )
    double getThirdMaxSalary();

    @Query(
            value = "SELECT AVG(salary) AS average_salary " +
                    "FROM employees;",
            nativeQuery = true
    )
    double getAverageSalary();

    @Query(
            value = "SELECT * " +
                    "FROM employees " +
                    "WHERE first_name LIKE 'A%';",
            nativeQuery = true
    )
    List<Employee> getEmployeesStartingWithA();

    boolean existsEmployeeByEmail(String email);
    boolean existsEmployeeById(Long id);
}
