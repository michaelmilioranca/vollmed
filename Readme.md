# Tools

- Docker
- Gradle
- Java 21
- MySql
- SpringBoot 3

# Run Locally

Para rodar local pode ser utilizado o profile de `dev`, assim sera criado um banco h2 que sera inicializado com dados ficticios conforme o arquivo data.sql

Exemplo rodando com profile de dev pelo terminal:

```BASH
./gradlew bootRun --args='--spring.profiles.active=dev'
```

Ou caso preferir pode ser utilizado o docker para subir um banco de dados local

Para subir o docker pode ser utilizado o comando abaixo:

```BASH
docker compose --file docker-compose.yml up vollmed_db -d
```
Importante! Caso opte por rodar local com o docker, deve ser atualizado as informacoes do banco de dados no arquivo `application-dev.yml`


# Sort pageable

Para poder usar a paginacao nos endpoint de consulta, pode simplesmente adicionar na chamada do servico os seguintes parametros:
- sort=<nome do campo> : O nome do campo que sera ordenado
- size=<tamanho da paginacao> : Numero de elementos retornado por pagina
- page=<numero da pagica> : Pagina desejada

Exemplo: https://localhost:8080/medicos?sort=nome,desc&size=1&page=2

# Error translation

Para receber os erros conforme a localidade, pode ser adicionado no header da requisicao o seguinte parametro:

- Accept-Language: pt-br

# HTTP Status examples:

- 1XX: Informativo – a solicitação foi aceita ou o processo continua em andamento;
- 2XX: Confirmação – a ação foi concluída ou entendida;
- 3XX: Redirecionamento – indica que algo mais precisa ser feito ou precisou ser feito para completar a solicitação; 
- 4XX: Erro do cliente – indica que a solicitação não pode ser concluída ou contém a sintaxe incorreta;
- 5XX: Erro no servidor – o servidor falhou ao concluir a solicitação.

