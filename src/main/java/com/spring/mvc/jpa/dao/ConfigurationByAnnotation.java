package com.spring.mvc.jpa.dao;


	 
	import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
	 
	@Configuration
	@EnableTransactionManagement
	@EnableWebMvc
	@PropertySource(value = { "classpath:application.properties" })
	public class ConfigurationByAnnotation {
	 
	    @Autowired
	    private Environment environment;
/*	 
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
	        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
	        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
	        return dataSource;
	    }
	 
	    
	     * Provider specific adapter.
	     
	    @Bean
	    public JpaVendorAdapter jpaVendorAdapter() {
	        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
	        return hibernateJpaVendorAdapter;
	    }
	 
	    
	     * Here you can specify any provider specific properties.
	     
	    private Properties jpaProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	        // properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	        return properties;
	    }
	    
	    
	    
	    @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
	        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	        factoryBean.setDataSource(dataSource());
	        factoryBean.setPackagesToScan(new String[] { "com.spring.mvc.jpa" });
	        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	        factoryBean.setJpaProperties(jpaProperties());
	        return factoryBean;
	    }
	 */
	    
	    
	 
	   /* @Bean
	    @Autowired
	    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	        JpaTransactionManager txManager = new JpaTransactionManager();
	        txManager.setEntityManagerFactory(emf);
	        return txManager;
	    }*/
	    
	    
	    
	    @Bean
		public JpaTransactionManager jpaTransMan(){
			JpaTransactionManager jtManager = new JpaTransactionManager(entityManagerFactoryBean().getObject());
			return jtManager;
		}
		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
			LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
			lcemfb.setDataSource(getDataSource());
			lcemfb.setPersistenceUnitName("localContainerEntity");
			LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
			lcemfb.setLoadTimeWeaver(loadTimeWeaver);
			return lcemfb;
		}
		@Bean
		public DataSource getDataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
		       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		       dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa_hibernate_integration_tiles");
		       dataSource.setUsername("root");
		       dataSource.setPassword("1234");
		       return dataSource;
		}
	 
	} 

