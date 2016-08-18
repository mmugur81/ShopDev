package com.mmugur81.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by mugurel on 18.08.2016.
 * Security
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/demo", "/account/register", "/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasRole('ADMIN')")
                .and()
            .formLogin()
                .loginPage("/account/login")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/account/logout")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(
        AuthenticationManagerBuilder auth,
        UserDetailsService userDetailsService,
        PasswordEncoder encoder
    )
        throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}