package br.gov.sp.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

@Entity // Indica que é uma classe de entidade (tabela)
@Table(name = "aut_autorizacao")
public class Autorizacao {

    @JsonView(View.UsuarioCompleto.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aut_id")
    private Long id;        // bigint == Long
    
    @JsonView({View.UsuarioResumo.class, View.UsuarioCompleto.class, View.AutorizacaoResumo.class})
    @Column(name = "aut_nome")
    private String nome;    // varchar == String

    @JsonView(View.AutorizacaoResumo.class)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "autorizacoes") // Nunca ponha EAGER dos 2 lados, para não gerar loop e erros
    private Set<Usuario> usuarios;
    
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public Set<Usuario> getUsuarios(){
        return this.usuarios;
    }
    public void setUsuarios(Set<Usuario> usuarios){
        this.usuarios = usuarios;
    }
}