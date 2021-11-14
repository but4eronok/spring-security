package com.example.springsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@SpringBootApplication
class SpringSecurityApplication

fun main(args: Array<String>) {
    runApplication<SpringSecurityApplication>(*args)
}

@Configuration
@EnableWebSecurity
class SecurityWebInitializer : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .and()
            .httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .inMemoryAuthentication()
            .withUser("bob")
            .password("belcher")
            .roles("USER")
            .and()
            .withUser("admin")
            .password("admin")
            .roles("USER", "ADMIN")
    }
}
