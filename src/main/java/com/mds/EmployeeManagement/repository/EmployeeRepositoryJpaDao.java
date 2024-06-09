package com.mds.EmployeeManagement.repository;

import com.mds.EmployeeManagement.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class EmployeeRepositoryJpaDao implements EmployeeDao {

    private final EmployeeRepository employeeRepository;


    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);

    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public double getThirdMaxSalary() {
        return employeeRepository.getThirdMaxSalary();
    }

    @Override
    public double getAverageSalary() {
        return employeeRepository.getAverageSalary();
    }

    @Override
    public List<Employee> getEmployeesStartingWithA() {
        return employeeRepository.getEmployeesStartingWithA();
    }

    @Override
    public boolean existsEmployeeByEmail(String email) {
        return employeeRepository.existsEmployeeByEmail(email);
    }

    @Override
    public boolean existsEmployeeById(Long id) {
        return employeeRepository.existsEmployeeById(id);
    }

    @Override
    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

}
