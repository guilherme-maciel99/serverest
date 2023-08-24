package org.br.serverest.stubs;

import org.br.serverest.model.Usuario;

import java.util.UUID;

public class UsuarioStub {

    private String idUsuario;
    String email = gerarEmailUnico();

    public Usuario stubUsuario(){
        Usuario user = new Usuario(
                "Guilherme Maciel",
                email,
                "testesenha",
                "true");
        return user;
    }
    public Usuario stubUsuarioComEmailRepetido(){
        Usuario user = new Usuario(
                "Guilherme Maciel",
                "gui.almaciel@gmail.com",
                "testesenha",
                "true");
        return user;
    }

    private String gerarEmailUnico(){
        UUID uuid = UUID.randomUUID();
        return "guilherme_" + uuid + "@exemplo.com";
    }

    public String setIdUsuario(String idUsuario) {
        return this.idUsuario = idUsuario;
    }
    public String getIdUsuario() {
        return this.idUsuario;
    }

}
