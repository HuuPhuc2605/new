package iuh.fit.lehuuphuc_22713601.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails student = User
                .builder()
                .username("student")
                .password(passwordEncoder().encode("123"))
                .roles("STUDENT")
                .build();
        UserDetails admin = User
                .builder()
                .username("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(student, admin);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
        return httpSecurity
                .authorizeHttpRequests(h->h.requestMatchers("/courses").hasAnyRole("ADMIN", "STUDENT")
                        .anyRequest().authenticated())
                .formLogin(f->f.defaultSuccessUrl("/courses", true))
                .logout(l->l.logoutSuccessUrl("/login"))
                .build();
    }
}
