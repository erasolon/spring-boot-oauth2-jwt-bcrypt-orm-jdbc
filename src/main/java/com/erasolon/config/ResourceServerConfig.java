/**
 * Config for resource server 
 * 
 * 
 * @project TariffServices
 * @package com.tariff.config
 * @filename ResourceServerConfig.java
 * @author Emile Rasoloniaina
 * @createDate 10 Aug 2018 13:31:53
 * @version 
 * @param 
 *  
 */
package com.erasolon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author erasolon
 *
 * Created on  19/08/2018
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	ResourceServerTokenServices tokenServices;
	
		
	@Override
    public void configure(HttpSecurity http) throws Exception {
         http          
           .authorizeRequests()
           .antMatchers(HttpMethod.GET,"/demo/**").access("#oauth2.hasScope('read')")
           .antMatchers(HttpMethod.POST,"/demo/**").access("#oauth2.hasScope('write')")
           .antMatchers(HttpMethod.PUT,"/demo/**").access("#oauth2.hasScope('write')")
           .antMatchers(HttpMethod.DELETE,"/demo/**").access("#oauth2.hasScope('write')");
    }
	
	/**
	 * If the resource server is running on different instance, then it will use RemoteTokenSevices, then uncomment below 
	 * @return  tokenServices
	 */
    /**
	@Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(
                "http://localhost:8080/oauth/check_token");
        tokenService.setClientId("customer-service");  // no need for Authentication 
        tokenService.setClientSecret("password");
        return tokenService;
    }    
    */

}
