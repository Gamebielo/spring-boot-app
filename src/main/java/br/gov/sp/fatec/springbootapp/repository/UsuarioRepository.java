package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Usuario;

// DAO - Para acesso ao Banco de Dados
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Spring gera para mim a implementação da classe automaticamente

    // Implementando um método de consulta, onde "ContainsIgnoreCase" == contem o nome ignorando maiusculas e minusculas...
    public List<Usuario> findByNomeContainsIgnoreCase(String nome);

    // Retornando um, pois o nome no banco de dados é unique
    public Usuario findByNome(String nome);

    // JPQL  (select apelido from Entidade apelido where apelido.algo = ?1, ?2, ?3 ...)
    @Query("select u from Usuario u where u.nome = ?1")
    public Usuario buscaUsuarioPorNome(String nome);

    // util para login por exemplo, retorna o usuário onde o nome e a senha é igual o especificado
    public Usuario findByNomeAndSenha(String nome, String senha);

    @Query("select u from Usuario u where u.nome = ?1 and u.senha = ?2")
    public Usuario buscaUsuarioPorNomeESenha(String nome, String senha);

    // Buscando o nome da autorizacao
    public List<Usuario> findByAutorizacoesNome(String autorizacao);

    // Faço join usando o atributo de ligação da entidade
    @Query("select u from Usuario u inner join u.autorizacoes a where a.nome = ?1")
    public List<Usuario> buscaPorNomeAutrizacao(String autorizacao);
}