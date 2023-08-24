package org.br.serverest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Usuario {
    private String nome;
    private String email;
    private String password;
    private String administrador;
}
