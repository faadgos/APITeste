package com.APITeste.validator;

import com.APITeste.models.Usuario;
import com.APITeste.repositories.UsuarioRepository;
import com.APITeste.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

    @InjectMocks
    UserValidator validator;

    @Test
    @DisplayName("Verificações com usuário valido")
    public void testaVerificacoesComUsuarioValido() {
        Usuario usuario = Usuario.builder()
                .email("teste@hotmail.com")
                .nome("Fulano dos Santos")
                .endereco("Rua Sempre Ali, 22")
                .habilidades(Collections.emptyList())
                .dataNascimento(LocalDate.of(1990, 6, 20)).build();

        assertThat(validator.isValid(usuario)).isEqualTo(true);
    }

    @Test
    @DisplayName("Verificações com usuário invalido, nome com caracter especial")
    public void testaVerificacoesComUsuarioInvalido1() {
        Usuario usuario = Usuario.builder()
                .email("teste@hotmail.com")
                .nome("Fulano dos Santos@")
                .endereco("Rua Sempre Ali, 22")
                .habilidades(Collections.emptyList())
                .dataNascimento(LocalDate.of(1990, 6, 20)).build();

        assertThat(validator.isValid(usuario)).isEqualTo(false);
    }

    @Test
    @DisplayName("Verificações com usuário invalido, provedor de email fora do esperado")
    public void testaVerificacoesComUsuarioInvalido2() {
        Usuario usuario = Usuario.builder()
                .email("teste@hotmaiu.com")
                .nome("Fulano dos Santos")
                .endereco("Rua Sempre Ali, 22")
                .habilidades(Collections.emptyList())
                .dataNascimento(LocalDate.of(1990, 6, 20)).build();

        assertThat(validator.isValid(usuario)).isEqualTo(false);
    }

    @Test
    @DisplayName("Verificações com usuário invalido, idade menor que 18 anos")
    public void testaVerificacoesComUsuarioInvalido3() {
        Usuario usuario = Usuario.builder()
                .email("teste@hotmail.com")
                .nome("Fulano dos Santos")
                .endereco("Rua Sempre Ali, 22")
                .habilidades(Collections.emptyList())
                .dataNascimento(LocalDate.of(2023, 1, 20)).build();

        assertThat(validator.isValid(usuario)).isEqualTo(false);
    }

    @Test
    @DisplayName("Verificações com usuário invalido, endereço com caracteres especiais")
    public void testaVerificacoesComUsuarioInvalido4() {
        Usuario usuario = Usuario.builder()
                .email("teste@hotmail.com")
                .nome("Fulano dos Santos")
                .endereco("Rua Sempre Ali!, 22")
                .habilidades(Collections.emptyList())
                .dataNascimento(LocalDate.of(2023, 1, 20)).build();

        assertThat(validator.isValid(usuario)).isEqualTo(false);
    }
}
