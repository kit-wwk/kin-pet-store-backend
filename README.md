# kin-pet-store-backend

## Requirements

For building and running the application you need:

- OpenJDK 17

## Running the application locally

### 1. Setup MySQL and phpMyAdmin with Docker

```shell
cd docker
docker compose up
```

### 2. Run Spring boot application locally

Start Spring boot 3.0 application, please make sure port 8080 is available

```shell
gradle bootrun
```

3. Visit homepage http://127.0.0.1:8080, then click "OAuth2 via auth0".
   And finish oAuth2 login flow with Google account for access token.

4. Copy the access token to Postman's collection. Then you can access all API.

### Documentation

Database login information in docker-compose.yml

* [Postman Collection](https://github.com/kit-wwk/kin-pet-store-backend/blob/main/docs/Kin%20Pet%20Store.postman_collection.json)
* [Swagger API docs](http://127.0.0.1:8080/swagger-ui/index.html)
* [phpMyAdmin](http://localhost:8081/)
