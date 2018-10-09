package com.erasolon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 
 * @author erasolon
 *
 * created on  17/08/2018
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${security.security-realm}")
	private String REALM;	
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
    	
        auth.inMemoryAuthentication().
        		withUser("user")
        			.password(passwordEncoder().encode("password")).roles("USER")
        		.and()
        		.withUser("admin")
        			.password(passwordEncoder().encode("password")).roles("ADMIN");
          	
    }


	protected void configure(HttpSecurity http) throws Exception {
	    http
	      .csrf().disable()
	      .anonymous().disable()
	      .sessionManagement()
	      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)	      
	      .and()
	      .authorizeRequests()	
	      .antMatchers("/oauth/token").authenticated()
	      .antMatchers("/oauth/check_token").permitAll()
	      .antMatchers("/demo/**").authenticated()
	      .and().httpBasic().realmName(REALM)
	      .and().csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return  PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	/**
	 * Basic HTTP security  filtering
	 * Please adjust  as per your need on Production
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        @SuppressWarnings("rawtypes")
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
	
}