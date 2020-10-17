package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

// UserDetailsServices é a classe que o spring usa para buscar os dados do user durante o login
public interface SegurancaService extends UserDetailsService {
    
    public Usuario criarUsuario(String nome, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioPorId(Long id);
    
    public Usuario buscarUsuarioPorNome(String nome);

    public Autorizacao buscarAutorizacaoPorNome(String nome);
}