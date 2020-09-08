package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

// DAO - Para acesso ao Banco de Dados
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Spring gera para mim a implementação disso automaticamente
}