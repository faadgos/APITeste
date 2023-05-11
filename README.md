# Projeto de Cadastro de Usuário

Esse projeto tem como objetivo fornecer uma API para o cadastro de usuários com validações. 
A API foi desenvolvida em Java usando Spring Boot e utiliza uma base de dados MySQL para armazenar os usuários cadastrados.

## Pré-requisitos

Para executar esse projeto, você precisará ter instalado em sua máquina:

- Java JDK 11 ou posterior (disponível em https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)- Docker e Docker Compose (disponíveis em https://docs.docker.com/get-docker/ e https://docs.docker.com/compose/install/)## Configuração

Antes de executar o projeto, você precisará configurar o arquivo `application.properties` com as informações de conexão com o banco de dados MySQL. Para isso, abra o arquivo `src/main/resources/application.properties` e atualize as seguintes propriedades:

```
spring.datasource.url=jdbc:mysql://localhost:3306/meubanco
spring.datasource.username=meuusuario
spring.datasource.password=minhasenha
```

Substitua `meubanco`, `meuusuario` e `minhasenha` pelos valores correspondentes para a sua instância do MySQL.

## Execução

Para executar o projeto, siga os seguintes passos:

1. Clone o repositório para a sua máquina
2. Abra um terminal e navegue até o diretório do projeto
3. Execute o seguinte comando para construir a imagem Docker da aplicação: `docker build -t cadastro-usuario .` 
4. Execute o seguinte comando para iniciar os serviços Docker (MySQL e a aplicação): `docker-compose up -d`
5. Aguarde alguns segundos para que a aplicação seja iniciada e, em seguida, abra um navegador e acesse a URL `http://localhost:8081/v1/usuarios`

Caso tudo tenha sido configurado corretamente, você deverá ver uma lista vazia de usuários na tela.

## Uso da API

A API suporta as seguintes operações:

- GET `v1/usuarios`: Retorna a lista de todos os usuários cadastrados
- GET `v1/usuarios/{id}`: Retorna o usuário com o ID especificado
- POST `v1/usuarios`: Cria um novo usuário com base nas informações enviadas no corpo da requisição
- PUT `v1/usuarios/{id}`: Atualiza as informações do usuário com o ID especificado com base nas informações enviadas no corpo da requisição
- DELETE `v1/usuarios/{id}`: Exclui o usuário com o ID especificado

### Exemplo de requisição POST```
POST /usuarios

{
"nome": "Fulano de Tal",
"email": "fulano@example.com",
"dataNascimento": "1990-01-01",
"endereco": "Rua da Exemplo, 123",
"habilidades": [
"Java",
"Spring Boot"
]
}
### Exemplo de resposta GET```
GET /usuarios

[
{
"id": 1,
"nome": "Fulano de Tal",
"email": "fulano@example.com",
"dataNascimento": "1990-01-01",
"endereco": "Rua da Exemplo, 123",
"habilidades": [
"Java",
"Spring Boot"
]
}
]

## Licença

Esse projeto é distribuído sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais informações.