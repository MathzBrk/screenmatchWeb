# Screenmatch-Web

## Descrição
O **Screenmatch-Web** é um projeto desenvolvido em Java utilizando o Spring Boot e Maven. O objetivo do projeto é consumir a API do OMDB para obter dados sobre séries, traduzir as sinopses do inglês usando a API MyMemory, e exibir essas informações de forma organizada. O projeto tem integração com o PostgreSQL, e faz com que os dados das séries sejam gravados no banco de dados.
O projeto faz uso de funções lambda, do método `stream`, `enum`, e da biblioteca Jackson para manipulação de JSON.

## Funcionalidades
- Consumo de dados da API do OMDB sobre séries.
- Tradução das sinopses do inglês para o português utilizando a API MyMemory.
- Manipulação e filtragem de dados utilizando `streams` e `lambdas`.
- Utilização de `enum` para organizar e categorizar informações.
- Manipulação de JSON com a biblioteca Jackson.
- Exibição dos dados adquiridos de forma organizada.
- Integração com banco de dados
  
## Tecnologias Utilizadas
- **Java**
- **Spring Boot**
- **Maven**
- **API OMDB**
- **API MyMemory**
- **Jackson**
- PostgreSQL
  
## Instalação
1. Clone o repositório:
   ```bash
   git clone https://github.com/MathzBrk/screenmatch-web.git

