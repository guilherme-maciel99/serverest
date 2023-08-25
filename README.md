# Descrição
Este repositório contém testes automatizados para a API ServeRest, com o objetivo de verificar o correto funcionamento dos endpoints e cenários de uso. A automação foi desenvolvida utilizando Java e o framework RestAssured.

# Pré-Requisitos
- Java JDK 17 instalado
- Gradle instalado
  
# Como Executar

1. Certifique-se de ter o Java JDK instalado em sua máquina.
2. Clone este repositório para o seu ambiente local.
3. Abra o projeto em sua IDE de preferência (por exemplo, IntelliJ, Eclipse).
4. Certifique-se de que as dependências do projeto foram baixadas.
5. Navegue até a classe de teste, por exemplo, TesteApiServeRest.java.
6. Execute a classe de teste. Isso pode ser feito clicando com o botão direito do mouse na classe e selecionando a opção "Run" ou "Run as JUnit Test", dependendo da sua IDE.

# Versões das Dependências
- RestAssured: 5.3.0

# Cenários de Teste
Os testes automatizados abrangem os seguintes cenários:

## Teste de Listagem de Usuários

- Verifica se a listagem de usuários é retornada corretamente.

- Verifica se os campos importantes (nome, email, senha) estão presentes e não vazios.

## Teste de Cadastro de Novo Usuário

- Verifica se um novo usuário é cadastrado com sucesso.

- Verifica se a mensagem de sucesso é retornada na resposta.

- Verifica se não é permitido cadastrar um usuário com email já utilizado.

## Teste de Edição ou Cadastro de Usuário Existente

- Verifica se um usuário existente pode ser editado com sucesso.

- Verifica se a mensagem de sucesso é retornada na resposta.

- Caso o usuário não exista, verifica se um novo usuário é cadastrado com sucesso.

- Caso seja um novo cadastro, verifica se a mensagem de sucesso é retornada na resposta.

## Teste de Exclusão de Usuário

- Verifica se um usuário existente pode ser excluído com sucesso.

- Verifica se a mensagem de sucesso é retornada na resposta.
