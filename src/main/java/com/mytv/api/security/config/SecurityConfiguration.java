package com.mytv.api.security.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mytv.api.firebase.filter.FirebaseJwtFilter;
import com.mytv.api.security.filter.JWTRequestFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTRequestFilter jwtRequestFilter;
	
	@Autowired
	
	private FirebaseJwtFilter firebaseFilter;


	@Autowired
	PasswordEncoder passwordEncoder;


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		final List<GlobalAuthenticationConfigurerAdapter> configurers = new ArrayList<>();
		configurers.add(new GlobalAuthenticationConfigurerAdapter() {
			@Override
			public void configure(AuthenticationManagerBuilder auth) throws Exception {
				auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
			}
		});
		return authConfig.getAuthenticationManager();
	}

	private void sharedSecurityConfiguration(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable).cors().configurationSource(corsConfigurationSource()).and()
				.sessionManagement(httpSecuritySessionManagementConfigurer -> {
					httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				});
	}


	@Bean
	public SecurityFilterChain securityFilterChainGlobalAdminAPIv1(HttpSecurity httpSecurity) throws Exception {
		sharedSecurityConfiguration(httpSecurity);
		httpSecurity.securityMatcher("api/v1/admin/**").authorizeHttpRequests(auth -> {
			auth.anyRequest()
			.hasRole("ADMIN");
		}).addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChainGlobalAbonneAPIv1(HttpSecurity http) throws Exception {
		
		sharedSecurityConfiguration(http);
		
		//La partie front sera géré par Fire base maintenant
		http.csrf().disable()
        .authorizeRequests()
        .requestMatchers("api/v1/front/**").authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      http.addFilterBefore(firebaseFilter, UsernamePasswordAuthenticationFilter.class);

      
      return http.build();
		/*
		
		httpSecurity.securityMatcher("api/v1/front/**").authorizeHttpRequests(auth -> {
			auth.anyRequest()
			.hasRole("USER");
		}).addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
		
		*/
	}

	@Bean
	public SecurityFilterChain securityFilterChainRegisterAPI(HttpSecurity httpSecurity) throws Exception {
		sharedSecurityConfiguration(httpSecurity);
		httpSecurity.securityMatcher("api/v1/auth/**").authorizeHttpRequests(auth -> {
			auth.anyRequest().permitAll();
			
		}).addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		final CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Collections.singletonList("*"));
		configuration.setAllowedMethods(Collections.singletonList("*"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));

		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
