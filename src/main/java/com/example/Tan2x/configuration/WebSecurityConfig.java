package com.example.Tan2x.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Tan2x.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
    public UserDetailsService userDetailsService() {
		System.out.println("this is userdetailsService in config");
        return new CustomUserDetailService();
    }
     
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	System.out.println("this is DaoAuthenticationProvider");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        System.out.println("good thing you are doing");
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("this is configure method with managerBuilder");
        auth.authenticationProvider(authenticationProvider());
        System.out.println("this is managerBuilder function in last");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println("this is configure function");
    	http
		.authorizeHttpRequests((requests) -> requests
			.antMatchers("/", "/home", "/shop/**", "/shop/viewproduct/**","/register").permitAll()
			.antMatchers("/resources/", "/static/", "/images/**", "/productImages/**", "/css/**", "/js/**").permitAll()
			.antMatchers("/admin","/admin/**").hasRole("Admin")
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
			.loginPage("/loginUrl")
			.loginProcessingUrl("/loginUrl")
			.permitAll()
			.defaultSuccessUrl("/home/")
			.usernameParameter("mobile")
			.passwordParameter("password")
		)
		.logout((logout) -> logout.permitAll());

    }
	
}
