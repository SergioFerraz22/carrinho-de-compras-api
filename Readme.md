# Carrinho De Compras JPA + H2 API Restful

Este projeto demonstra uma API de um carrinho de compras que é armazenado em um banco de dados H2 em memória.

## Recursos Principais:
- Operações CRUD básicas para gerenciar itens no carrinho de compras.
- Suporte para paginação e filtragem de itens.
- Documentação da API disponível em JSON e interface interativa do Swagger UI.

## Pré-requisitos

- JDK 21
- Maven 3.6.3
- Spring Boot 3.2.2

## Instalação

Para executar o projeto, siga estas etapas:

1. Execute o comando mvn clean install para compilar o projeto, executar testes e instalar o pacote no repositório local Maven.
2. Depois de instalar as dependências, execute o projeto usando o comando mvn spring-boot:run.

## Uso

Para testar a aplicação, você pode acessar sua documentação em formato JSON em http://localhost:8080/api-docs e utilizar o Swagger UI em http://localhost:8080/swagger-ui/index.html.

Para acessar o banco de dados H2, certifique-se de ter as credenciais corretas, como nome de usuário e senha, configuradas no arquivo application.properties.

Para utilizar as operações CRUD básicas da API, você pode enviar solicitações HTTP para criar, recuperar, atualizar e excluir itens do carrinho de compras.