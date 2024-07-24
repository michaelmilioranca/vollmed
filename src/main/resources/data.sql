-- Médico 1 - Ortopedia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dr. João da Silva', 'joao.silva@example.com', '123456', 'ORTOPEDIA', 'Rua dos Pinheiros', 'Jardins', '12345-678', 'Sala 101', '123', 'SP', 'São Paulo', '(11) 9876-5432', 1);

-- Médico 2 - Ortopedia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dra. Maria Santos', 'maria.santos@example.com', '654321', 'ORTOPEDIA', 'Av. Brasil', 'Copacabana', '54321-098', 'Bloco A', '456', 'RJ', 'Rio de Janeiro', '(21) 8765-4321', 1);

-- Médico 3 - Ortopedia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dr. Pedro Oliveira', 'pedro.oliveira@example.com', '987654', 'ORTOPEDIA', 'Rua das Palmeiras', 'Ipanema', '76543-210', NULL, '789', 'RJ', 'Rio de Janeiro', '(21) 7654-3210', 1);

-- Médico 4 - Cardiologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dr. Carlos Lima', 'carlos.lima@example.com', '234567', 'CARDIOLOGIA', 'Av. Paulista', 'Bela Vista', '98765-432', 'Apto 301', '2345', 'SP', 'São Paulo', '(11) 8765-4321', 1);

-- Médico 5 - Cardiologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dra. Ana Pereira', 'ana.pereira@example.com', '876543', 'CARDIOLOGIA', 'Rua das Flores', 'Centro', '34567-890', NULL, '567', 'RJ', 'Rio de Janeiro', '(21) 6543-2109', 1);

-- Médico 6 - Cardiologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dr. Gustavo Santos', 'gustavo.santos@example.com', '210987', 'CARDIOLOGIA', 'Rua do Sol', 'Barra da Tijuca', '54321-987', 'Casa 2', '1234', 'RJ', 'Rio de Janeiro', '(21) 5432-1098', 1);

-- Médico 7 - Ginecologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dra. Fernanda Costa', 'fernanda.costa@example.com', '345678', 'GINECOLOGIA', 'Av. das Águias', 'Copacabana', '67890-123', 'Apto 304', '890', 'RJ', 'Rio de Janeiro', '(21) 6543-2109', 1);

-- Médico 8 - Ginecologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dr. Lucas Oliveira', 'lucas.oliveira@example.com', '901234', 'GINECOLOGIA', 'Rua das Oliveiras', 'Leblon', '32109-876', 'Apto 201', '8765', 'RJ', 'Rio de Janeiro', '(21) 2109-8765', 1);

-- Médico 9 - Ginecologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dra. Patrícia Souza', 'patricia.souza@example.com', '567890', 'GINECOLOGIA', 'Av. Paulista', 'Itaim Bibi', '76543-210', 'Apto 405', '8765', 'SP', 'São Paulo', '(11) 2109-8765', 1);

-- Médico 10 - Dermatologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dr. Marcos Oliveira', 'marcos.oliveira@example.com', '543210', 'DERMATOLOGIA', 'Rua dos Jasmins', 'Botafogo', '65432-109', 'Bloco D', '5432', 'RJ', 'Rio de Janeiro', '(21) 1098-7654', 1);

-- Médico 11 - Dermatologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dra. Renata Martins', 'renata.martins@example.com', '432109', 'DERMATOLOGIA', 'Av. Brasil', 'Laranjeiras', '43210-987', 'Casa 3', '2109', 'RJ', 'Rio de Janeiro', '(21) 0987-6543', 1);

