package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Indica que é uma classe de entidade (tabela)
@Table(name = "aut_autorizacao")
public class Autorizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aut_id")
    private Long id;        // bigint == Long
    
    @Column(name = "aut_nome")
    private String nome;    // varchar == String
    
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
}