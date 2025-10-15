CREATE TABLE Empresa (
	id_empresa SERIAL PRIMARY KEY,
	cnpj VARCHAR(18) NOT NULL UNIQUE,
	nome_emp VARCHAR(100) NOT NULL,
	email_corporativo VARCHAR(100) NOT NULL UNIQUE,
	desc_empresa TEXT,
	pais VARCHAR(50),
	cep VARCHAR(15),
	senha VARCHAR(255) NOT NULL
);

CREATE TABLE Candidato (
	id_candidato SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	sobrenome VARCHAR(50) NOT NULL,
	cpf VARCHAR(14) NOT NULL UNIQUE,
	email VARCHAR(100) NOT NULL UNIQUE,
	pais VARCHAR(50),
	cep VARCHAR(15),
	data_nasc DATE,
	desc_pessoal TEXT,
	senha VARCHAR(255) NOT NULL
);

CREATE TABLE Competencia (
	id_competencia SERIAL PRIMARY KEY,
	nome_competencia VARCHAR(100) NOT NULL
);

CREATE TABLE Vaga(
	id_vaga SERIAL PRIMARY KEY,
	id_empresa INT NOT NULL REFERENCES Empresa(id_empresa) ON DELETE CASCADE,
	nome_vaga VARCHAR(100) NOT NULL,
	desc_vaga TEXT,
	localizacao VARCHAR(100)
);

CREATE TABLE Candidato_competencia(
	id_candidato INT NOT NULL REFERENCES Candidato(id_candidato) ON DELETE CASCADE,
	id_competencia INT NOT NULL REFERENCES Competencia(id_competencia) ON DELETE CASCADE,
	PRIMARY KEY (id_candidato,id_competencia)
);

CREATE TABLE Vaga_competencia(
id_vaga INT NOT NULL REFERENCES Vaga(id_vaga) ON DELETE CASCADE,
id_competencia INT NOT NULL REFERENCES Competencia(id_competencia) ON DELETE CASCADE,
PRIMARY KEY (id_vaga, id_competencia)
);

INSERT INTO Empresa (cnpj, nome_emp, email_corporativo, desc_empresa, pais, senha) VALUES
('12.345.678/0001-90', 'Pastelsoft', 'contato@pastelsoft.com', 'Desenvolvimento de softwares ERP', 'Brasil', 'senha123'),
('98.765.432/0001-10', 'TechByte', 'contato@techbyte.com', 'Consultoria em TI e inovação', 'Brasil', 'senha456'),
('11.222.333/0001-44', 'CodeLab', 'contato@codelab.com', 'Desenvolvimento de aplicativos', 'Brasil', 'senha789'),
('55.666.777/0001-88', 'SoftDesign', 'contato@softdesign.com', 'Design e soluções web', 'Brasil', 'senha321'),
('99.888.777/0001-66', 'DataFlow', 'contato@dataflow.com', 'Consultoria em dados e BI', 'Brasil', 'senha654');

INSERT INTO Candidato (cpf, nome, sobrenome, email, pais, cep, data_nasc, desc_pessoal, senha) VALUES
('123.456.789-00', 'Sandubinha', 'Silva', 'sandubinha@gmail.com', 'Brasil', '58000-000', '1990-01-01', 'Apaixonado por programação', 'senha123'),
('234.567.890-11', 'Maria', 'Oliveira', 'mariao@gmail.com', 'Brasil', '58000-001', '1992-02-02', 'Desenvolvedora Front-end', 'senha234'),
('345.678.901-22', 'João', 'Pereira', 'joaop@gmail.com', 'Brasil', '58000-002', '1988-03-03', 'Especialista em banco de dados', 'senha345'),
('456.789.012-33', 'Ana', 'Costa', 'anac@gmail.com', 'Brasil', '58000-003', '1995-04-04', 'Designer UX/UI', 'senha456'),
('567.890.123-44', 'Pedro', 'Santos', 'pedros@gmail.com', 'Brasil', '58000-004', '1991-05-05', 'Engenheiro de dados', 'senha567');
