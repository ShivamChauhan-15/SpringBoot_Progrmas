package com.example.BatchPocessingFileUpload.config;

import com.example.BatchPocessingFileUpload.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {

        @Override
        public Customer process(Customer customer) throws Exception {
            if(customer.getCountry().equals("China")) {
                return customer;
            }else{
                return null;
            }
        }
}