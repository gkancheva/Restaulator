package com.company.restaulator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/public/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/users/register", "/errors").permitAll()
                .antMatchers("/users").access("hasAuthority('ADMIN')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/users/login").permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
            .logout()
                .logoutUrl("/users/logout").permitAll()
                .logoutSuccessUrl("/users/login?logout").permitAll()
            .and()
                .exceptionHandling()
                .accessDeniedPage("/errors/error")
                .and()
                .csrf().disable();
    }

}