package com.miniproject.com.miniproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by STR02119 on 6/20/2017.
 */
@Configuration
public class Authentication extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    DataSource dataSource;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                //.antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/user")
                .loginPage("/home")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");

        auth
                .ldapAuthentication()
                .userDnPatterns("cn={0}")
                .contextSource(contextSource());
        /*auth.ldapAuthentication()
                .userDnPatterns("cn={0}")
                .contextSource()
                .url("ldap://172.20.55.11:389/dc=g-able,dc=local")
//                .port(389)
                .managerDn("cn=itsmgnt itsdept,dc=g-able,dc=local")
                .managerPassword("its@2013");*/
        /*auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
                .withUser("user").password("password").roles("USER");*/
        /*auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select name,password from user where name=?")*/
        /*auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select name ,password from user where name=?")
                .authoritiesByUsernameQuery(
                        "select Name, Role from user_role where name=?");*/
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        DefaultSpringSecurityContextSource defaultSpringSecurityContextSource;
        defaultSpringSecurityContextSource = new DefaultSpringSecurityContextSource(Collections.singletonList("ldap://172.20.55.11:389/"), "dc=g-able,dc=local");
        defaultSpringSecurityContextSource.setUserDn("cn=itsmgnt itsdept,dc=g-able,dc=local");
        defaultSpringSecurityContextSource.setPassword("its@2013");
        defaultSpringSecurityContextSource.setReferral("follow");
        return defaultSpringSecurityContextSource;
    }
}
