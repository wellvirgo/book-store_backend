package com.bookstore.backend_springboot.config;

import com.bookstore.backend_springboot.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.Writer;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    CustomUserDetailsService customUserDetailsService;

    @NonFinal
    @Value("${client.origin}") // Lấy giá trị từ application.properties
    private String CLIENT_ORIGIN;

    /* Khai báo các endpoint được truy cập không cần xác thực*/
    @NonFinal
    private static final String[] PUBLIC_ENDPOINT = {"/favicon.ico", "/login", "/api/v1/products/2"};

    /*  Khai báo bean mã hóa mật khẩu Bcrypt */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* Khai báo bean DaoAuthenticationProvider
     * để hỗ trợ cho Form Login
     * */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /* Khai báo bean cấu hình CORS
    * cho phép front-end gọi đến các api
    * */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(CLIENT_ORIGIN));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /* Khai báo bean AuthenticationSuccessHandler
    * giúp thay đổi response trả về khi login thành công
    *  */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String data = "{\"message\": \"Success!\", \"username\": \"" + userDetails.getUsername() + "\"}";
            Writer writer = response.getWriter();
            writer.write(data);
            writer.flush();
        };
    }

    /* Khai báo bean AuthenticationFailureHandler
     * giúp thay đổi response trả về khi login thất bại
     *  */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            Writer writer = response.getWriter();
            writer.write("{\"message\": \"Unauthorized!\"," +
                    "\"error\": \"" + exception.getMessage() + "\"}");
        };
    }

    /* Khai báo bean AccessDeniedHandler
     * giúp thay đổi response trả về
     * khi không có quyền truy cập
     *  */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");

            Writer writer = response.getWriter();
            writer.write("{\"message\": \"Unauthorized!\"," +
                    " \"error\": \"" + accessDeniedException.getMessage() + "\"}");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Cấu hình CORS
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()))

                // Vô hiệu hóa CSRF vì đang thiết kế REST API
                .csrf(AbstractHttpConfigurer::disable)

                // Cấu hình quyền truy cập
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll() // Cho phép truy cập endpoint công khai
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()) // Các endpoint còn lại cần xác thực

                // Custom lại Form Login
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler(authenticationSuccessHandler())
                        .failureHandler(authenticationFailureHandler())
                        .permitAll())

                // Xử lý khi không có quyền truy cập tài nguyên
                .exceptionHandling(e -> e
                        .accessDeniedHandler(accessDeniedHandler()))

                // Tạo session lưu phiên đăng nhập khi cần
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }
}
