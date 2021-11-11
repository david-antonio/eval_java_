# Prueba tecnica BCI

Method	Urls	Actions
POST	/apirest/users	Metodo post para creacion de usuario
GET	/apirest/users/{email}	Metodo get para obtener usuario
POST	/apirest/users/login	Metodo post que permite login de usuario actualizando fecha de ultimo login y retornando un nuevo session uuid
DELETE	/apirest/users/{email}	Metodo delete para eliminacion de usuario

## Run Spring Boot application
```
mvn spring-boot:run
```

