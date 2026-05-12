# Sales API

API REST desenvolvida com Java + Spring Boot para gerenciamento de vendas, clientes, produtos e pedidos.

O projeto possui autenticação JWT, controle de acesso por perfil de usuário, validações, tratamento global de erros e integração com MySQL.

---

# Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Swagger / OpenAPI
* Lombok

---

# Funcionalidades

## Autenticação

* Cadastro de usuários
* Login com JWT
* Senha criptografada com BCrypt
* Controle de acesso por perfil

## Clientes

* Criar cliente
* Listar clientes
* Buscar cliente por ID
* Atualizar cliente
* Deletar cliente

## Produtos

* Criar produto
* Listar produtos
* Buscar produto por ID
* Atualizar produto
* Deletar produto

## Pedidos

* Criar pedido
* Listar pedidos
* Buscar pedido por ID
* Cálculo automático do valor total
* Desconto automático do estoque

---

# Controle de acesso

## DONO

Pode acessar:

* /admin/**
* /clientes/**
* /produtos/**
* /pedidos/**
* /gerente/**

## GERENTE

Pode acessar:

* /clientes/**
* /produtos/**
* /pedidos/**
* /gerente/**

## CLIENTE

Usuário autenticado com acesso limitado.

---

# Segurança

O projeto utiliza:

* JWT para autenticação
* BCrypt para criptografia de senha
* Spring Security para autorização
* Rotas protegidas por roles

---

# Estrutura do projeto

```bash
src/main/java/com/pedro/salesapi
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
```

---

# Configuração do ambiente

## Clone o projeto

```bash
git clone https://github.com/Joao-Pedro-A-Miguel/spring-sales-api.git
```

---

# Configuração do .env

Crie um arquivo `.env` na raiz do projeto:

```env
DB_PORT=3306
DB_NAME=salesapi
DB_USER=root
DB_PASSWORD=sua_senha

JWT_SECRET=MinhaChaveSuperSecretaMinhaChaveSuperSecreta123
```

---

# application.properties

```properties
spring.application.name=salesapi

spring.datasource.url=jdbc:mysql://localhost:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.config.import=optional:file:.env[.properties]

jwt.secret=${JWT_SECRET}
```

---

# Como executar o projeto

## Instalar dependências

```bash
mvn clean install
```

## Executar aplicação

```bash
mvn spring-boot:run
```

---

# Swagger

Após iniciar a aplicação:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# Endpoints principais

## Autenticação

### Registrar usuário

```http
POST /auth/register
```

### Login

```http
POST /auth/login
```

---

## Clientes

```http
GET /clientes
POST /clientes
PUT /clientes/{id}
DELETE /clientes/{id}
```

---

## Produtos

```http
GET /produtos
POST /produtos
PUT /produtos/{id}
DELETE /produtos/{id}
```

---

## Pedidos

```http
GET /pedidos
POST /pedidos
GET /pedidos/{id}
```

---

# Validações implementadas

* CPF inválido
* Email inválido
* Produto duplicado
* Estoque insuficiente
* Quantidade menor ou igual a zero
* Pedido sem itens
* Cliente inexistente
* Produto inexistente

---

# Tratamento global de erros

O projeto possui `GlobalExceptionHandler` para retornar mensagens amigáveis da API.

Exemplo:

```json
{
  "erro": "Produto não encontrado"
}
```

---

# Banco de dados

Tabelas principais:

* clientes
* produtos
* pedidos
* itens_pedido
* usuarios

---

# Melhorias futuras

* Testes unitários
* Paginação
* Deploy
* Docker
* Refresh Token
* Logs
* CI/CD

---

# Autor

João Pedro
