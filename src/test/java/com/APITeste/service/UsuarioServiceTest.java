package com.APITeste.service;

import com.APITeste.models.Usuario;
import com.APITeste.repositories.UsuarioRepository;
import com.APITeste.validator.UserValidator;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@AutoConfigureDataJpa
@Transactional
@ActiveProfiles("teste")
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService service;

    @Autowired
    UserValidator userValidator;

    @Autowired
    private UsuarioRepository repository;

    @Test
    @DisplayName("Verifica se retorna uma lista de usuarios")
    @Sql("classpath:sql/success_insert.sql")
    public void buscaListaDeUsuariosCadastrados() {

        assertThat(!service.getAllUsers().isEmpty());
    }

    @Test
    @DisplayName("Verifica se retorna um usuario")
    @Sql("classpath:sql/success_insert.sql")
    public void buscaUsuarioCadastradoPorId() {
        Long id = obtemIdDaBase();

        assertThat(service.getUserById(id).isPresent());
    }

    @Test
    @DisplayName("Verifica se um usuario é cadastrado")
    @Sql("classpath:sql/success_insert.sql")
    public void cadastraUsuario() {
        Usuario user = usuarioValido();

        assertThat(service.newUser(user).equals(user));
    }

    @Test
    @DisplayName("Verifica se um usuario atualizado")
    @Sql("classpath:sql/success_insert.sql")
    public void atualizaUsuario() {

        Long id = obtemIdDaBase();

        Usuario usuarioAtualizado = service.updateUser(id, usuarioValido());

        assertThat(usuarioAtualizado.equals(usuarioValido()));
    }

    @Test
    @DisplayName("Verifica se um usuario é deletado")
    @Sql("classpath:sql/success_insert.sql")
    public void deletaUsuario() {

        Long id = obtemIdDaBase();

        service.deleteUser(id);

        assertThat(service.getUserById(id).isEmpty());
    }

    private Long obtemIdDaBase() {
        List<Usuario> users = service.getAllUsers();
        Long id = users.get(0).getId();
        return id;
    }

    private static Usuario usuarioValido() {
        Usuario user = Usuario.builder()
                .email("teste@hotmail.com")
                .nome("Fulano dos Santos")
                .endereco("Rua Sempre Ali, 22")
                .habilidades(Collections.emptyList())
                .dataNascimento(LocalDate.of(1990, 6, 20)).build();
        return user;
    }
}
