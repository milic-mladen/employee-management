package com.mds.EmployeeManagement.service;

import com.mds.EmployeeManagement.exception.DuplicateResourceException;
import com.mds.EmployeeManagement.exception.RequestValidationException;
import com.mds.EmployeeManagement.exception.ResourceNotFoundException;
import com.mds.EmployeeManagement.model.Employee;
import com.mds.EmployeeManagement.model.EmployeeCreationRequest;
import com.mds.EmployeeManagement.model.EmployeeUpdateRequest;
import com.mds.EmployeeManagement.repository.EmployeeDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeDao.getEmployeeById(id);
        return unwrapEmployee(employee, id);
    }

    public void saveEmployee(EmployeeCreationRequest employeeCreationRequest) {
        String email = employeeCreationRequest.email();
        if (employeeDao.existsEmployeeByEmail(email)) {
            throw new DuplicateResourceException("Email already exists");
        }
        Employee employee = new Employee(employeeCreationRequest.lastName(),
                employeeCreationRequest.firstName(),
                employeeCreationRequest.username(),
                employeeCreationRequest.email(),
                employeeCreationRequest.salary());
        employeeDao.saveEmployee(employee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeDao.existsEmployeeById(id)) {
            throw new ResourceNotFoundException("Employee with id: %s does not exist".formatted(id));
        }
        employeeDao.deleteEmployee(id);
    }

    public void updateEmployee(Long id, EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employee = getEmployeeById(id);
        boolean changes = false;
        if (employeeUpdateRequest.firstName() != null && !employeeUpdateRequest.firstName().equals(employee.getFirstName())) {
            employee.setFirstName(employeeUpdateRequest.firstName());
            changes = true;
        }
        if (employeeUpdateRequest.lastName() != null && !employeeUpdateRequest.lastName().equals(employee.getLastName())) {
            employee.setLastName(employeeUpdateRequest.lastName());
            changes = true;
        }
        if (employeeUpdateRequest.username() != null && !employeeUpdateRequest.username().equals(employee.getUsername())) {
            employee.setUsername(employeeUpdateRequest.username());
            changes = true;
        }
        if (employeeUpdateRequest.email() != null && !employeeUpdateRequest.email().equals(employee.getEmail())) {
            employee.setEmail(employeeUpdateRequest.email());
            changes = true;
        }
        if (employeeUpdateRequest.salary() > 0 && employeeUpdateRequest.salary() != employee.getSalary()) {
            employee.setSalary(employeeUpdateRequest.salary());
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException("No data changes found");
        }
        employeeDao.updateEmployee(employee);
    }

    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

    public double getThirdMaxSalary() {
        return employeeDao.getThirdMaxSalary();
    }

    public double getAverageSalary() {
        return employeeDao.getAverageSalary();
    }

    public List<Employee> getEmployeesStartingWithA() {
        return employeeDao.getEmployeesStartingWithA();
    }

    static Employee unwrapEmployee(Optional<Employee> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new ResourceNotFoundException("The employee id '" + id + "' does not exist in records");
    }

}
