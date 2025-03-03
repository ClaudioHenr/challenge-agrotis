-- Criação da tabela Laboratorio
CREATE TABLE laboratorio (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255)
);

-- Criação da tabela Propriedade
CREATE TABLE propriedade (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Criação da tabela Pessoa
CREATE TABLE pessoa (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    data_inicial TIMESTAMP,
    data_final TIMESTAMP,
    id_propriedade BIGINT,
    id_laboratorio BIGINT,
    observacoes VARCHAR(255),
    FOREIGN KEY (id_propriedade) REFERENCES propriedade(id),
    FOREIGN KEY (id_laboratorio) REFERENCES laboratorio(id)
);
