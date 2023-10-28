# spring-boot-consumer-service

## Tools

- Java version 21
- Maven
- MySQL
- Spring Framework
- Docker

## Dependency

- Spring Boot
- Spring Security
- Spring Validation
- Lombok
- JDBC API
- Java MySQL Connector

## Installation

Docker:

- clone repo: `$ git clone https://github.com/prafitradimas/spring-boot-consumer-service.git`
- run `docker compose`:

```sh
sudo docker compose up
```
- open browser then go to url `http://localhost:8081`

---
> **_NOTE:_**
> If MySQL denied connection from IP address: 172.x.x.x then run:
```sh
sudo docker exec -it mysqldb-compose bash
mysql -u root -p
UPDATE mysql.user SET host = '%' WHERE user = 'root' AND host = 'localhost';
exit
```
--
