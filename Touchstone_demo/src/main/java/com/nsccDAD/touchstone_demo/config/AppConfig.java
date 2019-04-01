package com.nsccDAD.touchstone_demo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.nsccDAD.touchstone_demo")
@PropertySource({ "classpath:persistence-mysql.properties" })
public class AppConfig implements WebMvcConfigurer {
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean
	public DataSource tsDataSource() {
		// create connection pool
		ComboPooledDataSource tsDataSource = new ComboPooledDataSource();
		// set the JDBC driver
		try {
			tsDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		}catch(PropertyVetoException ex) {
			throw new RuntimeException(ex);
		}
		
		// Log URL and user
		logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
		
		// set database connection properties
		tsDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		tsDataSource.setUser(env.getProperty("jdbc.user"));
		tsDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool properties
		tsDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		tsDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		tsDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
		tsDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		tsDataSource.setTestConnectionOnCheckout(getBoolProperty("connection.pool.testConnectionCheckOut"));
		tsDataSource.setPreferredTestQuery("SELECT 1");
		
		return tsDataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(tsDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		
		return txManager;
	}
	
	@Bean
	public ViewResolver ViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		return bean;
	}
	
	public void addResourceHandler(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	// need a helper method 
	// read environment property and convert to int
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
	private boolean getBoolProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to bool
		return propVal.equals("true");
	}
	private Properties getHibernateProperties() {

		// set hibernate properties
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;				
	}
}
