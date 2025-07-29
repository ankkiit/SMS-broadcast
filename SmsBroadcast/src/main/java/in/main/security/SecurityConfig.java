package in.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

     return   http
        
        
        .formLogin(formLogin->
        formLogin
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/home",true)
        .failureUrl("/login?error=true")
        .permitAll()
       
       
        )
        .logout(logout->logout
        		.logoutUrl("/logout")
        		.logoutSuccessUrl("/login?logout=true") // Redirect to login page after successful logout
                .invalidateHttpSession(true) // Invalidate the session
                .deleteCookies("JSESSIONID")  // Delete the session cookie
        		.permitAll()
        		)
        
                
                .authorizeHttpRequests((authz) -> authz
              // .requestMatchers("/login2").permitAll()
                		.requestMatchers("/login","/css/**", "/js/**", "/images/**","/fonts/**","/vendor/**").permitAll()
                .anyRequest().authenticated()  
                
                
      
                )
                .build();
              
                
                
                
              
              
               

	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

		var user = User.withUsername("user1")

				.password(passwordEncoder.encode("Tester")).roles("USER").build();
//		var user2 = User.withUsername("user2")
//
//				.password(passwordEncoder.encode("Tester")).roles("USER").build();
		return new InMemoryUserDetailsManager(user);
//
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().requestMatchers("/resources/**");
//	}

}
// Basic authentication will never logout
