package com.microservice.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private InfoAdicionalToken infoToken;

	@Override  //Endpoint auth/token  es el que va a dar el username pass etc POST username, password, client id, claims
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoToken, jwtAccessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(jwtTokenStore())
		.accessTokenConverter(jwtAccessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
	}
	
	@Override  //Permiso que tendra un endpoint
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()"); //Permit all hace que el usuario acceda a todo
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("backEndApp")
		.secret(passwordEncoder.encode("contra123"))
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);
	}

	@Bean
	public JwtTokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("llave_belica_4x4");
		return tokenConverter;
	}
	
}
