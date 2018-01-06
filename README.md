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
gradle word-count:bootRun
```

### ssh to kafka container
```bash
docker-compose exec kafka bash
# then you can run following commands in this shell
```

### receive messages
```bash
kafka-console-consumer --bootstrap-server kafka:9092 --from-beginning --property print.key=true --topic counts
```

### send messages
```bash
kafka-console-producer --broker-list kafka:9092 --topic words
```
 
### show dependencies
```bash
gradle analyzer:dependencies
```
