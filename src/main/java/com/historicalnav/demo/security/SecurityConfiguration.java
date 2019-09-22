package com.historicalnav.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
    //Create User - historicalNAV
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("magda").password(encoder.encode("secret"))
                .roles("USER", "ADMIN");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/login").permitAll()
        //        .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
        //        .formLogin();
        http.authorizeRequests()
                .antMatchers("/login/**")
                .authenticated()
                .antMatchers("/todo/**")
                .permitAll()
                .and()
                .formLogin();
    }
}
