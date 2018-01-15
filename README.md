Kafka-kstream
=============

> start kafka
```bash
docker-compose -f docker-compose-local.yml up
```

### run app
```bash
gradle word-count:bootRun
```

### ssh to kafka container
```bash
docker-compose -f docker-compose-local.yml exec kafka bash
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


### Gradle Commands
```bash
# upgrade project gradle version
gradle wrapper --gradle-version 4.4.1 --distribution-type all
# gradle daemon status 
gradle --status
gradle --stop
# show dependencies
gradle classifier:dependencies
gradle classifier:dependencyInsight --dependency spring-messaging
# refresh dependencies
gradle build -x test --refresh-dependencies 
```
