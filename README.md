# mongo-collector-service

Spring Boot microservice that:
- listens to MongoDB Change Streams and logs every insert/update/delete
- inserts a sample document into MongoDB **every 30 minutes** via `@Scheduled`

Kafka producer is **not** wired yet — see `MongoWatcherService.publishEvent()`.

## Stack

- Java 21 · Spring Boot 3.3.4 · Maven
- Spring Data MongoDB (MongoTemplate + MongoRepository)

## Project structure

```
src/main/java/am/wago/mongocollector/
├── config/
│   └── MongoConfig.java              — client settings, index bootstrap
├── model/
│   ├── CollectedDocument.java        — level 1 (root document)
│   ├── DocumentMetadata.java         — level 2
│   ├── EventPayload.java             — level 2
│   ├── SourceInfo.java               — level 3
│   └── SystemInfo.java               — level 4
├── repository/
│   └── CollectedDocumentRepository.java
├── service/
│   ├── MongoWatcherService.java      — Change Stream listener
│   └── MongoDataSchedulerService.java — inserts sample doc every 30 min
└── controller/
    └── WatcherController.java        — /api/watcher/status, /api/watcher/documents
```

### Document nesting (4 levels)

```
CollectedDocument              ← level 1
  metadata: DocumentMetadata   ← level 2
    source: SourceInfo         ← level 3
      system: SystemInfo       ← level 4
  payload: EventPayload        ← level 2
```

## Local run (Docker Compose)

```bash
# Build and start MongoDB replica set + the service
docker compose up --build

# Check watcher status
curl http://localhost:8080/api/watcher/status

# Actuator health
curl http://localhost:8080/actuator/health
```

## Local run (bare JVM)

### Start MongoDB as replica set (one-time)

```bash
docker run -d --name mongo-rs \
  -p 27017:27017 \
  mongo:7.0 mongod --replSet rs0 --bind_ip_all

docker exec -it mongo-rs mongosh --eval \
  "rs.initiate({_id:'rs0', members:[{_id:0, host:'localhost:27017'}]})"
```

### Run the service

```bash
export MONGO_URI="mongodb://localhost:27017/?replicaSet=rs0"
export MONGO_DATABASE="collector_db"
export MONGO_WATCH_COLLECTION="events"

mvn spring-boot:run
```

## Environment variables

| Variable                 | Default                                      | Description                          |
|--------------------------|----------------------------------------------|--------------------------------------|
| `MONGO_URI`              | `mongodb://localhost:27017/?replicaSet=rs0`  | MongoDB connection string            |
| `MONGO_DATABASE`         | `collector_db`                               | Target database                      |
| `MONGO_WATCH_COLLECTION` | `events`                                     | Collection watched by Change Stream  |
| `SCHEDULER_SOURCE_NAME`  | `scheduler`                                  | Source name in inserted documents    |
| `SCHEDULER_ENV`          | `local`                                      | Environment tag in inserted docs     |

## Endpoints

| Method | Path                     | Description                              |
|--------|--------------------------|------------------------------------------|
| GET    | /api/watcher/status      | Watcher state + stored document count    |
| GET    | /api/watcher/documents   | List all stored CollectedDocuments       |
| GET    | /actuator/health         | Spring Boot health check                 |
| GET    | /actuator/metrics        | Metrics                                  |

## Adding Kafka later

Open `MongoWatcherService.java` and replace the stub:

```java
public void publishEvent(Document doc) {
    // TODO: inject KafkaProducer and call producer.send(topic, doc)
}
```
