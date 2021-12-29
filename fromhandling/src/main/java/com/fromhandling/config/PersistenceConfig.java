package com.fromhandling.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource({"classpath:db.properties"})
@EnableJpaRepositories(basePackages = "com.fromhandling.respositories")
public class PersistenceConfig {
	@Autowired
	private Environment env;
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = null;
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driverClassname"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = null;
		HibernateJpaVendorAdapter jpaVendorAdapter = null;
		
		localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		
		jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);
		
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.fromhandling.entities");
		return localContainerEntityManagerFactoryBean;
	}
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = null;
		transactionManager = new JpaTransactionManager(emf);
		return transactionManager;
	}
}
