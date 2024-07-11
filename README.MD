# Plann.er
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)


O Plann.er é um projeto de planejamento e organização de viagens, desenvolvido em Java, utilizando Spring Boot para a construção do backend. Ele integra Flyway para migração de banco de dados, H2 Database para armazenamento de dados em memória durante o desenvolvimento, Lombok para simplificação do código e o Postman para teste das requisições.

O Plann.er permite:
- Cadastro de atividades que podem ser feitas durante viagens;
- Convidar participantes;
- Adicionar links que podem ser úteis.


Este projeto foi desenvolvido durante o evento Next Level Week ofertado pela Rocketseat.

## Estrutura do projeto

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para a criação de aplicações Java com configuração mínima.
- **Flyway**: Ferramenta de migração de banco de dados.
- **H2 Database**: Banco de dados em memória para ambiente de desenvolvimento.
- **Lombok**: Biblioteca para reduzir a verbosidade do código Java.

## Instalação 
1. Clone o repositório: 
```bash
git clone http://github.com/mclara831/project-springboot-jpa
```
2. Instale as dependências do Maven.

## Utilização 
1. Inicie a aplicação.
2. As API's podem ser acessadas em http://localhost:8080

## API's endpoints

```bash
POST /trip - registra uma nova viagem.

GET /trip/{id} - recupera a viagem pelo o seu id.

UPDATE /trip/{id} - atualiza os dados da viagem fornecida pelo id.

UPDATE /trip/{id}/confirm - confirma a viagem pelo o seu id.

POST /trip/{id}/activities - registra uma nova atividade na viagem fornecida pelo id.

GET /trip/{id}/activities - recupera todas as atividades cadastradas na viagem.

POST /trip/{id}/participants - convida um novo participante para a viagem.

GET /trip/{id}/participants - recupera todos os participantes cadastrados na viagem.

POST /trip/{id}/links - adiciona um novo link à viagem.

GET /trip/{id}/links - recupera todos os links cadastrados na viagem.

POST /participants/{id}/confirm - confirma o participante passado por id na viagem.
```

## Configuração do Banco de Dados

O projeto utiliza o H2 Database para desenvolvimento. A configuração padrão do banco de dados pode ser encontrada em 'src/main/resources/application.properties'.

O console H2 pode ser acessado em http://localhost:8080/h2-console com as seguintes credenciais:

- JDBC URL: jdbc:h2:mem:planner
- Username: sa
- Password:

## Postman
Os testes da API foram realizados utilizando o Postman.

Baixe e instale o Postman a partir do [site oficial](https://www.postman.com/).
