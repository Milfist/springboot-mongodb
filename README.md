[![Build Status](https://travis-ci.org/Milfist/springboot-mongodb.svg?branch=master)](https://travis-ci.org/Milfist/springboot-mongodb)

# SpringBoot MongoDB FluentD Spring AOP

### Descripción:
 
Microservicio SpringBoot 2 con persistencia en MongoDB.
La trazabilidad de los distintos acontecimientos, está realizada con Spring AOP, que son recogidos con FluentD y persistidos en Mongo DB.

### ¿Qué necesitamos?

La configuración de Mongo DB y FluentD está en el applicatión.yml. Si ya tienes ambos arrancados, bastará con apuntar el ms a donde los tengas alojados.

```
spring:
  data:
    mongodb:
      database: "miguel"
      host: "127.0.0.1"
      port: 27017

fluentd:
  tag: "com.milfist.mongo"
  host: "localhost"
  port: 24224
```

#### MongoDB

Para arrancar un MongoDB en un contenedor:

```
docker run -d -p 27027:27027 -v <directorio>:/data/db mongo
```

#### FluentD

Para arrancar FluentD en un contenedor:

```
docker run -d -p 24224:24224 -p 24224:24224/udp -v <directorio>:/fluentd/log fluent/fluentd:v1.3-debian-1
```

##### Importante

El contenedor debe tener permisos de escritura en el directorio.

# By:

[![alt text](https://github.com/Milfist/Docs/blob/master/milfist.JPG)][0]

[0]: https://github.com/Milfist/
