package com.example.demo.configuration;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "projectEntityManagerFactory",
		transactionManagerRef = "projectTransactionManager",
		basePackages = {"com.example.demo.db2.repository" }
		)
public class ProjectDBConfig {
	
	@Bean(name = "projectDataSource")
	@ConfigurationProperties(prefix = "spring.db2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "projectEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean projectEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("projectDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
		return builder
				.dataSource(dataSource)
				.properties(properties)
				.packages("com.example.demo.db2.model")
				//.persistenceUnit("Project")
				.build();
	}
	
	@Bean(name = "projectTransactionManager")
	public PlatformTransactionManager projectTransactionManager(
			@Qualifier("projectEntityManagerFactory") EntityManagerFactory projectEntityManagerFactory) {
		return new JpaTransactionManager(projectEntityManagerFactory);
	}

}
