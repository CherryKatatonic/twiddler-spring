package techtonic.academy.twiddler.security;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import techtonic.academy.twiddler.service.CustomUserDetailService;

// The Security Configuration class is the central core of our Spring Boot Security implementation.
@Configuration
// Works with WebSecurityConfigurerAdapter to optimize security settings for a web application
@EnableWebSecurity
// Enable method-level security annotations (@Secured - not used anywhere in this app, but could be)
@EnableGlobalMethodSecurity(
    securedEnabled = true, // Enable @Secured annotation
    jsr250Enabled = true, // Enable JSR-250 annotations
    prePostEnabled = true // Enable pre-post annotations
)
// WebSecurityConfigurerAdapter bootstraps a lot of default web security settings for us
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private JwtAuthEntryPoint unauthorizedHandler;
    @Autowired private CustomUserDetailService customUserDetailService;

    @Bean JwtAuthFilter authFilter() {
        return new JwtAuthFilter();
    }

    // Create a @Bean instance of BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure the Authentication Manager with CustomUserDetailService and BCryptPasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    // Create a @Bean instance of the Authentication Manager
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Added to prevent "Invalid CORS request" error when running being HTTPS proxy server
    // https://www.bountysource.com/issues/33825705-broken-cors-support-with-spring-security
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // The `configure(HttpSecurity http)` method determines how this Spring app interacts with HTTP requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Enable cross-origin resource sharing (because front-end is running on a different port):
            .cors().configurationSource(corsConfigurationSource())
            // CSRF protection not necessary when using JWT (and also extremely frustrating in Spring):
            .and().csrf().disable()
            // Enable custom exception handling for custom auth entry point:
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            // Set session management to "stateless" because we're using JWT:
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // Enable the option to use an embedded H2 database if MySQL is not present (to make the app more portable):
            .and().headers().frameOptions().sameOrigin()
            // Authorize requests to base URL and API endpoints
            .and().authorizeRequests()
                // Permit all requests to root URL (/) and User Controller (/api/users, /api/login, /api/signup)
                .antMatchers("/", "/api/users/**").permitAll()
                // Permit GET requests to Twiddle Controller (GET:/api/twiddles)
                .antMatchers(HttpMethod.GET,"/api/twiddles").permitAll()
            // Require authentication for any other paths (POST:/api/twiddles, etc...)
            .anyRequest().authenticated();

        // Add our JwtAuthFilter to the beginning of the HTTP request chain
        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
