package com.mds.EmployeeManagement.controller;

import com.mds.EmployeeManagement.model.Employee;
import com.mds.EmployeeManagement.model.EmployeeCreationRequest;
import com.mds.EmployeeManagement.model.EmployeeUpdateRequest;
import com.mds.EmployeeManagement.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createEmployee(@RequestBody EmployeeCreationRequest employeeCreationRequest) {
        employeeService.saveEmployee(employeeCreationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        employeeService.updateEmployee(id, employeeUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/salaries/third-highest")
    public ResponseEntity<Double> getThirdMaxSalary() {
        double salary = employeeService.getThirdMaxSalary();
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    @GetMapping("/salaries/average")
    public ResponseEntity<Double> getAverageSalary() {
        double averageSalary = employeeService.getAverageSalary();
        return new ResponseEntity<>(averageSalary, HttpStatus.OK);
    }

    @GetMapping("/name-starts-with-A")
    public ResponseEntity<List<Employee>> getEmployeesThatStartWithA() {
        List<Employee> employeesThatStartWithA = employeeService.getEmployeesStartingWithA();
        return new ResponseEntity<>(employeesThatStartWithA, HttpStatus.OK);
    }

}
