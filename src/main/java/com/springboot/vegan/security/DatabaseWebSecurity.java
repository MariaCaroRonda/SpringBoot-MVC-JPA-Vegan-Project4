package com.springboot.vegan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, status from Usersvegan where username=?")
                .authoritiesByUsernameQuery("select u.username, p.profile from Uservgprofile up " +
                        "inner join Usersvegan u on u.userId = up.userId " +
                        "inner join Profiles p on p.profileId = up.profileId " +
                        "where u.username = ?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Static resources don't require authentication
                .antMatchers(
                        "/bootstrap/**",
                        "/images/**",
                        "/tinymce/**",
                        "/logos/**").permitAll()
                // Public views don't require authentication
                .antMatchers("/",
                        "/signup",
                        "/search",
                        "/bcrypt/**",
                        "/favorites/**",
                        "/profile/**",
                        "/recipes/view/**").permitAll()

                // set up authorizations as per Roles
/*                .antMatchers("/favorites/create/**",
                        "/favorites/save/**").hasAnyAuthority("USERVEGAN")*/

/*                .antMatchers("/favorites/**").hasAnyAuthority("SUPERVISOR, ADMINISTRATOR")*/
                .antMatchers("/recipes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRATOR")
                .antMatchers("/categories/**").hasAnyAuthority("SUPERVISOR","ADMINISTRATOR")
                .antMatchers("/usersvegan/**").hasAnyAuthority("ADMINISTRATOR")


                // The rest of URLs require authentication
                .anyRequest().authenticated()
                // Form login don't require authentication
/*                .and().formLogin().permitAll();*/
                // Configure a personalized 'login' page
                .and().formLogin().loginPage("/login").permitAll();
    }

    // Password encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
