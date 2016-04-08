package com.quantlance.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	 @Autowired
	 private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/users/**").hasAuthority("ADMIN")
				.antMatchers("/product/**").hasAnyAuthority("ADMIN","USER")
				.antMatchers("/products/**").hasAnyAuthority("ADMIN","USER").anyRequest()
				.fullyAuthenticated().and().formLogin().loginPage("/login")
				.successHandler(successHandler())
				.failureUrl("/login?error").usernameParameter("email")
				.permitAll().and().logout().logoutUrl("/logout").permitAll()
				.deleteCookies("remember-me").logoutSuccessUrl("/")
				.and().rememberMe();
				//http.csrf().disable();
	}

	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
	 auth
	 .userDetailsService(userDetailsService)
	 .passwordEncoder(new BCryptPasswordEncoder());
	 }
	 
	 
	 @Bean
	 public AuthenticationSuccessHandler successHandler() {
	     SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler("/");
	     handler.setAlwaysUseDefaultTargetUrl(false);
	     handler.setTargetUrlParameter("spring-security-redirect");
	     return handler;	     
	 }
	 
}