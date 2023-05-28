## BRDO-Project

Test Java Spring Boot project

---

## Get started

clone repository

```plaintext
 git clone https://github.com/aleporyadin/brdo.git
```

---

I recommend you use **Intellij idea**  
Open project in Intellij idea  
Next, add **configuration Spring Boot**  
Use MySQL and create DB brdo.   
Check file **"application.properties" ** (change user db and password)

## Set up setting DB example

```plaintext
spring.application.name=brdo
server.port=8008
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/brdo?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

#jpa vendor adapter configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true

brdo.api=https://dummyjson.com/comments
```

## Routing

---

### Login page

```plaintext
localhost:8080/login
```

### Register page

```plaintext
localhost:8080/register
```

### List of users

```plaintext
localhost:8080/users
```

## How can you see users? 

Find CRON folder in src. There we have a component with a method @Schedule()  
You can do:   
@Scheduled(cron = "0 \* \* \* \* \*")  // users can uploading to db every 1 minute  
Just wait and reload page to see users after login