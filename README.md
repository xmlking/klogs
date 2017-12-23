Kafka-kstream
=============
 
# How to reproduce 

> start kafka 1.0.0
```bash
docker-compose up
```

> to test with kafka 0.10.1.1
```bash
docker-compose -f docker-compose-10.yml up
```

### run app
gradle analyzer:bootRun

docker-compose exec kafka bash

### list topics
kafka-topics --list --zookeeper zookeeper:2181

### send messages
kafka-console-producer --broker-list kafka:9092 --topic words
 
### receive messages
kafka-console-consumer --bootstrap-server kafka:9092 --topic counts --from-beginning --property print.key=true

### show dependencies
gradle analyzer:dependencies
