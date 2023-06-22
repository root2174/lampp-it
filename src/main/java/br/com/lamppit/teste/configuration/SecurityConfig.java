package br.com.lamppit.teste.configuration;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static br.com.lamppit.teste.auth.model.Role.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;

	@Value("${allowedHosts}")
	private String[] allowedOrigins;

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		for (String origin : allowedOrigins) {
			config.addAllowedOrigin(origin);
		}
		config.setAllowCredentials(true);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeHttpRequests(request -> request
						.requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/v2/api-docs")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/configuration/ui")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/swagger-resources/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/configuration/security")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/api/v1/hello")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/api/v1/products", "POST")).hasRole(COMPANY.name())
						.requestMatchers(new AntPathRequestMatcher("/api/v1/orders", "POST")).hasRole(CUSTOMER.name())
						.requestMatchers(new AntPathRequestMatcher("/api/v1/orders/change-status/**", "PATCH")).hasRole(COMPANY.name())
						.requestMatchers(new AntPathRequestMatcher("/api/v1/orders/customer/orders", "GET")).hasRole(CUSTOMER.name())
						.requestMatchers(new AntPathRequestMatcher("/api/v1/orders/delivery/available", "GET")).hasRole(DELIVERY_PERSON.name())
						.anyRequest().permitAll()
				)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
