package br.gov.sp.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@Service("segurancaService") // Indica que o spring terá que gerar uma instancia dentro dessa classe (necessário para o AutoWired...)
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired // Instancia a classe para eu usar diretamente...    
    private AutorizacaoRepository autRepo;
    @Autowired // Instancia a classe para eu usar diretamente...    
    private UsuarioRepository usuarioRepo;

    @Transactional
    @Override
    public Usuario criarUsuario(String nome, String senha, String autorizacao) {
        Autorizacao aut = autRepo.findByNome(autorizacao);
        if(aut == null)
        { // Se ela não existe, crie ela...
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if (usuarioOp.isPresent()) {
            return usuarioOp.get();
        }
        throw new RuntimeException("Usuario não encontrado!");
    }
    
    @Override
    public Usuario buscarUsuarioPorNome(String nome) {
        Usuario usuario = usuarioRepo.findByNome(nome);
        if (usuario != null) {
            return usuario;
        }
        throw new RuntimeException("Usuário não encontrado!");
    }

    @Override
    public Autorizacao buscarAutorizacaoPorNome(String nome) {
        Autorizacao autorizacao = autRepo.findByNome(nome);
        if (autorizacao != null){
            return autorizacao;
        }
        throw new RuntimeException("Autorizacao não encontrada!"); // Não retornar null para não causar nullPoint
    }
}