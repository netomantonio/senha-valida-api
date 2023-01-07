# Getting Started

## Documentação de referência
Para referência adicional, considere as seguintes seções:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.7/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.7/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.7/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.7/reference/htmlsingle/#using.devtools)
* [GraphQL Kotlin](https://opensource.expediagroup.com/graphql-kotlin/docs/)

## Guia

### Subir Aplicação

- #### 1 - Docker
    Para rodar a aplicação basta executar o comando abaixo, desde que o docker esteja instalado, no seu terminal/cmd:
    
    ```docker compose up -d```
    
    A aplicação irá rodar na url ``localhost:8080``
    
    Para baixar a aplicação digite o comando a baixo no terminal
    
   ``docker compose down``

- ##### 2 - JAR
    Basta rodar o comando abaixo na pasta ``build/libs/`` na raiz do projeto:
    
    ``java -jar senha-valida-1.0.0-api.jar``
    
    A aplicação irá rodar na url ``localhost:8080``

### Usando API GraphQL

- #### Postman ou Insomnia
    Para usar a api basta importar o arquivo ``Senha_Valida.postman_collection.json`` na pasta ``resources`` que se encontra
    na raíz do projeto, ao postman ou insomnia e enviar a request já configurada.

- #### cURL
    ``
  curl --location --request POST 'http://localhost:8080/graphql' \
  --header 'Content-Type: application/json' \
  --data-raw '{"query":"query {\r\n    verify(password: \"Tee1$233@4\", rules: [\r\n        {rule: \"minSize\",value: 8},\r\n        {rule: \"minSpecialChars\",value: 2},\r\n        {rule: \"noRepeted\",value: 0},\r\n        {rule: \"minDigit\",value: 4}\r\n    ]) {\r\n        verify\r\n        noMatch\r\n    }\r\n}","variables":{}}'
    ``

#### Payload 
O objeto a seguir contém o padrão para solicitação de validação de senha conforme as regras informadas.
Porém, a api só poderá validar as regras a seguir (**minSize; minSpecialChars; noRepeted; minDigit**), 
qualquer outra regra que não seja as mencionadas serão ignoradas.
    * Observação: a regra noRepeted será considerada apenas se seu valor for a partir de 2

    ``query {
        verify(password: "Tee1$233@4", rules: [
            {rule: "minSize",value: 8},
            {rule: "minSpecialChars",value: 2},
            {rule: "noRepeted",value: 0},
            {rule: "minDigit",value: 4}
        ]) {
            verify
            noMatch
        }
    }``

