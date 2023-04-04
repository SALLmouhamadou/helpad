package fr.helpad;

import static org.springframework.security.config.Customizer.withDefaults;

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
				//.mvcMatchers("/user/**").hasRole("user")
				.anyRequest().permitAll()
			)
			.formLogin()
			.loginPage("/login")
			.permitAll();
			

		return http.build();
	}

}
