package org.br.serverest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String nome;
    private String email;
    private String password;
    private String administrador;
}
