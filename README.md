Kafka-kstream
=============
 
# How to reproduce 

> start kafka 1.0.0
```bash
docker-compose up
```

> to test with kafka 0.11
```bash
docker-compose -f docker-compose-11.yml up
```

### run app
```bash
gradle analyzer:bootRun
```

### ssh to kafka container
```bash
docker-compose exec kafka bash
# then you can run following commands in this shell
```

### list topics
```bash
kafka-topics --list --zookeeper zookeeper:2181
```

### send messages
```bash
kafka-console-producer --broker-list kafka:9092 --topic words
 ```
 
### receive messages
```bash
kafka-console-consumer --bootstrap-server kafka:9092 --topic counts --from-beginning --property print.key=true
```

### show dependencies
```bash
gradle analyzer:dependencies
```
