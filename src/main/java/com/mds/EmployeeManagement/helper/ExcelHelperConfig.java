package com.mds.EmployeeManagement.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelHelperConfig {

    @Bean
    public ExcelHelper excelHelper() {
        return new ExcelHelper();
    }

}
