## Spring Boot OAuth2 with Keycloak
- This is a 1Source Ledger reference UI that connects to https://stageapi.equilend.com and https://stageauth.equilend.com.
- The build leans on Spring Boot Web and Security but includes other adapters like the Smiley proxy.
- _Feel free to borrow any feature or function but in no way has this demo passed rigorous QA and/or Security checks._
## To Run:
- Import as Maven project
- Build with Java 8
- Set Keycloak properties in application.properties
## Deploying Docker container to AWS ECR
### Prerequisites
- Docker Desktop
- AWS CLI
- Permission to push images to AWS ECR
### Build the Docker Jar
- Base directory: ${project_loc:1source-spring-boot-oauth2}
- Goals: clean package
### Login with CLI
- aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 856307828688.dkr.ecr.us-east-1.amazonaws.com
### Build Docker image
- docker build -t 1source-spring-boot-oauth2:1.0.18 .
- note the version number _1.0.18_. Check AWS ECR for the last used image tag and increment accordingly 
### Tag the Docker image in AWS ECR
- docker tag 1source-spring-boot-oauth2:1.0.18 856307828688.dkr.ecr.us-east-1.amazonaws.com/1source-spring-boot-oauth2:1.0.18
### Push the Docker image to AWS ECR
- docker push 856307828688.dkr.ecr.us-east-1.amazonaws.com/1source-spring-boot-oauth2:1.0.18
