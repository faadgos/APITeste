package com.APITeste.controller;

import com.APITeste.models.Usuario;
import com.APITeste.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController()
@Slf4j
@RequestMapping(value = "/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> getAllUsers() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = usuarioService.getUserById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping()
    public ResponseEntity<?> newUser(@Valid @RequestBody Usuario user) {
        Usuario usuario = usuarioService.newUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @Valid @RequestBody Usuario usuarioAtualizado) {
        Usuario usuarioAtualizadoSalvo = usuarioService.updateUser(id, usuarioAtualizado);
        return ResponseEntity.ok(usuarioAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
