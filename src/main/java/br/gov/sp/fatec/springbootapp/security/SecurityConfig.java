package br.gov.sp.fatec.springbootapp.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity // Segurança e pre configurações
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilitar segurança por anotação
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and()
        // this disables session creation on Spring Security (para cada página, gera um token e confirma o acesso...)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}