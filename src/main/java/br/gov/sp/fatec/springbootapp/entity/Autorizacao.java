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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Indica que é uma classe de entidade (tabela)
@Table(name = "aut_autorizacao")
public class Autorizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aut_id")
    private Long id;        // bigint == Long
    
    @Column(name = "aut_nome")
    private String nome;    // varchar == String

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "autorizacoes") // Nunca ponha EAGER dos 2 lados, para não gerar loop e erros
    @JsonIgnore // Parar o loop no get dos usuários
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