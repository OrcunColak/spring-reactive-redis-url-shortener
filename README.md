# Read Me First

The original idea is from  
https://medium.com/dandelion-tutorials/url-shortener-using-spring-boot-webflux-with-spring-data-and-reactive-redis-8b89f5e63221

This example uses ReactiveRedisTemplate

```
curl --location 'http://localhost:8080/api/v1/add' \
--header 'Content-Type: application/json' \
--data '{
"link": "https://foo.com"
}'
```

or

```
curl -X POST "http://localhost:8080/api/v1/add" ^
--header "Content-Type: application/json" ^
--data "{\"link\": \"https://foo.com\"}"
```
