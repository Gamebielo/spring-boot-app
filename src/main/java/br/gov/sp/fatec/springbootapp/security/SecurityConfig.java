package br.gov.sp.fatec.springbootapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // Segurança e pre configurações
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilitar segurança por anotação
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired // Irá pegar o segurancaService
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and()
                // this disables session creation on Spring Security (para cada página, gera um
                // token e confirma o acesso...)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    // Configurando como o spring faz a autenticação (LOGIN)
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Toda vez que receber um usuario e senha, use esse serviço para buscar o usuário...
        auth.userDetailsService(userDetailsService);
    }

    // Essa tag pega o objeto e disponibiliza ele no spring para conseguirmos
    // utilizar o autowired (usar em outos lugares)
    @Bean // Utilizo essa tag em classes que não são minhas...
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Indica que irei utilizar este padrão de encoder...
    }
}