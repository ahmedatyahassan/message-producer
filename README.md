# Kafka microservices

`microservice1` exposes an HTTP endpoint that publishes a string to the Kafka
topic `messages`. `microservice2` consumes that topic and returns received
messages over HTTP in FIFO order, one per request.

## Run locally

Start Kafka from this directory:

```bash
docker compose up -d
```

Start each Spring Boot application from its project directory:

```bash
cd ~/Intellij/message-producer && ./mvnw spring-boot:run
cd ~/Intellij/message-consumer && ./mvnw spring-boot:run
```

The producer listens on port `9091`, the consumer HTTP endpoint on port `9093`,
and Kafka on port `9092`.

Publish messages and consume them one at a time. If `A` is published before
`B`, successive consume requests return `A` and then `B`:

```bash
curl 'http://localhost:9091/produce/message?message=Hello%20Kafka'
curl http://localhost:9093/consume/message
```

The producer and consumer use `KAFKA_BOOTSTRAP_SERVERS`, which defaults to
`localhost:9092`.
