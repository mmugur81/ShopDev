package com.mmugur81.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

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
                .antMatchers("/", "/demo",
                    "/about", "/contact",
                    "/account/register", "/resources/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
            .authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasRole('ADMIN')")
                .and()
            .formLogin()
                .loginPage("/account/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/account/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/account/logout"))
                .permitAll();

        // Temporary allow api access for anyone
        http
            .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .anyRequest().anonymous();

        http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
            private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
            private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/api.*", null);

            @Override
            public boolean matches(HttpServletRequest request) {
                // No CSRF due to allowedMethod
                if(allowedMethods.matcher(request.getMethod()).matches())
                    return false;

                // No CSRF due to api call
                if(apiMatcher.matches(request))
                    return false;

                // CSRF for everything else that is not an API call
                return true;
            }
        });
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

}