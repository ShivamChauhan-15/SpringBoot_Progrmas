package com.example.BatchProcessing.config;


import com.example.BatchProcessing.entity.Customer;
import com.example.BatchProcessing.service.UserActivityCSVWriter;
import com.example.BatchProcessing.service.UserActivityDataReader;
import com.example.BatchProcessing.service.UserActivityDataWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
@Slf4j
public class CSVBatchConfig{
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private UserActivityDataReader userActivityExcelReader;
    private UserActivityCSVWriter userActivityCSVWriter;


    @Bean("userActivityExcelJob")
    public Job userActivityJob() throws Exception {
        return jobBuilderFactory.get("userActivityExcelJob")
                .incrementer(new RunIdIncrementer())
                .start(userActivityStep())
                .build();
    }

    @Bean
    public Step userActivityStep() throws Exception {
        log.info("inside userAcivityStep");
        return stepBuilderFactory.get("userActivityStep")
                .<Customer, Customer>chunk(20)
                .reader(userActivityExcelReader)
                .processor(processor())
                .writer(userActivityCSVWriter)
                .build();
    }

    @Bean
    public ItemProcessor<Customer,Customer>processor(){
        return user->user;
    }


}

