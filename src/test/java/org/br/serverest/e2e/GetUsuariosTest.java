package org.br.serverest.e2e;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class GetUsuariosTest {

    private final String url = "https://serverest.dev";


    @BeforeEach
    void init(){
        RestAssured.baseURI = url;
    }

    @Test
    @DisplayName("GET - Deve retornar uma lista contendo os usuários")
    void getUsuarios(){
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
}
