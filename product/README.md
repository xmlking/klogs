Product
========
Product Kafka Streams App
 

### Run
> use `./gradlew` instead of `gradle` if you didn't installed `gradle`
```bash
gradle product:bootRun
```
### Test
```bash
gradle analyzer:test
```
### Build
```bash
gradle analyzer:build -x test 
# continuous build with `-t`. 
gradle -t analyzer:build
# build docker image
gradle analyzer:docker -x test 
```
 
