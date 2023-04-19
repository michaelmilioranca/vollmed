# Tools

- Docker
- Gradle
- Java 17
- MySql

# Run Locally

First we have to compose our database image, to do that we simply run the following command on the root of the project:

    docker compose --file docker-compose.yml up vollmed_db -d


# Sort pageable get endpoint

In order to sort the pageable get endpoints simply add in your call the following parameters:
- sort=<name_of_field> : This will order the response using your choice of field
- size=<page_size> : This will ensure that each page has the desired number of elements
- page=<page_number> : This will return the desired page

Example: https://localhost:8080/medicos?sort=nome,desc&size=1&page=2