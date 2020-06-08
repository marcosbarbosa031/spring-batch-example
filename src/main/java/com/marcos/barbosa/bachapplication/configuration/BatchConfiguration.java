package com.marcos.barbosa.bachapplication.configuration;

import javax.sql.DataSource;

import com.marcos.barbosa.bachapplication.model.Customer;
import com.marcos.barbosa.bachapplication.processor.PersonItemProcessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  
  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Bean
  public FlatFileItemReader<Customer> reader() {
    return new FlatFileItemReaderBuilder<Customer>()
      .name("personItemReader")
      .resource(new ClassPathResource("example-data.csv"))
      .delimited()
      .names(new String[]{"firstName", "lastName", "balance"})
      .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
        setTargetType(Customer.class);
      }})
      .build();
  }

  @Bean
  public PersonItemProcessor processor() {
    return new PersonItemProcessor();
  }

  @Bean
  public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Customer>()
      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
      .sql("INSERT INTO PEOPLE (first_name, last_name, balance) VALUES (:firstName, :lastName, :balance)")
      .dataSource(dataSource)
      .build();
  }

  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory.get("importUserJob")
      .incrementer(new RunIdIncrementer())
      .listener(listener)
      .flow(step1)
      .end()
      .build();
  }

  @Bean
  public Step step1(JdbcBatchItemWriter<Customer> writer) {
    return stepBuilderFactory.get("step1")
    .<Customer, Customer> chunk(10)
    .reader(reader())
    .processor(processor())
    .writer(writer)
    .build();
  }
}
