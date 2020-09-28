package br.gov.sp.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

@Entity // Indica que é uma classe de entidade (tabela)
@Table(name = "usr_usuario")
public class Usuario {

    @JsonView(View.UsuarioCompleto.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;        // bigint == Long
    
    @JsonView({View.UsuarioResumo.class, View.AutorizacaoResumo.class})
    @Column(name = "usr_nome")
    private String nome;    // varchar == String
    
    @Column(name = "usr_senha")
    private String senha;

    @JsonView(View.UsuarioResumo.class)
    @ManyToMany(fetch = FetchType.EAGER) // EAGER me fará um join e preencherá nessa lista todas as aut. ligadas a esse user
    @JoinTable(name = "uau_usuario_autorizacao", // Nome da tabela de ligação
        joinColumns = { @JoinColumn(name = "usr_id") }, // Coluna que referencia o user
        inverseJoinColumns = { @JoinColumn(name = "aut_id") } // Coluna que referencia o outro lado
    )
    private Set<Autorizacao> autorizacoes; // Set pois não posso repetir 2x o usuário com a mesma autorizacao
    
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

    public String getSenha(){
        return this.senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public Set<Autorizacao> getAutorizacoes(){
        return this.autorizacoes;
    }
    public void setAutorizacoes(Set<Autorizacao> autorizacoes){
        this.autorizacoes = autorizacoes;
    }
}