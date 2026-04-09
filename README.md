# TI2 - Cadastro de Produtos com Spark e PostgreSQL

Este projeto é um exemplo de um backend Java usando Spark, Maven e PostgreSQL, com um formulário HTML para cadastrar e listar produtos.

## Requisitos
- Java 17
- Maven
- PostgreSQL
- Eclipse (pode importar como projeto Maven)

## Configuração do banco de dados
1. Crie um banco de dados PostgreSQL, por exemplo `ti2db`.
2. Ajuste os parâmetros na classe `ProductDao` se necessário.
3. Usuário e senha padrão usados no projeto:
   - usuário: `postgres`
   - senha: `1234`

## Execução
### Usando Eclipse
1. Importe o projeto como Maven existente.
2. Execute a classe `com.example.ti2.ProductApp`.
3. Abra o navegador em `http://localhost:4567/`.

### Usando Maven no terminal
1. No diretório do projeto, execute:
   ```sh
   mvn clean package
   mvn exec:java
   ```
2. Acesse `http://localhost:4567/` no navegador.

## Funcionalidades
- Formulário HTML para cadastrar produto.
- Backend Spark que recebe o cadastro via POST.
- Lista de produtos exibida em HTML usando API REST.
- Persistência em PostgreSQL.
