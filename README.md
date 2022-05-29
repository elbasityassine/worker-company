# WORKER-COMPANY
We are retrieving company information for the purpose of updating our database of active
businesses, we usually split business based on their company description. This information
in France is called the SIRET. It’s a unique identifier for a business in France and this info is
public. For the purpose of this test we need to build a microservice which updates the public
database info in our system.

## Requirements

* JDK 11
* Gradle
* IDE: IntelliJ


## Run tests
#### Unit tests:
* `gradle clean test` : runs Junit tests

## Run with Docker Compose
1. `docker system prune` : in case you have a running docker image
2. `gradle build -x test && docker-compose up --build`

## Startup using Profile
* `java -jar -Dspring.profiles.active=prod presentation/target/presentation-0.0.1-SNAPSHOT.jar`

## Properties
* `feature.batch.output-folder` To specify the export folder paths of csv files
* `feature.batch.entreprise.siret-companies-file-path` To specify the path of the file containing the SIRETs of the companies to load
