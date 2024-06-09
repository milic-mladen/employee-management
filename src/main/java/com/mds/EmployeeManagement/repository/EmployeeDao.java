package com.mds.EmployeeManagement.repository;

import com.mds.EmployeeManagement.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    void saveEmployee(Employee employee);

    Optional<Employee> getEmployeeById(Long id);

    void updateEmployee(Employee employee);

    void deleteEmployee(Long id);

    List<Employee> getEmployees();

    double getThirdMaxSalary();

    double getAverageSalary();

    List<Employee> getEmployeesStartingWithA();

    void saveAll(List<Employee> employees);

    boolean existsEmployeeByEmail(String email);

    boolean existsEmployeeById(Long id);
}
