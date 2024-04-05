package com.example.leadManagementSystem2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
//@EnableTransactionManagement
@EnableWebSecurity
public class MyConfiguration {

	@Autowired
	CustomAuthSuccessHandler customAuthSuccessHandler;
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoauthenticationProvider = new DaoAuthenticationProvider();
		daoauthenticationProvider.setUserDetailsService(this.getDetailsService());
		daoauthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		
		return daoauthenticationProvider;
	}
	
	@Bean
    public SecurityFilterChain FilterChain(HttpSecurity httpSecurity) throws Exception {
				
		httpSecurity.cors(AbstractHttpConfigurer::disable);
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		
		httpSecurity.authorizeHttpRequests(request->{
	            request.requestMatchers("/", "/login", "/CustomersForm/**" , "/saveLeads/**" , "/Admin/registration" , "/css/**" ,  "/addAdmin", "/SaveAdmin","/form","/process","/img/Suprams_logo.jpg", "/img/Bg.jpeg", "/img/**").permitAll();
	            request.requestMatchers("/user/**").hasRole("USER");
	            request.requestMatchers("/Admin/**").hasRole("ADMIN");
	            request.requestMatchers("/Caller/**").hasRole("CALLER");
	            request.requestMatchers("/fieldManager/**").hasRole("FIELDMANAGER");
	            request.requestMatchers("/Manager/**").hasRole("MANAGER");
	            request.requestMatchers("/businessAssociate/**").hasRole("BUSINESSASSOCIATE");
	            request.anyRequest().authenticated();
	            
	        }).formLogin(form->{
	            form.loginPage("/login").loginProcessingUrl("/userLogin")
	                   .successHandler(customAuthSuccessHandler).permitAll();
	        });
	        
	        
	        return httpSecurity.build();
		
		
		
	        
	}
}
	
