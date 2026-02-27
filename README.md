# 📚 Sistema de Biblioteca — API REST

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de uma biblioteca, com controle de livros, usuários e empréstimos. Banco de dados MySQL rodando via **Docker** e endpoints testados com **Postman**.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **JDBC** (acesso manual ao banco de dados)
- **MySQL** (containerizado com Docker)
- **Docker / Docker Compose**
- **Postman** (testes de endpoints)

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas, separando bem as responsabilidades:

```
src
└── main
    └── java
        └── com.weg.SistemaBiblioteca
            ├── controller    → Recebe as requisições HTTP (endpoints REST)
            ├── service       → Regras de negócio
            ├── dao           → Acesso ao banco de dados via JDBC
            ├── repository    → Interface de persistência
            ├── dto           → Objetos de transferência de dados
            ├── mapper        → Conversão entre entidades e DTOs
            └── model         → Entidades do domínio
```

---

## 🗄️ Banco de Dados

O banco MySQL é executado via **Docker**. Para subir o container:

```bash
docker run --name biblioteca-mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=biblioteca \
  -p 3306:3306 \
  -d mysql:latest
```

Ou utilize o `docker-compose.yml` se disponível no projeto:

```bash
docker-compose up -d
```

### Estrutura das tabelas

```sql
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE livro (
  id               BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo           VARCHAR(150) NOT NULL,
  autor            VARCHAR(100) NOT NULL,
  ano_publicacao   INT NOT NULL
);

CREATE TABLE usuario (
  id     BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome   VARCHAR(100) NOT NULL,
  email  VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE emprestimo (
  id               BIGINT AUTO_INCREMENT PRIMARY KEY,
  livro_id         BIGINT NOT NULL,
  usuario_id       BIGINT NOT NULL,
  data_emprestimo  DATE NOT NULL,
  data_devolucao   DATE,
  FOREIGN KEY (livro_id)   REFERENCES livro(id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
```

---

## 📡 Endpoints da API

### 📚 Livros — `/livros`

| Método   | Endpoint       | Descrição              |
|----------|----------------|------------------------|
| `POST`   | `/livros`      | Cadastrar novo livro   |
| `GET`    | `/livros`      | Listar todos os livros |
| `GET`    | `/livros/{id}` | Buscar livro por ID    |
| `PUT`    | `/livros/{id}` | Atualizar livro        |
| `DELETE` | `/livros/{id}` | Remover livro          |

### 👤 Usuários — `/usuarios`

| Método   | Endpoint          | Descrição                |
|----------|-------------------|--------------------------|
| `POST`   | `/usuarios`       | Cadastrar novo usuário   |
| `GET`    | `/usuarios`       | Listar todos os usuários |
| `GET`    | `/usuarios/{id}`  | Buscar usuário por ID    |
| `PUT`    | `/usuarios/{id}`  | Atualizar usuário        |
| `DELETE` | `/usuarios/{id}`  | Remover usuário          |

### 📖 Empréstimos — `/emprestimos`

| Método   | Endpoint                        | Descrição                     |
|----------|---------------------------------|-------------------------------|
| `POST`   | `/emprestimos`                  | Registrar novo empréstimo     |
| `GET`    | `/emprestimos`                  | Listar todos os empréstimos   |
| `GET`    | `/emprestimos/{id}`             | Buscar empréstimo por ID      |
| `PUT`    | `/emprestimos/{id}`             | Atualizar empréstimo          |
| `DELETE` | `/emprestimos/{id}`             | Excluir empréstimo            |
| `PUT`    | `/emprestimos/{id}/devolucao`   | Registrar devolução           |
| `GET`    | `/usuarios/{id}/emprestimos`    | Listar empréstimos do usuário |

---

## 🧠 Regras de Negócio

- ✅ Não é permitido emprestar um livro que já possui empréstimo ativo (sem `data_devolucao`)
- ✅ Não é permitido excluir um livro com empréstimo ativo
- ✅ A devolução é registrada atualizando o campo `data_devolucao` do empréstimo
