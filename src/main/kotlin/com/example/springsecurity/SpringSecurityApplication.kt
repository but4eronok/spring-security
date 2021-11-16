package com.example.springsecurity

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource


@SpringBootApplication
class SpringSecurityApplication


fun main(args: Array<String>) {
    runApplication<SpringSecurityApplication>(*args)
}

@Configuration
@EnableWebSecurity
class SecurityWebInitializer : WebSecurityConfigurerAdapter() {
    @Autowired
    private val dataSource: DataSource? = null

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/app/**").hasRole("ADMIN")
            .antMatchers("/api/**").hasAnyRole("ADMIN", "API")
            .antMatchers("/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .and()
            .logout().permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select login, password, 'true' from users where login=?")
            .authoritiesByUsernameQuery("select login, role from users where login=?")

    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder(8)
    }
}

