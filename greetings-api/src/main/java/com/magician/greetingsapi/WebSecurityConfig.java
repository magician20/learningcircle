package com.magician.greetingsapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import com.c4_soft.springaddons.security.oauth2.config.reactive.ResourceServerAuthorizeExchangeSpecPostProcessor;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    ResourceServerAuthorizeExchangeSpecPostProcessor authorizeExchangeSpecPostProcessor() {
        // @formatter:off
		return (ServerHttpSecurity.AuthorizeExchangeSpec spec) -> spec
				//  .pathMatchers(HttpMethod.GET,"/api/test/anonymous").permitAll()
                    // IDK why this not working
                    //    .pathMatchers(HttpMethod.GET,"/api/test/user").hasAnyRole("ROLE_USER","ROLE_ADMIN")
                    //    .pathMatchers(HttpMethod.GET,"/api/test/admin").hasRole("ROLE_ADMIN")
				.anyExchange().authenticated();
		// @formatter:on
    }

}