-- Médico 12 - Dermatologia
INSERT INTO medico (nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
VALUES ('Dr. Thiago Santos', 'thiago.santos@example.com', '678901', 'DERMATOLOGIA', 'Rua das Flores', 'Centro', '54321-098', 'Apto 501', '0987', 'SP', 'São Paulo', '(11) 9876-5432', 1);

-- Pacientes
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('João Pereira', 'joao.pereira@example.com', '987.654.321-00', '(21) 9988-7766', 'Rua dos Jacarandás', 'Centro', '23456-789', 'Casa', '456', 'RJ', 'Rio de Janeiro', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Maria Oliveira', 'maria.oliveira@example.com', '456.789.123-09', '(31) 9123-4567', 'Avenida Brasil', 'Bairro Alto', '34567-890', 'Bloco B', '789', 'MG', 'Belo Horizonte', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Carlos Souza', 'carlos.souza@example.com', '321.654.987-22', '(41) 9345-6789', 'Rua das Palmeiras', 'Zona Sul', '45678-901', 'Apto 102', '321', 'PR', 'Curitiba', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Fernanda Lima', 'fernanda.lima@example.com', '654.321.987-44', '(51) 9567-8901', 'Rua dos Ipês', 'Bela Vista', '56789-012', 'Bloco A', '654', 'RS', 'Porto Alegre', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Ricardo Alves', 'ricardo.alves@example.com', '789.123.456-55', '(61) 9789-0123', 'Avenida das Nações', 'Asa Norte', '67890-123', 'Casa', '987', 'DF', 'Brasília', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Patrícia Fernandes', 'patricia.fernandes@example.com', '159.753.486-33', '(71) 9988-1122', 'Rua das Acácias', 'Pituba', '12346-789', 'Apto 203', '159', 'BA', 'Salvador', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('André Ribeiro', 'andre.ribeiro@example.com', '357.951.258-66', '(81) 9765-4321', 'Avenida Boa Viagem', 'Boa Viagem', '98765-432', 'Bloco C', '357', 'PE', 'Recife', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Juliana Rocha', 'juliana.rocha@example.com', '258.963.147-88', '(91) 9123-7890', 'Rua das Mangueiras', 'Cidade Velha', '65432-109', 'Apto 405', '258', 'PA', 'Belém', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Gustavo Martins', 'gustavo.martins@example.com', '369.258.147-00', '(51) 9567-1234', 'Avenida Independência', 'Centro Histórico', '54321-098', 'Casa', '369', 'RS', 'Porto Alegre', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Mariana Castro', 'mariana.castro@example.com', '147.258.369-11', '(83) 9345-6789', 'Rua João Pessoa', 'Tambaú', '87654-321', 'Apto 506', '147', 'PB', 'João Pessoa', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Eduardo Silva', 'eduardo.silva@example.com', '123.789.456-22', '(41) 9988-7766', 'Avenida Paraná', 'Centro', '45678-123', 'Bloco D', '456', 'PR', 'Curitiba', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Renata Gomes', 'renata.gomes@example.com', '789.654.123-33', '(31) 9123-9876', 'Rua Amazonas', 'Savassi', '12345-654', 'Casa', '789', 'MG', 'Belo Horizonte', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Bruno Santos', 'bruno.santos@example.com', '951.753.852-44', '(21) 9345-6789', 'Rua do Lavradio', 'Lapa', '56789-321', 'Apto 202', '951', 'RJ', 'Rio de Janeiro', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Aline Dias', 'aline.dias@example.com', '753.951.456-55', '(11) 9567-1234', 'Avenida Paulista', 'Bela Vista', '78901-234', 'Bloco E', '753', 'SP', 'São Paulo', 1);
INSERT INTO paciente(nome, email, cpf, telefone, logradouro, bairro, cep, complemento, numero, uf, cidade, ativo) VALUES ('Lucas Vieira', 'lucas.vieira@example.com', '654.987.321-66', '(61) 9123-4567', 'Rua das Orquídeas', 'Asa Sul', '89012-345', 'Apto 307', '654', 'DF', 'Brasília', 1);

-- Usuario padrao para a request
insert into usuario values (1, 'ana.souza@voll.med', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');
