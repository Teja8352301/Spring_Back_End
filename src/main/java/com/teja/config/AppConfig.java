package com.teja.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.teja.interceptors.AuthInterceptor;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = "com.teja")
@PropertySource({ "classpath:mysql.properties" })
public class AppConfig implements WebMvcConfigurer{
	
	@Bean
	public ViewResolver viewResolver() {
	  InternalResourceViewResolver irv = new InternalResourceViewResolver();
	  irv.setPrefix("/WEB-INF/view/");
	  irv.setSuffix(".jsp");
	  return irv;
	 }
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// define a bean for ViewResolver

	@Bean
	public DataSource myDataSource() {
		
		// create connection pool
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();

		// set the jdbc driver
		try {
			myDataSource.setDriverClass("com.mysql.jdbc.Driver");		
		}
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// for sanity's sake, let's log url and user ... just to make sure we are reading the data
		logger.info("jdbc.url=" + "jdbc:mysql://localhost:3306/spring?useSSL=false");
		logger.info("jdbc.user=" + "root");
		
		// set database connection props
		myDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spring_latest?useSSL=false");
		myDataSource.setUser("root");
		myDataSource.setPassword("abcd123456");
		
		// set connection pool props
		myDataSource.setInitialPoolSize(5);
		myDataSource.setMinPoolSize(5);
		myDataSource.setMaxPoolSize(20);		
		myDataSource.setMaxIdleTime(3000);

		return myDataSource;
	}
	
	private Properties getHibernateProperties() {

	
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		
		return props;				
	}

	
	// need a helper method 
	// read environment property and convert to int
	
//	private int getIntProperty(String propName) {
//		
//		String propVal = env.getProperty(propName);
//		
//		// now convert to int
//		int intPropVal = Integer.parseInt(propVal);
//		
//		return intPropVal;
//	}	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factorys
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan("com.teja.entity");
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
	public AuthInterceptor addInterceptorObject() {
		return new AuthInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(addInterceptorObject());
	}
	
	

	
}
