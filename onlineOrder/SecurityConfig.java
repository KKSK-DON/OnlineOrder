package com.onlineorder.onlineOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {//customize url
        http
            .csrf()//跨域禁止访问
                .disable()
            .formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
            .failureForwardUrl("/login?error=true");
        http
            .authorizeRequests()
            .antMatchers("/order/*", "/cart", "/checkout").hasAuthority("ROLE_USER")
            //必须要有authentication的
            .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()//用jdbc做authentication
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder)
            .usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email=?")
            .authoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email=?");
    }
}

