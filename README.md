### Docker
Criando e utilizando a API

Dockerfile
```
FROM openjdk:8
ADD target/docker-transacoes.jar docker-transacoes.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-transacoes.jar"]
```

Crie a imagem do docker
```
docker build -f Dockerfile -t docker-transacoes.jar .
```

Rode o container
```
docker run -p 8080:8080 docker-transacoes.jar
```

Caso queira rodar outras instâncias do container
```
docker run -p 9090:8080 docker-transacoes.jar
```
  
### POST "8080:8080"
curl -X POST \
  http://localhost:8080/transacoes \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 2ab7f3c6-65ea-47f4-adfe-84218fc00994' \
  -H 'cache-control: no-cache' \
  -d '{
"amount": 25000.15,
"timestamp": 1478221904000
}
'

### GET "8080:8080"
curl -X GET \
  http://localhost:8080/estatisticas \
  -H 'Postman-Token: a5b95e3c-e889-4668-8e75-1588da642ca2' \
  -H 'cache-control: no-cache'

### POST "9090:8080"
curl -X POST \
  http://localhost:9090/transacoes \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: d198bb1a-93fc-4058-b34e-ca41ad9222a6' \
  -H 'cache-control: no-cache' \
  -d '{
"amount": 25000.15,
"timestamp": 1478221904000
}
'
### GET "9090:8080"
curl -X GET \
  http://localhost:9090/estatisticas \
  -H 'Postman-Token: 72904c13-fbe7-4ad5-a175-8a183507326f' \
  -H 'cache-control: no-cache'