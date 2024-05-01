# BibliotecaWise

Programa de alugueis de livros.

## Requerimentos

- JDK(Java SE Development Kit)
- Netbeans
- Xamppp
- Mysql Workbench(Opcional)

## Banco de Dados

O arquivo para gerar o banco de dados se encontra na pasta SQL/bacoDeDados.sql

## Tela Inicial

![image](https://github.com/PhaellZX/BibliotecaWise/assets/48337836/e375fd95-6a10-4fb7-84e2-11e8475956fe)

Contém os acesso das telas de cadastro de clientes e livros, e também a tela de alugar livros disponiveis.

## Tela Cliente

![image](https://github.com/PhaellZX/BibliotecaWise/assets/48337836/feefc08b-07a9-401c-9c9b-f5092e333689)

Cliente dados:
- ID do cliente
- Nome
- CPF
- Endereço
- Telefone

Tela de Cadastro, edição, remoção e busca dos clientes.

## Tela Livro

![image](https://github.com/PhaellZX/BibliotecaWise/assets/48337836/2717e8fc-242c-4042-ba0d-18b4d47a36a8)

Livros dados:
- ID do livro
- Ano da Publicação
- Título
- Autor
- Gênero
- Dísponivel(Se o livro estiver disponivel, o aluguel é feito(true), senão, o aluguel não pode ser feito(false))

Tela de Cadastro, edição, remoção e busca dos livros.

## Tela Aluguel

![image](https://github.com/PhaellZX/BibliotecaWise/assets/48337836/f03926f7-acff-4118-b5ff-880e18616caa)

Dados do aluguel:
- ID do aluguel
- ID do cliente
- ID do livro
- Data do aluguel
- Data da devolução

## Tela para alugar ou devolver os livros.

![image](https://github.com/PhaellZX/BibliotecaWise/assets/48337836/b4d7ae87-2a5e-4c30-a0ee-df8f81032a4e)

O botão "Gerar Seed" serve para armazenar o conjunto de dados no banco de dados, em cada tela possui esse botão para fazer os testes possíveis. 

![image](https://github.com/PhaellZX/BibliotecaWise/assets/48337836/17dbd283-5d31-4625-880e-a173e919a0f8)

O botão de Refresh serve para atualizar os dados da tabela, caso necessário.

![image](https://github.com/PhaellZX/BibliotecaWise/assets/48337836/1f38547d-ed57-47ec-a4e5-4eba1ad31d4f)

Definir o tempo da thread.







