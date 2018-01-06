WorkCount
========
WorkCount Kafka Streams App

> use `./gradlew` instead of `gradle` if you didn't installed `gradle`

### Test
```bash
gradle word-count:test
```
### Build
```bash
gradle word-count:build -x test 
# continuous build with `-t`. 
gradle -t word-count:build
# build docker image
gradle word-count:docker -x test 
```

 ### Deploy
 > Deploying to production.
```bash
nohup java -jar -Dspring.profiles.active=prod word-count-0.1.0-SNAPSHOT.jar > word-count.log 2>&1 & 
```

### Run
> run locally
#### start kafka
```bash
docker-compose up
# stop and remove volumes
docker-compose down -v
```

#### start app
```bash
gradle word-count:bootRun
# run with `prod` profile.
SPRING_PROFILES_ACTIVE=prod gradle word-count:bootRun
# fource to enable debug logs
SPRING_PROFILES_ACTIVE=prod gradle word-count:bootRun --debug
# via docker
docker-compose up word-count
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
