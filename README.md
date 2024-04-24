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
## Descrição
Microserviço de validação de jwt, utilizando Java 17, Spring 2.x e Webflux.
Para o desenvolvimento da solução foi criada uma `JwtServiceImpl` que decodifica o Jwt
e utiliza o Gson para converter a String em um JsonObject, caso ocorra algum erro
na conversão pro JsonObject, significa que o Jwt é invalido retornando `false`
após a conversão do Jwt é ultilizado o `.filter()` do webflux para chamar a os metodos 
da classe `ValidatorClaimService`, onde nela é realizado as regras de negócios, sendo que seus metodos são:

`verifyNumberOfClaims` verifica a quantidade de claims no Jwt

`verifyDigitClaimName` verifica se o Claim Name possui digito

`verifyClaimRole` verifica se o Claim Role é valido

`verifyPrimeNumberOnClaimSeed` verifica se o Claim Seed é primo

`verifySizeOnClaimName` verifica se o Claim name tem mais de 256 caracteres

Caso passe por todas as verificações retorna um booleano `true` caso não retorna `false`

Na realização dos testes integrados foi ultilizado `WebTestClient` e nos teste unitários `JUnit`

Para verificar a cobertura de teste foi ultilizado o `jacoco` e para observability foi
utilizado o `Spring Cloud Sleuth`

O projeto segue o pattern `Controller-Service-Repository`

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

