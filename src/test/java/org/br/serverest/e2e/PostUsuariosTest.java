package org.br.serverest.e2e;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.br.serverest.model.Usuario;
import org.br.serverest.stubs.UsuarioStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostUsuariosTest {

    private final String url = "https://serverest.dev";

    private UsuarioStub usuarioStub = new UsuarioStub();

    @Test
    @DisplayName("POST - Deve cadastrar um novo usuário")
    void testCadastrarNovoUsuario() {
        Usuario novoUsuario = usuarioStub.stubUsuario();

        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(novoUsuario)
                .when()
                .post("/usuarios");

        assertEquals(201, response.getStatusCode());

        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("Cadastro realizado com sucesso"));

        usuarioStub.setIdUsuario(response.jsonPath().getString("_id"));
        System.out.println(usuarioStub.getIdUsuario());
            }

    @Test
    @DisplayName("POST - Deve retornar erro ao cadastrar um usuário com email existente")
    void testCadastrarUsuarioComEmailRepetido(){

        Usuario usuario = usuarioStub.stubUsuarioComEmailRepetido();
        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios");

        assertEquals(400, response.getStatusCode());
    }
}
