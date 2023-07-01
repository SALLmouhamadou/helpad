package fr.helpad.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		String REPORT_TO = "{\"group\":\"csp-violation-report\",\"max_age\":2592000,\"endpoints\":[{\"url\":\"https://localhost:8080/report\"}]}";
        http
                .authorizeHttpRequests(authorize -> authorize
                                //.mvcMatchers("/admin/**").hasRole("ADMIN")
                                //.mvcMatchers("/infirmerie/**").hasRole("INFIRMIERE")
                                //.mvcMatchers("/user/**").hasRole("USER")
                                .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("email")
                        //.loginProcessingUrl("/process-login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .headers(headers -> headers
                        .xssProtection()
                        .and()
                        .contentSecurityPolicy(
//                				"default-src http:// https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js 'self';" +
                                "script-src https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" + 
                                " https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js 'unsafe-inline' 'self';" +
                				"frame-src 'self';" + 
                                // Sandbox : Attention à vérifier les autorisations
                                "sandbox allow-same-origin allow-scripts allow-forms" + 
                				" allow-storage-access-by-user-activation allow-popups allow-modals;" +
                				"frame-ancestors 'self';" +
                                "report-uri /report; report-to csp-violation-report;" +
                				"style-src  https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/* 'unsafe-inline' 'self';" + 
                				"font-src https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/* 'self';"))
                .headers(headers -> headers.addHeaderWriter(new StaticHeadersWriter("Report-To", REPORT_TO)));
		return http.build();
	}

}
