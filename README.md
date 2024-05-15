# LiterAlura - Catálogo de Livros
Esta aplicação foi construída durante o segundo desafio de back-end do programa de formação da ONE-Oracle Next Education, turma 6.

<br />

## Resumo

O LiterAlura é um catálogo de livros interativo desenvolvido em Java. Utiliza a API [gutendex](https://gutendex.com/) para buscar os livros. No app, você pode pesquisar por um livro, armazená-lo no banco de dados PostgreSQL configurado no Docker e buscar autores e livros diretamente no banco. O aplicativo foi desenvolvido utilizando Java e Spring.

<br />

### Demonstração

- Funcionalidade de busca na API:
<img src="https://ucarecdn.com/7a50e480-60b2-4f80-9330-66d64fee535d/1.gif" />

<br />

- Funcionalidade de listar livros registrados:
<img src="https://ucarecdn.com/4bca65df-6c37-4595-b482-838669653aee/2.gif" />

<br />

- Funcionalidade de listar autores registrados:
<img src="https://ucarecdn.com/c6a66fe6-9ef9-4503-8ab6-519dd85fad47/3.gif" />

<br />

- Funcionalidade de listar autores vivos em um determinado ano:
<img src="https://ucarecdn.com/03307d63-0dbb-42e7-a688-265bb86304dd/4.gif" />

<br />

- Funcionalidade de listar livros em um determinado idioma:
<img src="https://ucarecdn.com/93d6ded4-beb9-4860-bb42-3565e253cb50/5.gif" />

<br />
<br />

### Tecnologias Utilizadas

- Java _17_
- Spring Boot _3.2_
- JPA
- PostgreSQL
- Docker

<br />
<br />

### Executando o projeto localmente

1. **Pré-requisitos:**
    - Certifique-se de ter o Docker e o Docker Compose instalados.

2. **Clone o Repositório:**
    - Clone o repositório do LiterAlura para o seu ambiente local:
      ```
      git clone git@github.com:felipel7/challenge-java-literalura.git
      ```

3. **Configuração do Banco de Dados:**
    - Navegue até o diretório do projeto clonado.
    - Execute o comando `docker compose up` para iniciar o contêiner do PostgreSQL.
    - Após o contêiner estar em execução, abra um terminal e execute o comando:
      ```
      docker exec -it literalura-db-1 bash
      ```
    - Dentro do contêiner, execute o comando:
      ```
      psql -U postgres
      ```
    - No prompt do PostgreSQL, crie o banco de dados LiterAlura com o comando:
      ```
      CREATE DATABASE literalura;
      ```

4. **Execução da Aplicação:**
    - Após configurar o banco de dados, você pode executar a aplicação Java normalmente.

<br />

Com esses passos, você terá o LiterAlura pronto para ser utilizado localmente :).

<br />
<br />

Feito com carinho ♡ por Felipe Silva :)