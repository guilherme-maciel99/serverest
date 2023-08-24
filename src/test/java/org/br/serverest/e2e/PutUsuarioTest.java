package org.br.serverest.e2e;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.br.serverest.model.Usuario;
import org.br.serverest.stubs.UsuarioStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PutUsuarioTest {
    private static final String url = "https://serverest.dev";
    private UsuarioStub usuarioStub;

    @BeforeEach
    void setup(){
        RestAssured.baseURI = url;
        usuarioStub = new UsuarioStub();
    }

    @Test
    @DisplayName("PUT - Deve atualizar um usuário existente ou cadastrar novo usuário")
    void testAtualizarUsuario(){
        Usuario novoUsuario = usuarioStub.stubUsuario();
        Response responsePost = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(novoUsuario)
                .when()
                .post("/usuarios");

        String idUsuario = responsePost.jsonPath().getString("_id");

        Usuario usuarioAtualizado = usuarioStub.stubUsuarioPut();

        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(usuarioAtualizado)
                .when()
                .put("/usuarios/" + idUsuario);
        System.out.println(idUsuario);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("Registro alterado com sucesso"));

        Response responseGet = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios/" + idUsuario);

        assertEquals(200, responseGet.getStatusCode());
        assertEquals(usuarioAtualizado.getNome(), responseGet.jsonPath().getString("nome"));
        assertEquals(usuarioAtualizado.getEmail(), responseGet.jsonPath().getString("email"));
        assertEquals(usuarioAtualizado.getPassword(), responseGet.jsonPath().getString("password"));
        assertEquals(usuarioAtualizado.getAdministrador(), responseGet.jsonPath().getString("administrador"));
    }

    @Test
    @DisplayName("PUT - Deve retornar erro ao tentar cadastrar um email já cadastrado")
    void testErroEmailDuplicado() {
        Usuario usuario1 = usuarioStub.stubUsuario();
        Response responsePost1 = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(usuario1)
                .when()
                .post("/usuarios");
        assertEquals(201, responsePost1.getStatusCode());

        Usuario usuario2 = usuarioStub.stubUsuario();
        usuario2.setEmail(usuario1.getEmail());
        Response responsePost2 = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(usuario2)
                .when()
                .post("/usuarios");
        assertEquals(400, responsePost2.getStatusCode());

        String responseBody = responsePost2.getBody().asString();
        assertTrue(responseBody.contains("Este email já está sendo usado"));
    }
}
