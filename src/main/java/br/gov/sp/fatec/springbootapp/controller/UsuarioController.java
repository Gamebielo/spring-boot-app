package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

import com.fasterxml.jackson.annotation.JsonView;

@RestController // Indica que esta classe implementa webservices RESTful
@RequestMapping(value = "/usuario") // Rota do serviço
@CrossOrigin
public class UsuarioController {

    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.UsuarioResumo.class)
    @GetMapping()
    public List<Usuario> buscarTodos() {
        return segurancaService.buscarTodosUsuarios();
    }

    @JsonView(View.UsuarioCompleto.class)
    @GetMapping(value = "/{id}") // Passa o id direto para a url
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id) {
        return segurancaService.buscarUsuarioPorId(id);
    }

    @JsonView(View.UsuarioResumo.class)
    @GetMapping(value = "/nome") // url = "/nome?nome=NomeDoUsuario"
    public Usuario buscarUsuarioPorNome(@RequestParam(value = "nome") String nome) {
        return segurancaService.buscarUsuarioPorNome(nome);
    }

    @JsonView(View.UsuarioResumo.class)
    @PostMapping // ResponseEntyti para formatar as respostas do HTTP
    public ResponseEntity<Usuario> cadastrarNovoUsuario(@RequestBody Usuario usuario,
            UriComponentsBuilder uriComponentsBuilder) { // Pega os dados da requisiçao
        usuario = segurancaService.criarUsuario(usuario.getNome(), usuario.getSenha(), "ROLE_USUARIO");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            uriComponentsBuilder.path( // Pegando a url deste controler
                "/usuario/" + usuario.getId()  // Colocando na rota específica após a criação
            ).build().toUri()
        );
        // Enviando o usuario criado, a url e o status (CREATED 201)
        return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
    }

    @JsonView(View.AutorizacaoResumo.class)
    @GetMapping(value = "/autorizacao/{autorizacao}")
    public Autorizacao buscarAutorizacaoPorNome(@PathVariable("autorizacao") String nome) {
        return segurancaService.buscarAutorizacaoPorNome(nome);
    }
}