package com.APITeste.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Component
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @Past
    @Min(value = 18, message = "A idade mínima é de 18 anos")
    private LocalDate dataNascimento;

    @NotBlank
    @Size(max = 200)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
    private String endereco;

//    @ElementCollection
    private List<@NotBlank String> habilidades;

}