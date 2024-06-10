package com.mds.EmployeeManagement.service;

import com.mds.EmployeeManagement.helper.ExcelHelper;
import com.mds.EmployeeManagement.model.Employee;
import com.mds.EmployeeManagement.repository.EmployeeDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class ExcelService {

    private final EmployeeDao employeeDao;
    private final ExcelHelper excelHelper;

    public void writeToDatabase(MultipartFile file) {
        try {
            List<Employee> employees = excelHelper.excelToEmployees(file.getInputStream());
            employeeDao.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("Fail to store excel data: " + e.getMessage());
        }
    }

}
