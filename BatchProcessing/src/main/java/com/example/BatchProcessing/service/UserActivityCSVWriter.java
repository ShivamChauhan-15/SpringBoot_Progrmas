package com.example.BatchProcessing.service;


import com.example.BatchProcessing.entity.Customer;
import com.example.BatchProcessing.helper.CSVHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@StepScope
@Component
public class UserActivityCSVWriter implements ItemStreamWriter<Customer> {

    private String fileName;
    private String filePath;
    private File file;
    private FlatFileItemWriter<Customer> flatFileItemWriter;

    public UserActivityCSVWriter() {
        this.fileName = "user_activity";
        this.filePath = "/tmp/" + fileName + ".csv";
        this.file = new File(filePath);
        flatFileItemWriter = createFlatFileItemWriter();
    }

    private FlatFileItemWriter<Customer> createFlatFileItemWriter() {
        FlatFileItemWriter<Customer> fileItemWriter= new FlatFileItemWriter<>();
        fileItemWriter.setResource(new FileSystemResource(filePath));
        fileItemWriter.setHeaderCallback(writer -> writer.write(CSVHelper.HEADERS));
//        fileItemWriter.setAppendAllowed(true);
        fileItemWriter.setLineAggregator(userActivityCRMLineAggregator());
        return fileItemWriter;
    }

    public LineAggregator<Customer> userActivityCRMLineAggregator() {
        DelimitedLineAggregator<Customer> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(new BeanWrapperFieldExtractor<Customer>() {{
            setNames(CSVHelper.FIELDTOEXTRACT);
        }});
        return lineAggregator;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        flatFileItemWriter.open(executionContext);

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
//        flatFileItemWriter.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        flatFileItemWriter.close();
    }

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        log.info("start writing-----");
        log.info("Writing chunk of size: {}", items.size());
        flatFileItemWriter.write(items);
    }


}
