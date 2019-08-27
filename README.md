### 0. Start Database
```
docker-compose up
```

### 1. Build the project
```
./bradlew build
```

### 2. Start the server (started on port 8087)
```
java -jar server/build/libs/gs-spring-boot-0.1.0.jar
```

### 3. Start the client
```
 java -jar client/build/libs/client.jar --number_of_users=10 --concurrent_threads_per_user=16 --round_per_thread=20
```

### Connection to database
login: **betpawa_user**

password: **betpawa_user**

database: **betpawa**

### Important choices:

- Use PESSIMISTIC_WRITE mode for Wallet table
- Made a separate module for proto files
- Use Flyway and Docker for database

### Careful
Integration tests start on the same port as the main server
