package com.accademyofl.loader.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.accademyofl.loader.entities.Student;
import com.accademyofl.loader.repository.StudentRepository;

@Configuration
@EnableBatchProcessing

public class SpringBatchConfig {
	
	/*@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;*/
	
	@Autowired
	private StudentRepository studentRepository;

	
	@Bean
	public FlatFileItemReader<Student> reader (){
		FlatFileItemReader<Student> itemReader=new FlatFileItemReader<Student>();
		itemReader.setResource(new FileSystemResource("src/main/resources/students.csv"));
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		
		return itemReader;
		
	}

	private LineMapper<Student> lineMapper() {
		// TODO Auto-generated method stub
		DefaultLineMapper<Student> lineMapper=new DefaultLineMapper<Student>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("firstname","lastname","DOB","annee_bacc");
		BeanWrapperFieldSetMapper<Student> fieldSetMapper=new BeanWrapperFieldSetMapper<Student>();
		fieldSetMapper.setTargetType(Student.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	
	
	@Bean
	public StudentsProcessor processor() {
		return new StudentsProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<Student> writer(){
		RepositoryItemWriter<Student> itemWriter=new RepositoryItemWriter<Student>();
		itemWriter.setRepository(studentRepository);
		itemWriter.setMethodName("save");
		
		return itemWriter;
	}
	
	
	/*@Bean
	public Step step1() {
		return  stepBuilderFactory.get("CSV-step").<Student,Student>chunk(10, null)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.taskExecutor(taskExecutor())
				.build();
				
	}*/
	
	
	@Bean
	public Step step1(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
	    return new StepBuilder("csv-step",   jobRepository)
	        .<Student, Student>chunk(10, transactionManager)
	        .reader(reader())
	        .processor(processor())
	        .writer(writer())
	        .taskExecutor(taskExecutor())
	        .build();
	    }
	
	
	/*@Bean
	public Job runJob() {
		return jobBuilderFactory.get("studentProcessingJob").flow(step1()).end().build();
	}*/
	
	@Bean
	public Job runJob(JobRepository jobRepository, Step step) {
	    return new JobBuilder("studentProcessingJob", jobRepository)
	    		.flow(step)
	       .end()
	        .build();
	}
	
	
	
	//to  execute in multithreading mode
	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
