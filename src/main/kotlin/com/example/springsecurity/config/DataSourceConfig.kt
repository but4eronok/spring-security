package com.example.springsecurity.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import javax.sql.DataSource


class DataSourceConfig {
    @Bean
    fun getDataSource(): DataSource? {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.postgresql.Driver")
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/users")
        dataSourceBuilder.username("postgres")
        dataSourceBuilder.password("123")
        return dataSourceBuilder.build()
    }
}