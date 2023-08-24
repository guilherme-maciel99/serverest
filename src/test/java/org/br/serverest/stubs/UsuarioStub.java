package org.br.serverest.stubs;

import org.br.serverest.model.Usuario;

import java.util.UUID;

public class UsuarioStub {

    private String idUsuario;
    private String nome;
    private String email;
    private String password;
    private String administrador;
    String gerar = gerarEmailUnico();

    public Usuario stubUsuario(){
        Usuario user = new Usuario(
                "Guilherme Maciel",
                gerar,
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
    public Usuario stubUsuarioPut(){
        Usuario user = new Usuario(
                "Guilherme Maciel",
                gerar,
                "novasenha",
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}
