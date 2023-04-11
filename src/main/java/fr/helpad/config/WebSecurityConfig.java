package fr.helpad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http

			.authorizeHttpRequests(authorize -> authorize
				.mvcMatchers("/admin/**").hasRole("ADMIN")
				//.mvcMatchers("/user/**").hasRole("USER")
				.anyRequest().permitAll()
			)
			.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			//.loginProcessingUrl("/process-login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=true")
			.permitAll();
			
			

		return http.build();
	}

}
