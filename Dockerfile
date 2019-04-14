FROM openjdk:8
ADD target/docker-transacoes.jar docker-transacoes.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-transacoes.jar"]