-- Oracle: executar uma vez antes de publicar esta versao da API.
-- Nao atribui senhas a contas antigas e nao apaga dados existentes.
CREATE TABLE tb_soulup_credencial (
    id_usuario NUMBER PRIMARY KEY,
    senha_hash VARCHAR2(255) NOT NULL,
    CONSTRAINT fk_soulup_credencial_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario) ON DELETE CASCADE
);

CREATE TABLE tb_soulup_grupo_membro (
    id_grupo NUMBER NOT NULL,
    id_usuario NUMBER NOT NULL,
    PRIMARY KEY (id_grupo,id_usuario),
    CONSTRAINT fk_soulup_membro_grupo FOREIGN KEY (id_grupo) REFERENCES tb_grupo(id_grupo) ON DELETE CASCADE,
    CONSTRAINT fk_soulup_membro_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario) ON DELETE CASCADE
);
