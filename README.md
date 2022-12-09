# kin-pet-store-backend

## Requirements

For building and running the application you need:

- OpenJDK 17
- Docker

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

## Features

- Store and District entity support multiple languages. Change Accept-Language in request can control your language of
  response(eg en, zh-TW, zh-CN).
- Implement OAuth2 with Auth0. Guest able to log in with Google account, then user with access token can access create,
  update and delete. And all user related API authorization needed.
- DB driven API error message return. And it support multiple languages.
- Store search API allow user search store by GPS (latitude and longitude). An example put in Postman collection
  named ``store/Search Store - by latitude/longitude (near Sai Wan)``

## Know issues

- In query of store will hit N+1 problem, need extra effort to optimize.
- Customized error message do not cover all exceptions.

## Note

- Not complete implement with OAuth2, application should control the access by user's scope.

## Documentation

Database login information in docker-compose.yml

* [Postman Collection](https://github.com/kit-wwk/kin-pet-store-backend/blob/main/docs/Kin%20Pet%20Store.postman_collection.json)
* [Swagger API docs](http://127.0.0.1:8080/swagger-ui/index.html)
* [phpMyAdmin](http://localhost:8081/)
