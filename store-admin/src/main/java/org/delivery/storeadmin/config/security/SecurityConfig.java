package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // security 활성화
public class SecurityConfig {

    private static final String[] SWAGGER = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //csrf 공격 보안 disable 처리 (csrf Cross Site Forgery 사이트 간 요청 위조)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                // static resource 의 경우 모든 요청 허용 처리
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                                // swagger 인증 없이 통과 처리
                                .requestMatchers(SWAGGER).permitAll()

                                // open-api 하위 모든 주소는 인증 없이 통과
                                .requestMatchers("/open-api/**").permitAll()

                                // 그 외 모든 요청 인증 사용
                                .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // spring security 는 BCryptPasswordEncoder 사용
        // hash 방식으로 암호화 (salt 추가 해시로 저장하고, 해시를 시켜서 두 값이 동일한지 확인한느 형태)
        // decoding 불가, Encoding 만 가능해서 두개의 값을 비교해서 체크
        return new BCryptPasswordEncoder() ;
    }
}
