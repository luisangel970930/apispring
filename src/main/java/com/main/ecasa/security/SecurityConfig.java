package com.main.ecasa.security;


import com.main.ecasa.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @RequiredArgsConstructor @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder encoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
         customAuthenticationFilter.setFilterProcessesUrl("/api/ecasa/login");

       http.csrf().disable();
       http.sessionManagement().sessionCreationPolicy(STATELESS);
       http.authorizeRequests().antMatchers("/api/ecasa/login/**").permitAll();
       http.authorizeRequests().antMatchers("/api/ecasa/user/**").hasAnyAuthority("ROLE_USER");
       http.authorizeRequests().antMatchers("/api/ecasa/user/save/**").hasAnyAuthority("ROLE_ADMIN");
       http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();

    }
}
