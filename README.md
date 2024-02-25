# Project phonebook

Teste prático da Equipe Blue

## Requisitos

- Regras de negócio e validações
  - Utilização de annotation de validation nos modelos @NotBlank
  - Validação de login com criptografia utilizando BCrypt e senha em hash com salt
  - Relacionamento de possesão da informação
  - Tenancy de visualização baseado no token JWT
- Utilização de padrões de projeto (repositórios, services, controllers, interfaces, injeção de dependência, etc.) dentro do contexto da aplicação
- RESTful API stateless
- Utilização do Spring Boot, Spring Security e Hibernate/JPA
- Utilização de libs e frameworks acessórios
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
    - Implementando
    - Quinoa
- Organização e limpeza do código
- Cobertura de testes no front
- Cobertura de testes de backend
- Utilização forte de componentes no frontend
- Modelo de casos de uso
- Modelo do banco de dados
  - Utilizado liquibase para definição e/ou população dos modelos

## Diferenciais
- Utilização e configuração do swagger
- Dockerfile ou docker-compose da aplicação
- Quarkus

## Como executar o backend
- > mvn quarkus:dev
- Abrir no navegador a interface de Dev do Quarkus em http://localhost:8080/q/dev

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