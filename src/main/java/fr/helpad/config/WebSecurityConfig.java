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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                                " https://www.google.com/maps/*" +
                                " https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js 'unsafe-inline' 'self';" +
                				"frame-src https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2631.535289347332!2d2.252469775189193!3d48.73347070949117!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47e67831935ebb83%3A0xb61ad29627d1475e!2sGRETA%20de%20Massy!5e0!3m2!1sfr!2sfr!4v1686051531647!5m2!1sfr!2sfr 'self';" + 
                                // Sandbox : Attention à vérifier les autorisations
                                "sandbox allow-same-origin allow-scripts allow-forms" + 
                				" allow-storage-access-by-user-activation allow-popups allow-modals;" +
                				"frame-ancestors 'self';" +
                                "report-uri /report; report-to csp-violation-report;" +
                				"style-src  https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/* 'unsafe-inline' 'self';" + 
                				"font-src https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/* 'self';"))
                .headers(headers -> headers.addHeaderWriter(new StaticHeadersWriter("Report-To", REPORT_TO)))
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")));
		return http.build();
	}

}
