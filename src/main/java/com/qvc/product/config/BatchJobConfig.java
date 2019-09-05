package com.qvc.product.config;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qvc.product.product.ProductWriter;
import com.qvc.product.product.Product;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {
	@Autowired private JobBuilderFactory jobs;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	@Autowired private SqlSessionFactory sqlSessionFactory;
	
	@Bean
	public Job addProductLoaderJob(){
		return jobs.get("addProductLoaderJob")
				.start(step())
				.build();
	}
	
	@Bean
	public Step step(){
		return stepBuilderFactory.get("step")
				.<Product, Product>chunk(1024) 
				.reader(productReader())
				.writer(productWriter())
				.faultTolerant()
				.build();
	}
	
	@Bean
    ItemReader<Product> productReader() {
		MyBatisPagingItemReader<Product> itemReader = new MyBatisPagingItemReader<>();
		itemReader.setPageSize(1024);
		itemReader.setName("productReader");
		itemReader.setSqlSessionFactory(sqlSessionFactory);
		itemReader.setParameterValues(new HashMap<>());
		itemReader.setQueryId("com.qvc.product.product.ProductMapper.findProduct");
		return itemReader;
	}
	
	@Bean
    ItemWriter<Product> productWriter() {
		return new ProductWriter();
	}
  
    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactory(ResourcelessTransactionManager txManager)
            throws Exception {
        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(txManager);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public JobRepository jobRepository(MapJobRepositoryFactoryBean factory) throws Exception {
        return factory.getObject();
    }

    @Bean
    public JobExplorer jobExplorer(MapJobRepositoryFactoryBean factory) {
        return new SimpleJobExplorer(factory.getJobInstanceDao(), factory.getJobExecutionDao(),
                factory.getStepExecutionDao(), factory.getExecutionContextDao());
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }
    
}