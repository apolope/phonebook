# Project phonebook

Teste prático da Equipe Blue

## Requisitos

- [x] Regras de negócio e validações
  - Utilização de annotation de validation nos modelos @NotBlank
  - Validação de login com criptografia utilizando BCrypt e senha em hash com salt
  - Relacionamento de possesão da informação
  - Tenancy de visualização baseado no token JWT
- [x] Utilização de padrões de projeto (repositórios, services, controllers, interfaces, injeção de dependência, etc.) dentro do contexto da aplicação
- [x] RESTful API stateless
- [x] Utilização do Spring Boot, Spring Security e Hibernate/JPA
- [x] Utilização de libs e frameworks acessórios
  - #### Backend
    - Resteasy Client
    - Resteasy Jackson
    - Hibernate, JPA e Panache
    - Smallrye JWT
    - Liquibase
    - Micrometer
    - JUnit 5
    - Lombook
  - #### Frontend
    - Quinoa
    - OpenAPI Generator para injeção no Angular
    - NGX Toaster
    - JWT Decode
    - Moment
- [x] Organização e limpeza do código
- [ ] Cobertura de testes no front (gap do projeto)
- [ ] Cobertura de testes de backend (gap do projeto)
- [ ] Utilização forte de componentes no frontend (gap do projeto)
- [x] Modelo de casos de uso
- [x] Modelo do banco de dados
  - Utilizado liquibase para definição e/ou população dos modelos

## Diferenciais
- [x] Utilização e configuração do swagger
- [x] Dockerfile ou docker-compose da aplicação
- [x] Quarkus

## Como executar o projeto
- Instalar o Java 17
- Instalar o Node 18
- Instalar o Maven 3
- > npm install
- > mvn clean package

### Executar com o maven
- > mvn quarkus:dev
- Abrir no navegador a interface de Dev do Quarkus em http://localhost:8080/q/dev
- Abrir no navegador do frontend http://localhost:8080/

### Executar com maven e docker com docker-compose
- > docker compose up -d
- Abrir no navegador a interface de Dev do Quarkus em http://localhost:8080/q/dev
- Abrir no navegador do frontend http://localhost:8080/
- 
## Documentação utilizadas para consulta
> **_RESTEASY CLASSIC_** - https://quarkus.io/guides/resteasy

> **_GENERATING JAKARTA REST RESOURCES WITH PANACHE_** - https://quarkus.io/guides/rest-data-panache

> **_SIMPLIFIED HIBERNATE ORM WITH PANACHE_** - https://quarkus.io/guides/hibernate-orm-panache

> **_CONFIGURE DATA SOURCES IN QUARKUS_** - https://quarkus.io/guides/datasource

> **_VALIDATION WITH HIBERNATE VALIDATOR_** - https://quarkus.io/guides/validation

> **_USING JWT RBAC_** - https://quarkus.io/guides/security-jwt

> **_SCHEDULING PERIODIC TASKS WITH QUARTZ_** - https://quarkus.io/guides/quartz

> **_MICROMETER METRICS_** - https://quarkus.io/guides/telemetry-micrometer

> **_LOMBOOK_** - https://projectlombok.org/setup/maven

> **_UTILIZAR OPENAPI E SWAGGER UI_** - https://pt.quarkus.io/guides/openapi-swaggerui

> **_USING LIQUIBASE_** - https://pt.quarkus.io/guides/liquibase

> **_BCrypt_** - https://github.com/jeremyh/jBCrypt/blob/master/src/main/java/org/mindrot/BCrypt.java

> **_Quinoa_** - https://docs.quarkiverse.io/quarkus-quinoa/dev/index.html

> **_OpenAPI Generator_** - https://pguso.medium.com/using-openapi-generator-in-angular-projects-4c2813f55a91



# Procedimentos
## OpenAPI Angular
### Instalar com npm
> npm install @openapitools/openapi-generator-cli -g
### Importar no Angular
>  npx openapi-generator-cli generate -i docs/openapi.yaml -g typescript-angular -o src/app/core/openapi --additional-properties=ngVersion=16.2.12,npmName=restClient,supportsES6=true,npmVersion=10.2.3,withInterfaces=true  --generate-alias-as-model