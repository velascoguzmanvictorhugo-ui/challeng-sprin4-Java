# Soul Up - Backend Java Spring Boot (Sprint 4)

## Integrantes - Nexus Elite (1TDSPY)
* Victor Hugo Velasco Guzman (RM 573173)
* Raphael Ferreira Tavares (RM 569767)
* Bryan Germano (RM 569716)
* Enzo Macedo (RM 569427)
* Vinicios Alves (RM 573002)

## Arquitetura do Projeto
* `controller`: Camada REST (`UsuarioController`, `GrupoController`, `DesafioController`)
* `service`: Regras de negócio (`UsuarioService`, `GrupoService`, `DesafioService`)
* `dominio`: Entidades e interfaces de repositório (`Validador`, `Grupo`, `DesafioPratico`)
* `dao`: Persistência JDBC com Oracle Database (`ConnectionFactory`, `UsuarioDAO`, `GrupoDAO`, `DesafioDAO`)
* `config`: Configuração global de CORS (`CorsConfig`)

## Tabela de Endpoints

| Recurso | Método | Descrição | Status de Sucesso |
|---|---|---|---|
| `/usuarios` | `GET` | Lista todos os usuários | `200 OK` |
| `/usuarios` | `POST` | Cadastra um novo usuário | `201 Created` |
| `/usuarios` | `PUT` | Atualiza dados de um usuário | `200 OK` |
| `/usuarios/{id}` | `DELETE` | Remove um usuário pelo ID | `204 No Content` |
| `/grupos` | `GET` | Lista todos os grupos | `200 OK` |
| `/grupos` | `POST` | Cria um novo grupo | `201 Created` |
| `/grupos` | `PUT` | Atualiza dados de um grupo | `200 OK` |
| `/grupos/{id}` | `DELETE` | Remove um grupo pelo ID | `204 No Content` |
| `/desafios` | `GET` | Lista todos os desafios | `200 OK` |
| `/desafios` | `POST` | Cadastra um novo desafio | `201 Created` |
| `/desafios` | `PUT` | Atualiza um desafio existente | `200 OK` |
| `/desafios/{id}` | `DELETE` | Remove um desafio pelo ID | `204 No Content` |

## Deploy
* URL da API na nuvem (Render): `https://challeng-sprin4-java.onrender.com`