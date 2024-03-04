package likelion12.puzzle.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtUtility jwtUtility;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/swagger-ui/**").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/item-rent/**").hasRole("MEMBER")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                //간단한 테스트를 위해 csrf토큰 비활성화
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/**"))
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtility), UsernamePasswordAuthenticationFilter.class);
        ;
        return http.build();
    }


}

