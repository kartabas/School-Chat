package com.schoolchat.school.chat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.schoolchat.school.chat.service.userService.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
		this.customOAuth2UserService = customOAuth2UserService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> {
					auth

							.requestMatchers("/", "/login", "/app", "/register", "/home", "/profile", "/css/**", "/js/**",
									"/fotos/**", "/error")
							.permitAll()
							.anyRequest().authenticated();
				})
				.oauth2Login(oauth2 -> oauth2
						.loginPage("/register")
						.defaultSuccessUrl("/register", true)
						.userInfoEndpoint(userInfo -> userInfo
								.userService(customOAuth2UserService))
						.failureUrl("/error"))
				.oauth2Login(oauth2 -> oauth2
						.loginPage("/login")
						.defaultSuccessUrl("/login", true)
						.userInfoEndpoint(userInfo -> userInfo
								.userService(customOAuth2UserService))
						.failureUrl("/error"))
				.build();

	}

	// @Bean
	// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
	// Exception {
	// http.csrf(csrf -> csrf.disable())
	// .authorizeHttpRequests(authorize -> authorize
	// .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
	// .anyRequest().authenticated())
	// // .formLogin(form -> form
	// // .loginPage("/login")
	// // .defaultSuccessUrl("/home", true)
	// // .failureUrl("/error")
	// // .permitAll())
	// .oauth2Login(oauth2 -> oauth2
	// .loginPage("/login")

	// // .defaultSuccessUrl("/login", true)
	// .failureUrl("/error"))
	// .userDetailsService(customUserDetailsService); // 👈 Required!

	// return http.build();
	// }

	@Bean
	public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder() {
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

	}

}
