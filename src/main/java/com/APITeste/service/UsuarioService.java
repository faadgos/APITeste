package com.APITeste.service;

import com.APITeste.models.Usuario;
import com.APITeste.repositories.UsuarioRepository;
import com.APITeste.validator.UserValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UserValidator userValidator;

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUserById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario newUser(Usuario user) {
        if(userValidator.isValid(user)) usuarioRepository.save(user);

        return user;
    }

    @Transactional
    public Usuario updateUser(Long id, @Valid Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
        usuario.setEndereco(usuarioAtualizado.getEndereco());
        usuario.setHabilidades(usuarioAtualizado.getHabilidades());

        return usuarioRepository.save(usuario);
    }

    public void deleteUser(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }

        Usuario usuario = usuarioOptional.get();
        usuarioRepository.delete(usuario);
    }
}
