# TaskFlow

Sistema de gestão de tarefas e projetos (kanban) - Projeto Integrador, SENAC Santo Amaro.

## Como rodar

**Back-end** (Java 17+, na pasta raiz do projeto):

```
./mvnw spring-boot:run
```

Sobe em `http://localhost:8080`. O banco é o H2 (arquivo `data/taskflowdb`) e os usuários de teste são criados na primeira execução.

**Front-end** (Node.js, na pasta raiz do projeto):

```
npm install
npm start
```

Abre em `http://localhost:4200`.

## Login de teste

As senhas são salvas criptografadas (BCrypt) no banco. Novos usuários: botão "Criar conta" na tela de login.

| E-mail | Senha |
|---|---|
| ana@taskflow.com | 123456 |
| bruno@taskflow.com | 123456 |
| carla@taskflow.com | 123456 |
| vinnicius@taskflow.com | 123456 |
| luciano@taskflow.com | 123456 |
| leonardo@taskflow.com | 123456 |
| saimon@taskflow.com | 123456 |

## Rotas do back

| Método | Rota | Descrição |
|---|---|---|
| POST | `/auth/cadastro` | Recebe `{ "nome", "email", "senha" }`. Cria o usuário (201), 400 se faltar campo ou 409 se o e-mail já existir. |
| POST | `/auth/login` | Recebe `{ "email", "senha" }`. Devolve `{ "id", "nome", "email" }` (200) ou 401 se estiver errado. |
| GET/POST/PUT/DELETE | `/api/atividades` | CRUD de atividades. |

Console do banco: `http://localhost:8080/h2-console` (URL `jdbc:h2:file:./data/taskflowdb`, usuário `sa`, senha vazia).

## Pastas do back

`src/main/java/backend/backend/` com `model`, `repository`, `controller`, `dto` e `config` (DataLoader). Configuração em `src/main/resources/application.properties`.
