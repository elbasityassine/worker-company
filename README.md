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

## Environment variable
* `%COMPANIES_BATCH_OUT_FOLDER%` To specify the export folder paths of csv files
* `%SIRET_COMPANIES_FILE_IN%` To specify the path of the file containing the SIRETs of the companies to load
* `%COMPANIES_BATCH_CHUNK_SIZE%` To specify the size of chunk
* `%SERVICE_PUBLIC_ENTREPRISE%` URL of api public enterprise

## Startup using Profile
* `java -jar -Dspring.profiles.active=prod application/target/application-0.0.1-SNAPSHOT.jar`

