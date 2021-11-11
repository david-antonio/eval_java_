# Prueba tecnica BCI

Se implemento API Restfull que exponga una API RESTful de creación de usuarios 

- SrpingBoot
- BD en memoria H2
- JPA
- Maven

## 1. Crear usuario
```
Metodo      POST 
Contexto    /apirest/users
Descripcion Metodo post para creacion de usuario

Ejemplo     http://localhost:8080/apirest/users
>{
>    "name":"juan",
>    "email":"juan@mail.cl",
>    "password":"juan123",
>    "phone":[
>        {
>            "number":"7777777",
>            "citycode":"9",
>            "countrycode":"56"
>        }
>    ]
>}
```

## 2. Obtener usuario
```
Metodo GET 
Contexto    /apirest/users/{email}             
Descripcion Metodo get para obtener usuario
        
Ejemplo     http://localhost:8080/apirest/users/juan@mail.cl

```

## 3. Login
```
Metodo      POST    
Contexto    /apirest/users/login    
Descripcion Metodo post que permite login de usuario actualizando fecha de ultimo login y retornando un nuevo session uuid

Ejemplo     http://localhost:8080/apirest/users/login
>{
>    "email":"juan@mail.cl",
>    "password":"juan123"
>}
```

## 4. Eliminar usuario
```
Metodo      DELETE
Contexto    /apirest/users/{email} 
Descripcion Metodo delete para eliminacion de usuario

Ejemplo     http://localhost:8080/apirest/users/juan@mail.cl
```

## Run Spring Boot application
```
mvn spring-boot:run
```