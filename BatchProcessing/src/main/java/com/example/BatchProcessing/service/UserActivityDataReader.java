package com.example.BatchProcessing.service;



import com.example.BatchProcessing.entity.Customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;


@Slf4j
@Transactional(readOnly = true)
@StepScope
@Component
public class UserActivityDataReader extends JpaPagingItemReader<Customer> {
    String query="Select * from customers_info";
    public UserActivityDataReader(@Autowired EntityManagerFactory entityManagerFactory) throws Exception {

        log.info("inside UserActivityExcelReader constructor");
        this.setName("UserActivityExcelReader");
        JpaNativeQueryProvider<Customer> queryProvider = new JpaNativeQueryProvider<Customer>();
        queryProvider.setSqlQuery(query);
        queryProvider.setEntityClass(Customer.class);
        queryProvider.afterPropertiesSet();

        this.setEntityManagerFactory(entityManagerFactory);
        this.setPageSize(20);
        this.setQueryProvider(queryProvider);
        this.afterPropertiesSet();
        this.setSaveState(true);
        log.info("fetch data succesfully--------------");
    }
    @Override
    public Customer read() throws Exception {
        log.info("in userActivityCrm ------------");
        return super.doRead();
    }
}
