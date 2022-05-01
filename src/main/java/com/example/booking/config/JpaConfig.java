package com.example.booking.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
class JpaConfig {

    @Bean
    public DataSource getDataSource()
    {
    	/**
    	 * # DATASOURCE 
			#spring.datasource.initialize=true
			#spring.datasource.url=jdbc:mysql://localhost:3306/booking?useSSL=false&useJDBCComplaintTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
			#spring.datasource.username=sharsoni
			#spring.datasource.password=Pwdmar@123
			#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    	 */
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/booking?useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true");
        dataSourceBuilder.username("sharsoni");
        dataSourceBuilder.password("Pwdmar@123");
        return dataSourceBuilder.build();
    }
}