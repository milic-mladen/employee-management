package com.mds.EmployeeManagement.helper;

import com.mds.EmployeeManagement.model.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    @Value("${excel.file.type}")
    private String excelFileType;

    @Value("${excel.file.headers}")
    private String[] excelFileHeaders;

    @Value("${excel.file.sheet}")
    private String excelFileSheet;

    public boolean hasExcelFormat(MultipartFile file) {

        if (!excelFileType.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public List<Employee> excelToEmployees(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(excelFileSheet);
            Iterator<Row> rows = sheet.iterator();

            List<Employee> employees = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Employee employee = new Employee();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIndex) {

                        case 0:
                            employee.setFirstName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            employee.setLastName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            employee.setUsername(currentCell.getStringCellValue());
                            break;
                        case 3:
                            employee.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            employee.setSalary(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIndex++;
                }

                employees.add(employee);
            }

            workbook.close();

            return employees;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }
}
