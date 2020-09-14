package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional  // Abre uma transação (cada método dessa classe abre uma transação nova e uma conexao com o banco)
@Rollback       // Ao final da transaçao, não comita e sim dá rollback (usar em Testes apenas...)
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autRepo;

	@Test
	void contextLoads() {
    }
    
    // @Test // Criando um USER
    // void testaInsercao(){
    //     Usuario usuario = new Usuario();
    //     usuario.setNome("Gabriel");
    //     usuario.setSenha("123");
    //     usuarioRepo.save(usuario); // Ao salvar, o ID é automaticamente preenchido
    //     assertNotNull(usuario.getId());
    // }

    @Test // Criando um USER
    void testaInsercao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setSenha("SenhaF0rte");
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO");
        autRepo.save(aut); // Salvando o registro no banco para conseguir buscar e referênciar
        usuario.getAutorizacoes().add(aut); // Adicionando uma autorizacao para o usuario
        usuarioRepo.save(usuario); // Ao salvar, o ID é automaticamente preenchido
        assertNotNull(usuario.getAutorizacoes().iterator().next().getId());
    }

    @Test 
    void testaAutorizacao(){
        Usuario usuario = usuarioRepo.findById(1L).get(); // Pegando o primeiro registro
        assertEquals("ROLE_ADMIN", usuario.getAutorizacoes().iterator().next().getNome()); // Comparando com "ROLE_ADMIN"
    }

    @Test 
    void testaUsuario(){
        Autorizacao aut = autRepo.findById(1L).get(); // Pegando o primeiro registro
        assertEquals("Gabriel", aut.getUsuarios().iterator().next().getNome()); // Comparando com "Gabriel"
    }

}
