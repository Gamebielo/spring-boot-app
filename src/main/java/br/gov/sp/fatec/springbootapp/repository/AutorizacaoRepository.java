package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;

// DAO - Para acesso ao Banco de Dados
public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
    // Spring gera para mim a implementação disso automaticamente
    // JPARepository tem todas as funcionalidades necessárias, buscar, salvar, dar update...
}