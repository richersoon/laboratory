This is a over-simplified sample Rest API for viruses

# Technologies used
- Spring Boot 2
- Spring Web
- Spring JPA
- H2 Embedded Database

# How to run via IDE
1. Run the LaboratoryApplication.java's main method
2. Go to browser then access the [Swagger](http://localhost:8080/swagger-ui.html)

# Automated Test
We have high-coverage automated test in service level and model level where business logic resides

# Code commit history
Use GIT in order to track code history and how the code evolves

# Code Architecture
The code architecture follows Domain Driven Design where as it has anemic service but rich model <br/>
I can also follow fat service but anemic model but for this code I choose Domain Drive Design approached <br/>
For more info about DDD please refer to [Domain-Driven Design vs. anemic model. How do they differ?](https://blog.pragmatists.com/domain-driven-design-vs-anemic-model-how-do-they-differ-ffdee9371a86)

# Disclaimer
This is not a perfect project there are more improvement can be done like rest api level integration test using Cucumber or JBehave
I will appreciate any suggestions or comments regarding this project