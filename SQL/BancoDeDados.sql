# Banco de dados 
#CREATE DATABASE bibliotecawise;

# Cliente
CREATE TABLE cliente (
	idCliente INTEGER PRIMARY KEY auto_increment,
    nome VARCHAR(100),
    cpf VARCHAR(50),
    endereco VARCHAR(100),
    telefone VARCHAR(100)
);

# Livro
CREATE TABLE livro (
	idLivro INTEGER PRIMARY KEY auto_increment,
    anoPublicacao VARCHAR(20),
    titulo VARCHAR(100),
    autor VARCHAR(50),
    genero VARCHAR(20),
    disponivel BOOl
);

#Aluguel
CREATE TABLE aluguel(
	idAluguel INTEGER PRIMARY KEY auto_increment,
    idCliente INTEGER NOT NULL,
    idLivro INTEGER NOT NULL,
	dataAluguel VARCHAR(20),
    dataDevolucao VARCHAR(20),
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
    FOREIGN KEY (idLivro) REFERENCES livro(idLivro)
);