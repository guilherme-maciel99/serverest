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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteUsuarioTest {
        private static final String url = "https://serverest.dev";
        private UsuarioStub usuarioStub;

        @BeforeEach
        void setup() {
            RestAssured.baseURI = url;
            usuarioStub = new UsuarioStub();
        }

        @Test
        @DisplayName("DELETE - Deve excluir um usuário existente")
        void testExcluirUsuario() {
            Usuario novoUsuario = usuarioStub.stubUsuario();
            Response responsePost = given()
                    .baseUri(url)
                    .contentType(ContentType.JSON)
                    .body(novoUsuario)
                    .when()
                    .post("/usuarios");

            assertEquals(201, responsePost.getStatusCode());
            String idUsuario = responsePost.jsonPath().getString("_id");

            Response responseDelete = given()
                    .baseUri(url)
                    .contentType(ContentType.JSON)
                    .when()
                    .delete("/usuarios/" + idUsuario);

            assertEquals(200, responseDelete.getStatusCode());
            assertTrue(responseDelete.getBody().asString().contains("Registro excluído com sucesso"));


        }

    }
