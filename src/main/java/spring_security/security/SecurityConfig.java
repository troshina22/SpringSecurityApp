package spring_security.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private final SuccessUserHandler successUserHandler;

    public SecurityConfig(@Qualifier("userServiceImpl") UserDetailsService userDetailsService, SuccessUserHandler successUserHandler) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/login","/index").permitAll()
                .antMatchers("/user").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .and().formLogin()
                .loginPage("/login")
                .successHandler(successUserHandler)
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
