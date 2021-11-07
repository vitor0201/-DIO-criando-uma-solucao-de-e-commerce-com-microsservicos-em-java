### Criando uma solução de e-commerce com microsserviços em Java

Projeto original: https://github.com/hatanakadaniel/ecommerce-checkout-api

Aulas do projeto:  https://web.dio.me/project/criando-uma-solucao-de-e-commerce-com-microsservicos-em-java/learning/506c1caf-2543-42cf-91f7-f4e28f2657ab?back=/track/capgemini-fullstack-java-and-angular

Meu perfil na DIO: https://web.dio.me/users/vitorrodrigues0201?tab=achievements

##### Implementação

Foi necessário realizar um downgrade da versão estável do Spring Boot (2.5.6) e do Kafka para conseguir rodar o projeto conforme as aulas, adicionar o repositório (https://packages.confluent.io/maven/)

`plugins {
    id "com.github.davidmc24.gradle.plugin.avro" version "1.2.0"
    id 'org.springframework.boot' version '2.3.1.RELEASE'
    id 'io.spring.dependency-management' version '1.0.9.RELEASE'
    id 'java'
}`

`dependencies {`

`// ...`

`implementation group: 'io.confluent', name: 'kafka-avro-serializer', version: '5.5.0'`

`implementation 'org.springframework.boot:spring-boot-starter-data-jpa'`



Também foi acrescentado duas bibliotecas a mais para auxiliar (Spring DevTools e Spring Actuator )



Além do mais foi necessário algumas alterações no Docker (schema-registry)

schema-registry:
    image: confluentinc/cp-schema-registry:7.0.0 **// retirei o latest e escolhi a versão**
    hostname: schema-registry
    container_name: schema-registry
    depends_on:

   - zookeeper
     kafka
         ports:
        - "8081:8081"
          vironment:
                SCHEMA_REGISTRY_HOST_NAME: schema-registry
                SCHEMA_REGISTRY_KAFKASTORE_CONNECTION_URL: 'zookeeper:2181'
                SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS: 'PLAINTEXT://broker:29092' **//necessário para poder executar o contêiner**
                SCHEMA_REGISTRY_DEBUG: true 









