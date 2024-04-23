 # jwt-challenge
Api de Validação de Jwt

![technology java](https://img.shields.io/badge/technology-Java-purple.svg)
![technology Gradle](https://img.shields.io/badge/technology-Gradle-blue.svg)
![technology Webflux](https://img.shields.io/badge/tecnology-WebFlux-green)


- ### Pré-requisitos
    - [**Java 17**](https://www.oracle.com/java/technologies/downloads/#java17)
    - [**Gradle**](https://docs.gradle.org/current/userguide/userguide.html) | _or use the wrapper ./gradlew_
    - [**Spring Boot 2**](https://spring.io/projects/spring-boot)
    - [**Docker**](https://docs.docker.com/docker-for-mac/install/#download-docker-for-mac)

## Rodando a aplicação:

A aplicação irá subir na porta 8080


### Instalando dependências

````
./gradlew clean build
````

### Rodando os testes

```
./gradlew clean test
```

### Via IDE

Em `Run/Debug Configuration`, crie uma configuração Gradle e define conforme as opções abaixo:

Gradle project

```
jwt-challenge
```

Task

```
bootRun
```

### Executando os testes

Execute o comando abaixo para executar os testes da aplicação.

```./gradlew clean test```

