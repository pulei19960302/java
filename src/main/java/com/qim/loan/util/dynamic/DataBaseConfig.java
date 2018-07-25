package com.qim.loan.util.dynamic;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataBaseConfig{
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name="writeDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name="readDataSource")
    @ConfigurationProperties(prefix="spring.datasource.read")
    public DataSource readDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
