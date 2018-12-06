```
mvn clean package
```

```
java -Xms5m -Xmx5m -XX:MaxMetaspaceSize=24M -XX:ReservedCodeCacheSize=16M -XX:CompressedClassSpaceSize=8M -XX:MaxDirectMemorySize=8M -Xss512k -jar target/blackhole-1.0.0-SNAPSHOT.jar
```
