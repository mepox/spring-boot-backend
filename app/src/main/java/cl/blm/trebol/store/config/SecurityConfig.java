package cl.blm.trebol.store.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.Claims;

import cl.blm.trebol.store.security.JwtTokenVerifierFilter;
import cl.blm.trebol.store.security.JwtUsernamePasswordAuthenticationFilter;
import cl.blm.trebol.store.services.security.AuthorizationTokenParserService;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig
    extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;
  private final SecretKey secretKey;
  private final JwtProperties jwtConfig;
  private final AuthorizationTokenParserService<Claims> jwtClaimsParserService;

  @Autowired
  public SecurityConfig(
      PasswordEncoder passwordEncoder,
      UserDetailsService userDetailsService,
      SecretKey secretKey,
      JwtProperties jwtConfig,
      AuthorizationTokenParserService<Claims> jwtClaimsParserService) {
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
    this.secretKey = secretKey;
    this.jwtConfig = jwtConfig;
    this.jwtClaimsParserService = jwtClaimsParserService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
        .addFilterAfter(new JwtTokenVerifierFilter(jwtClaimsParserService),
            JwtUsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/login", "/catalog/**").permitAll()
        .anyRequest().authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

}