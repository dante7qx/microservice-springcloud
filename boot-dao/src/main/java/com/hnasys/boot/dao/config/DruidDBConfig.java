package com.hnasys.boot.dao.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
public class DruidDBConfig {

	private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);
	
	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.initialSize}")
	private int initialSize;

	@Value("${spring.datasource.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.maxActive}")
	private int maxActive;

	/**
	 * 配置获取连接等待超时的时间
	 */
	@Value("${spring.datasource.maxWait}")
	private int maxWait;

	/**
	 * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
	 */
	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	/**
	 * 配置一个连接在池中最小生存的时间，单位是毫秒
	 */
	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;

	/**
	 * 打开PSCache，并且指定每个连接上PSCache的大小
	 */
	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;

	/**
	 * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙, 'config'用于加密
	 */
	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.filters}")
	private String filters;

	/**
	 * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
	 */
	@Value("${spring.datasource.connectionProperties}")
	private String connectionProperties;
	
	/**
	 * 合并多个DruidDataSource的监控数据
	 */
	@Value("${spring.datasource.useGlobalDataSourceStat}")
	private boolean useGlobalDataSourceStat;
	
	/**
	 * @Primary表示在同样的DataSource中，首先使用被标注的DataSource,将覆盖其他来源的DataSource
	 * 
	 * @return DataSource
	 */
	@Bean
    @Primary
    public DataSource dataSource(){  
        DruidDataSource datasource = new DruidDataSource();  
        datasource.setUrl(this.dbUrl);  
        datasource.setUsername(username);  
        datasource.setPassword(password);  
        datasource.setDriverClassName(driverClassName);  
          
        //configuration  
        datasource.setInitialSize(initialSize);  
        datasource.setMinIdle(minIdle);  
        datasource.setMaxActive(maxActive);  
        datasource.setMaxWait(maxWait);  
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
        datasource.setValidationQuery(validationQuery);  
        datasource.setTestWhileIdle(testWhileIdle);  
        datasource.setTestOnBorrow(testOnBorrow);  
        datasource.setTestOnReturn(testOnReturn);  
        datasource.setPoolPreparedStatements(poolPreparedStatements);  
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize); 
//      datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        
        try {  
            datasource.setFilters(filters);  
        } catch (SQLException e) {  
            logger.error("druid configuration initialization filter", e);  
        }  
//        datasource.setConnectionProperties("config.decrypt=true;config.decrypt.key=MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAN2MkDG3rm/w74X/UHxLD687pqtaOcZj3eJx7eyFMzWFVG1FTeSqgvj3bzIWqxvxc0BxLTN2RJKtXslk4Gpl9x8CAwEAAQ==;druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000;");
        datasource.setConnectionProperties(connectionProperties);  
          
        return datasource;  
    }  
}