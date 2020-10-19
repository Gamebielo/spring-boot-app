package br.gov.sp.fatec.springbootapp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {

    @Override // Recebo uma requisição, a resposta e o chain que é usado para passar a request
              // adiante
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            // Cast da request para (HttpServletRequest)
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            // Depois do cast , com o novo tipo, consigo pegar os headers...
            String authorization = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization != null) {
                // Jogando o Bearer fora (não utilizarei), deixando só o token
                Authentication credentials = JwtUtils.parseToken(authorization.replaceAll("Bearer ", ""));
                SecurityContextHolder.getContext().setAuthentication(credentials); // Faço o login
            }
            chain.doFilter(request, response); // Passando a requisição adiante
        } catch (Throwable t) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            // Retornando erro 401 (Unauthorized)
            servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, t.getMessage());
        }
    }
}