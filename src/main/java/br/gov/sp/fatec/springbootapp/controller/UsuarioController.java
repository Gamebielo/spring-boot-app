package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController // Indica que esta classe implementa webservices RESTful
@RequestMapping(value = "/usuario") // Rota do serviço
@CrossOrigin
public class UsuarioController {

    @Autowired
    private SegurancaService segurancaService;
    
    @GetMapping
    public List<Usuario> buscarTodos(){
        return segurancaService.buscarTodosUsuarios();
    }

    @GetMapping(value = "/{id}") // Passa o id direto para a url
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id){
        return segurancaService.buscarUsuarioPorId(id);
    }

    @GetMapping(value = "/nome") // url = "/nome?nome=NomeDoUsuario"
    public Usuario buscarUsuarioPorNome(@RequestParam(value="nome") String nome){
        return segurancaService.buscarUsuarioPorNome(nome);
    }

    @PostMapping
    public Usuario cadastrarNovoUsuario(@RequestBody Usuario usuario) {
        return segurancaService.criarUsuario(usuario.getNome(), usuario.getSenha(), "ROLE_USUARIO");
    }
}