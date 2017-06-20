package com.citobi.leasing;

import com.citobi.leasing.tools.DualUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /**
     * Configure the authentication system for Security
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username,password,enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");

        // For testing purpose, have an inmemory user so that we are not locked out of the system
        //todo have a multi environment system and have this line only on test/dev and not production
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
    }

    /**
     * Secure the application endpoints to user levels
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").successHandler(authenticationSuccessHandler())
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();
    }

    /**
     * Define the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    /**
     * Delegate the AuthenticationSuccessHander to one of our own so we can redirect correctly
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        AuthenticationSuccessHandler authenticationSuccessHandler = new DualUrlAuthenticationSuccessHandler();
        return authenticationSuccessHandler;
    }
}
