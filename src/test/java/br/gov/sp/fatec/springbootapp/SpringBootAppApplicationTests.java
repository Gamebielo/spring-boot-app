package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional  // Abre uma transação (cada método dessa classe abre uma transação nova)
@Rollback       // Ao final da transaçao, não comita e sim dá rollback (usar em Testes apenas...)
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

	@Test
	void contextLoads() {
    }
    
    @Test
    void testaInsercao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Gabriel");
        usuario.setSenha("123");
        usuarioRepo.save(usuario); // Ao salvar, o ID é automaticamente preenchido
        assertNotNull(usuario.getId());
    }

}
