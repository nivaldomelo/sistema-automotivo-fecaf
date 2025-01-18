
-- Criação da tabela de marcas
CREATE TABLE automotivo.marca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Criação da tabela de veículos
CREATE TABLE automotivo.veiculo (
    id SERIAL PRIMARY KEY,
    modelo VARCHAR(255) NOT NULL,
    cor VARCHAR(50),
    ano INT NOT NULL,
    preco NUMERIC(15, 2) NOT NULL,
    quilometragem INT,
    disponivel BOOLEAN DEFAULT TRUE,
    marca_id INT NOT NULL,
    CONSTRAINT fk_marca FOREIGN KEY (marca_id) REFERENCES automotivo.marca (id) ON DELETE CASCADE
);

-- Inserção de marcas
INSERT INTO automotivo.marca (nome) VALUES ('Toyota'), ('Ford'), ('Chevrolet');

-- Inserção de veículos
INSERT INTO automotivo.veiculo (modelo, cor, ano, preco, quilometragem, disponivel, marca_id)
VALUES
('Corolla', 'Preto', 2020, 90000.00, 15000, TRUE, 1),
('Focus', 'Branco', 2019, 75000.00, 30000, TRUE, 2),
('Cruze', 'Prata', 2021, 95000.00, 10000, FALSE, 3);


