package com.douzone.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement  // DataSourceTransactionManager 빈을 찾아 Transaction Manager로 사용
@PropertySource("classpath:com/douzone/mysite/config/app/jdbc.properties")
public class DBConfig {
	

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
////		dataSource.setUrl("jdbc:mysql://192.168.254.30:3307/webdb?characterEncoding=utf8");
////		dataSource.setUsername("webdb");
////		dataSource.setPassword("webdb");
//		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bookmall_local");
//		dataSource.setUsername("bookmall_local");
//		dataSource.setPassword("bookmall_local");
//		dataSource.setInitialSize(10);
//		dataSource.setMaxActive(100);
		
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class)); 
//		setInitialSize : 최초로 생성할 Connection 수 : default값 - 0개
		dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
//		setMaxActive : 커넥션풀이 제공할 최대 커넥션개수
		return dataSource;
	}
	
//	트랜잭션 매니저 등록
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource); 
	}
}