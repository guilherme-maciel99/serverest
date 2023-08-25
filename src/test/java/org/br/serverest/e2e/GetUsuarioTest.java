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


public class GetUsuarioTest {

    private static final String url = "https://serverest.dev";
    private UsuarioStub usuarioStub;

    @BeforeEach
    void setup(){
        RestAssured.baseURI = url;
        usuarioStub = new UsuarioStub();
    }

    @Test
    @DisplayName("GET - Deve retornar uma lista contendo os usuários")
    void testRetornarUsuarios(){
        Response response = given()
                .when()
                .get("/usuarios");
        assertEquals(200, response.getStatusCode());

        String email = response.jsonPath().getString("usuarios[0].email");
        String password = response.jsonPath().getString("usuarios[0].password");

        assertFalse(email.isEmpty());
        assertFalse(password.isEmpty());

    }

    @Test
    @DisplayName("GET - Deve retornar erro ao acessar um endpoint errado")
    void testErroListarUsuarios() {
        Response response = given()
                .baseUri(url)
                .when()
                .get("/usuarios/endpoint-inexistente"); // Endpoint inválido

        assertEquals(400, response.getStatusCode()); // Verificar código de erro esperado
    }

    @Test
    @DisplayName("GET - Deve buscar um usuário por ID")
    void testBuscarUsuarioPorId() {
        Usuario novoUsuario = usuarioStub.stubUsuario();

        Response responsePost = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(novoUsuario)
                .when()
                .post("/usuarios");


        String idUsuario = responsePost.jsonPath().getString("_id");

        Response response = given()
                .when()
                .get("/usuarios/" + idUsuario);
        assertEquals(200, response.getStatusCode());

        String email = response.jsonPath().getString("email");
        String senha = response.jsonPath().getString("password");
        String id = response.jsonPath().getString("_id");

        assertEquals(novoUsuario.getEmail(), email);
        assertEquals(novoUsuario.getPassword(), senha);
        assertEquals(idUsuario, id);
    }
}
