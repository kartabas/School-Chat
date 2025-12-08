package com.schoolchat.school.chat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.schoolchat.school.chat.service.userService.CustomOAuth2UserService;

@Configuration
// @EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/", "/login", "/register",
                        "/checkUsername", 
                        "/react-frontend/**",
                        "/home/**", "/profile/**",
                        "/css/**", "/js/**", "/fotos/**",
                        "/error"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/login", true)
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(customOAuth2UserService))
                .failureUrl("/error")
            );

        return http.build();
}

	@Bean
	public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder() {
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

	}

}
