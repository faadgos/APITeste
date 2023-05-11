package com.APITeste.validator;

import com.APITeste.models.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

@Component
public class UserValidator {
    private static final Integer IDADE_MINIMA = 18;

    private final List<String> allowedProviders = Arrays.asList("gmail.com", "hotmail.com", "outlook.com", "yahoo.com");

    public boolean isValid(Usuario user) {
        if (user.getEmail() == null) {
            return false;
        }
        String[] parts = user.getEmail().split("@");
        if (parts.length != 2) {
            return false;
        }
        if (!user.getNome().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
            return false;
        }
        if (!user.getEndereco().matches("^[a-zA-Z0-9,\\s]+$")) {
            return false;
        }
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataNascimento = user.getDataNascimento();
        if (!diferencaMinima(dataAtual, dataNascimento, IDADE_MINIMA)) {
            return false;
        }

        String provider = parts[1].toLowerCase();

        return allowedProviders.contains(provider);
    }

    public static boolean diferencaMinima(LocalDate data1, LocalDate data2, int anos) {
        Period periodo = Period.between(data1, data2);
        int idade = Math.abs(periodo.getYears());
        return idade >= anos;
    }
}