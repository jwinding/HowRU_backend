package com.academy.HowRU.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    PasswordEncoder encoder;

    @Bean
    public PasswordEncoder encoder() {
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        encoder=passwordEncoder;
        return passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = encoder();
        super.configure(auth);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors();

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/", "/h2", "/h2/**").permitAll()
                .anyRequest().authenticated();
//        http.httpBasic().and()
//                .authorizeRequests()
//                .antMatchers("/","/h2","/h2/**").permitAll()
//                .anyRequest().authenticated();
    }

}
