Screenmatch-Web
##Descrição
O Screenmatch-Web é um projeto desenvolvido em Java utilizando o Spring Boot e Maven. O objetivo do projeto é consumir a API do OMDB para obter dados sobre séries, traduzir as sinopses do inglês usando a API MyMemory, e exibir essas informações de forma organizada. O projeto tem integração com o PostgreSQL, e faz com que os dados das séries sejam gravados no banco de dados.

Agora, a aplicação também atua como uma API REST, permitindo que o front-end consuma os dados de séries, incluindo informações detalhadas sobre as temporadas e episódios, além de retornar as 5 melhores séries e episódios de cada série.

O projeto faz uso de funções lambda, do método stream, enum, e da biblioteca Jackson para manipulação de JSON. A API retorna informações como as séries mais recentes, as melhores séries avaliadas, e para cada série, os detalhes de todas as temporadas e episódios, ou por temporada específica.

##Funcionalidades
Consumo de dados da API do OMDB sobre séries.
Tradução das sinopses do inglês para o português utilizando a API MyMemory.
Manipulação e filtragem de dados utilizando streams e lambdas.
Utilização de enum para organizar e categorizar informações.
Manipulação de JSON com a biblioteca Jackson.
API REST para fornecer dados ao front-end sobre séries, temporadas e episódios.
Retorna as melhores séries e episódios.
Exibe detalhes das temporadas e episódios de cada série.
Integração com banco de dados PostgreSQL.
Tecnologias Utilizadas
Java
Spring Boot
Maven
API OMDB
API MyMemory
Jackson
PostgreSQL
