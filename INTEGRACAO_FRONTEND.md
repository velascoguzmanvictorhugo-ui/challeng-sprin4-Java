# Correcao de autenticacao e membros dos grupos

Esta alteracao adiciona cadastro com senha, autenticacao por sessao HttpOnly e persistencia de membros. Foi preparada a partir da branch main, commit 107b64c689ccd649fc51350d87daf0e4f90bbde5.

## Antes de publicar

1. Fazer backup e revisar com o responsavel pelo Oracle a migracao `SoulUp_Menu/database/sprint4-auth-members.sql`. Ela cria duas tabelas novas com chaves estrangeiras e nao remove dados. Executar uma unica vez, antes do deploy; nao executar novamente sem conferir as tabelas existentes.
2. Executar `mvn test` em `SoulUp_Menu` (Java 17+ e Maven). Os testes de autenticacao usam mocks e nao acessam o Oracle. A migracao e as transacoes de cadastro e grupos ainda precisam ser verificadas em banco de homologacao.
3. Configurar `FRONTEND_ORIGINS` com a URL exata do front-end, sem barra final. Multiplas origens sao separadas por virgula. Usar `/api` no front-end para aproveitar o proxy e manter os cookies na mesma origem.
4. Em HTTPS, manter `SESSION_COOKIE_SECURE=true`. Somente para desenvolvimento HTTP, definir `SESSION_COOKIE_SECURE=false`.
5. Publicar a API corrigida e validar cadastro, login, `/auth/me`, logout e membros em homologacao antes da producao.

## Contrato

- `POST /usuarios`: `{ "nome": "...", "email": "...", "senha": "..." }`. Gera o ID em transacao e persiste apenas o hash PBKDF2 da senha. Retorna 201 com a conta sem a senha. Rejeita e-mail existente com 409.
- `POST /auth/login`: `{ "email": "...", "senha": "..." }`. Valida o hash e cria uma nova sessao. Senha incorreta retorna 401.
- `GET /auth/me`: retorna a conta da sessao atual, ou 401. O cliente nao pode autenticar apenas editando o armazenamento local.
- `POST /auth/logout`: invalida a sessao e retorna 204.
- `POST /grupos`: aceita `nome` e `membros: [{ "id": 123 }]`; grupo e vinculos sao persistidos em uma unica transacao. Retorna o ID efetivamente cadastrado. Membros inexistentes ou duplicados nao deixam grupo parcial no banco.
- `GET /grupos`: retorna os membros persistidos.
- `DELETE /usuarios?id=123` e `DELETE /grupos?id=123`: vinculam o parametro explicitamente.

Contas antigas sem registro em `tb_soulup_credencial` nao recebem senha automatica e nao podem ser assumidas apenas pelo e-mail. A recuperacao dessas contas exige um fluxo de verificacao de identidade separado.

As operacoes administrativas ja existentes na API ainda precisam de autorizacao propria no backend antes de uso com dados sensiveis. A senha da interface de funcionarios protege o endpoint Node, mas nao transforma os endpoints Java publicos em endpoints privados. Nao existe integracao remota de pareceres e ranking local neste patch.

Nao foram executadas alteracoes no banco de producao nem publicado deploy por este procedimento.
