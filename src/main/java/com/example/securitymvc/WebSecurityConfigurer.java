package com.example.securitymvc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/healtcheck").permitAll()
                .antMatchers("/user/*").hasRole("USER")
                .antMatchers("/najnowszy/*").hasRole("NAJNOWSZY")
                .antMatchers("/delete/*").hasRole("ADMIN")
                .antMatchers("/update/*").hasRole("NOWY");

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .authorities("USER")
                        .build();
        UserDetails najnowszy =
                User.withDefaultPasswordEncoder()
                        .username("najnowszy")
                        .password("password")
                        .roles("NAJNOWSZY")
                        .authorities("USER")
                        .build();
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build();

        UserDetails nowy =
                User.withDefaultPasswordEncoder()
                        .username("nowy")
                        .password("nowy")
                        .roles("NOWY")
                        .build();

        return new InMemoryUserDetailsManager(user, admin, nowy);
    }
}
