# Comfortly: Trip data microservice

## Prerequisites

```bash
docker run -d --name pg-trip-data -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=trip-data -p 5432:5432 postgres:13
```