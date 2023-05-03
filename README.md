## How to run

- Run axon server and mysql firstly

```
cd infra
docker-compose up
```

## Build common API & Run each service

- Build common API
```
cd common-api
mvn clean install
```

- Run each service
```
# new terminal
cd Order
mvn clean spring-boot:run

# new terminal
cd Delivery
mvn clean spring-boot:run

# new terminal
cd Product
mvn clean spring-boot:run

```

- Run API gateway
```
cd gateway
mvn clean spring-boot:run
```

- Run frontend server
```
cd frontend
npm i
npm run serve

```

## Test By UI
Head to http://localhost:8088 with a web browser

## Test Rest APIs
- Order
```
 http :8088/orders id="id" userId="userId" productId="productId" 
```
- Delivery
```
 http :8088/deliveries id="id" address="address" orderId="orderId" 
```
- Product
```
 http :8088/products id="id" Name="Name" stock="stock" 
```

## Test RSocket APIs

- Download RSocket client
```
wget -O rsc.jar https://github.com/making/rsc/releases/download/0.4.2/rsc-0.4.2.jar
```
- Subscribe the stream
```
java -jar rsc.jar --stream  --route orders.all ws://localhost:8088/rsocket/orders

java -jar rsc.jar --stream  --route deliveries.all ws://localhost:8088/rsocket/deliveries

java -jar rsc.jar --stream  --route products.all ws://localhost:8088/rsocket/products

```
