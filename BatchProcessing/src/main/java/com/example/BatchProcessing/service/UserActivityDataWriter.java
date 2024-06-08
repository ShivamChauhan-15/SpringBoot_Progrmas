package com.example.BatchProcessing.service;

import com.example.BatchProcessing.download.ExcelDownload;
import com.example.BatchProcessing.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@StepScope
//@Component
public class UserActivityDataWriter implements ItemWriter<Customer> {
    private ExcelDownload builder;
    private File file;
    private List headers;

    private static final String FILE_PATH = "/tmp/user_activity.xlsx";

    public UserActivityDataWriter() {
        this.file = new File(FILE_PATH);
        Class<?> userActivityCRMClass = Customer.class;
        Field[] fields = userActivityCRMClass.getDeclaredFields();
        this.headers = new ArrayList<>();
        for (Field field : fields) {
            headers.add(field.getName()); // Add field names to headers
        }
    }

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        log.info("In write method----------------");
        List<List> customerList= new ArrayList<>();
        for(Customer c:items){
            customerList.add(new ArrayList(){{
                c.getFirstName();
                c.getLastName();
                c.getEmail();
                c.getGender();
                c.getContactNo();
                c.getCountry();
                c.getDob();
            }});
        }
        log.info("File exist {}",file.exists());
        if (file.exists()) {
            log.info("file is already exists");
            builder = ExcelDownload.builder(file);

        }
        else {
            builder=ExcelDownload.builder();
            builder.createWorkSheet("Customer_Data",headers.size())
                    .header(headers);
        }

        builder.rows(customerList)
                .save(file);

    }

}









