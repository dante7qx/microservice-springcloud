package org.dante.springcloud.microsleuth.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import zipkin.storage.mysql.MySQLStorage;

@Configuration
public class ZipkinConfig {
	@Bean
    public MySQLStorage mySQLStorage(DataSource datasource) {
        return MySQLStorage.builder().datasource(datasource).executor(Runnable::run).build();
    }
}
