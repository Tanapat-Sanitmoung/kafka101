
I read this [Link](https://hub.docker.com/r/apache/kafka)

## Single container role : Broker + Controller

1. execute

```shell
podman compose -f docker-compose.yml -p kafka101 up -d
```

2. open new terminal and execute
```shell
podman exec --workdir /opt/kafka/bin/ -it broker sh

# type command to create a "test-topic" topic
./kafka-topic.sh --bootstrap-server localhost:9092 --create --topic test-topic

# type command to produce message 
./kafka-console.producer.sh --bootstrap-server localhost:9092 --topic test-topic
```

3. open new terminal and execute
```shell
podman exec --workdir /opt/kafka/bin/ -it broker sh

# type command to consume a "test-topic" topic
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic
```

4. switch back to terminal in 2., type someting and hit Enter, you will see message in terminal 3.

---

```shell
# Create a customer
curl --json '{"email":"user@example.com","name":"Tanapat Sanitmoung"}' \
  http://localhost:8080/customers
```
