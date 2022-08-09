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

    // Personalize the Website Login feature. It checks the login username and password
    //against the Database
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, status from Usersvegan where username=?")
                .authoritiesByUsernameQuery("select u.username, p.profile from Uservgprofile up " +
                        "inner join Usersvegan u on u.userId = up.userId " +
                        "inner join Profiles p on p.profileId = up.profileId " +
                        "where u.username = ?");
    }


    // Configure the authorization to HTML templates per User role (Supervisor, Administrator
    //and regular User)
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
                        "/search/**",
                        "/orderRecByNameAsc",
                        "/orderRecByNameDesc",
                        "/bcrypt/**",
                        "/favorites/**",
                        "/profile/**",
                        "/recipes/view/**").permitAll()

                // set up authorizations as per Roles
              //  .antMatchers("/favorites/create/**", "/favorites/save/**", "/favorites/index/**").hasAuthority("USERVEGAN")
/*                .antMatchers("/favorites/create/**", "/favorites/save/**", "/favorites/index/**"
                        , "/favorites/update/**").hasAuthority("USERVEGAN")*/
                .antMatchers("/favorites/indexAdmin/**").hasAnyAuthority("ADMINISTRATOR")
                .antMatchers("/favorites/**", "/favorites/index/**").hasAnyAuthority("USERVEGAN", "SUPERVISOR", "ADMINISTRATOR")
             /*   .antMatchers("/favorites/**").hasAuthority("SUPERVISOR, ADMINISTRATOR, USERVEGAN")*/
                .antMatchers("/recipes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRATOR")

                .antMatchers("/categories/**",  "/categories/searchCat/**").hasAnyAuthority("SUPERVISOR","ADMINISTRATOR")

                .antMatchers("/usersvegan/**").hasAnyAuthority("ADMINISTRATOR")

               /* .antMatchers("/favorites/**", "/favorites/index/**").hasAuthority("USERVEGAN")*/

                // The rest of URLs require authentication
                .anyRequest().authenticated()
                // Form login don't require authentication
                // Configure a personalize 'login' page
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll();
    }

    // Password encryption. It will encrypt the password when a new User register to
    //the website
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
