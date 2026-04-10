# 🛒 Spring Sales API

API REST desenvolvida com **Spring Boot** para gerenciamento de um sistema de vendas, incluindo clientes, produtos, pedidos e itens de pedido.

---

## Sobre o projeto

Este projeto simula um sistema de vendas real, permitindo operações completas de CRUD e relacionamento entre entidades.

A aplicação foi construída seguindo práticas de desenvolvimento backend, com foco em organização, escalabilidade e segurança.

---

## Funcionalidades

✔ Cadastro de clientes
✔ Cadastro de produtos
✔ Criação de pedidos
✔ Associação de itens aos pedidos
✔ Cálculo automático do valor total do pedido
✔ Relacionamentos entre entidades (JPA)
✔ Tratamento de exceções

---

## Arquitetura

O projeto segue uma arquitetura em camadas:

```
controller → recebe requisições HTTP
service → regras de negócio
repository → acesso ao banco de dados
entity → representação das tabelas
```

---

## Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Docker
* Maven

---

## Como rodar o projeto

### 🔹 1. Clonar o repositório

```bash
git clone https://github.com/Joao-Pedro-A-Miguel/spring-sales-api.git
cd spring-sales-api
```

---

### 🔹 2. Configurar variáveis de ambiente

Crie um arquivo na raiz do projeto:

```
.env.properties
```

Adicione:

```env
DB_USER=root
DB_PASSWORD=sua_senha_aqui
DB_NAME=salesapi
DB_PORT=3306
```

---

### 🔹 3. Subir o banco com Docker

```bash
docker-compose up -d
```

---

### 🔹 4. Rodar a aplicação

```bash
mvn spring-boot:run
```

---

## Segurança

O projeto utiliza variáveis de ambiente para proteger dados sensíveis como credenciais do banco de dados.

✔ O arquivo `.env.properties` está no `.gitignore`
✔ Nenhuma senha é exposta no código

---

## Endpoints da API

### Clientes

* `GET /clientes` → Lista todos os clientes
* `POST /clientes` → Cadastra um novo cliente

---

### Produtos

* `GET /produtos` → Lista todos os produtos
* `POST /produtos` → Cadastra um novo produto

---

### Pedidos

* `GET /pedidos` → Lista todos os pedidos
* `POST /pedidos` → Cria um novo pedido

---

### Itens do Pedido

* Associação entre pedidos e produtos
* Controle de quantidade e preço por item

---

## Banco de dados

O sistema utiliza MySQL com as seguintes entidades principais:

* Cliente
* Produto
* Pedido
* ItemPedido

Relacionamentos:

* Um cliente possui vários pedidos
* Um pedido possui vários itens
* Um item está associado a um produto

---

## Estrutura do projeto

```
src/main/java/com/pedro/salesapi

controller/
service/
repository/
entity/
dto/
```

---

## Boas práticas aplicadas

✔ Separação em camadas
✔ Uso de DTOs
✔ Relacionamentos bem definidos com JPA
✔ Uso de variáveis de ambiente (.env)
✔ Código limpo e organizado

---

## Melhorias futuras

* Validação com Bean Validation
* Tratamento global de exceções (@ControllerAdvice)
* Autenticação e autorização (JWT)
* Documentação com Swagger
* Deploy em nuvem (AWS / Railway / Render)

---

## Autor

**João Pedro**

---

## Contato

* GitHub: https://github.com/Joao-Pedro-A-Miguel

---
