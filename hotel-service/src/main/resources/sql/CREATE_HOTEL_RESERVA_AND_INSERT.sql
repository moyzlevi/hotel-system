CREATE TABLE hoteis (
    id SERIAL PRIMARY KEY,
    numero_quartos INT NOT NULL,
    localizacao VARCHAR(255) NOT NULL,
    preco_diaria DECIMAL(10, 2) NOT NULL,
    comodidades TEXT,
    media_avaliacao DECIMAL(2, 1)
);

CREATE TABLE reservas_quartos (
    id SERIAL PRIMARY KEY,
    hotel_id INT REFERENCES hoteis(id) ON DELETE CASCADE,
    nome_hospede VARCHAR(255) NOT NULL,
    contato VARCHAR(255),
    detalhes_pagamento TEXT,
    inicio_reserva DATE NOT NULL,
    fim_reserva DATE NOT NULL,
    status VARCHAR(255) DEFAULT 'RESERVADO'
);


INSERT INTO hoteis (numero_quartos, localizacao, preco_diaria, comodidades, media_avaliacao)
VALUES (100, 'São Paulo, SP', 250.00, 'Wi-Fi, Piscina, Academia', 4.5);

INSERT INTO reservas_quartos (hotel_id, nome_hospede, contato, detalhes_pagamento, inicio_reserva, fim_reserva)
VALUES (1, 'João da Silva', 'joao@example.com', 'Cartão de Crédito', '2023-10-01', '2023-10-07');
