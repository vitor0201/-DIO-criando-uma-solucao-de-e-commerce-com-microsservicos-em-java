### Criando uma solução de e-commerce com microsserviços em Java

Projeto original: https://github.com/hatanakadaniel/ecommerce-checkout-api

Aulas do projeto:  https://web.dio.me/project/criando-uma-solucao-de-e-commerce-com-microsservicos-em-java/learning/506c1caf-2543-42cf-91f7-f4e28f2657ab?back=/track/capgemini-fullstack-java-and-angular

Meu perfil na DIO: https://web.dio.me/users/vitorrodrigues0201?tab=achievements

##### Implementação

Foi necessário realizar um downgrade da versão estável do Spring Boot (2.5.6) e do Kafka para conseguir rodar o projeto conforme as aulas, adicionar o repositório (https://packages.confluent.io/maven/)

```groovy
plugins {
    id "com.github.davidmc24.gradle.plugin.avro" version "1.2.0"
    id 'org.springframework.boot' version '2.3.1.RELEASE'
    id 'io.spring.dependency-management' version '1.0.9.RELEASE'
    id 'java'
}
```

`

```groovy
dependencies {

// ...
    implementation "org.apache.avro:avro:1.10.1"
    implementation group: 'io.confluent', name: 'kafka-avro-serializer', version: '5.5.0'
```

Também foi acrescentado duas bibliotecas a mais para auxiliar (Spring DevTools e Spring Actuator )



Além do mais foi necessário algumas alterações no Docker (schema-registry)

```yaml
  schema-registry:
    image: confluentinc/cp-schema-registry:7.0.0
    hostname: schema-registry
    container_name: schema-registry
    depends_on:
      - zookeeper
      - kafka
    ports:
      - "8081:8081"
    environment:
      SCHEMA_REGISTRY_HOST_NAME: schema-registry
      SCHEMA_REGISTRY_KAFKASTORE_CONNECTION_URL: 'zookeeper:2181'
      SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS: 'PLAINTEXT://broker:29092'
      SCHEMA_REGISTRY_DEBUG: true 
```



Nas últimas aulas o professor não corrige o erro do programa, mas para isso deve-se fazer a alteração seguinte no *application.yml* da payment-API



```yaml
  bindings:
    checkout-created-input:
      destination: streaming.ecommerce.checkout.created
      contentType: application/*+avro
      group: ${spring.application.name}
      consumer:
        use-native-decoding: true //mudar aqui
    payment-paid-output:
      destination: streaming.ecommerce.payment.paid
      contentType: application/*+avro
      producer:
        use-native-encoding: true //mudar aqui
```

