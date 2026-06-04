# ThreatFusion

ThreatFusion is a threat intelligence platform built with Spring Boot and PostgreSQL.

## Features

- IP address threat lookups
- IPv4 validation
- Search history persistence
- PostgreSQL integration
- Global exception handling
- GeoIP integration using external REST APIs

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## Endpoints

### Health Check

GET /api/health

### IP Lookup

GET /api/search/ip/{ipAddress}

### Search History

GET /api/history