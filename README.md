# documents-api
Microservice exposing API to create, update, delete and viewing documents and authors

The next diagram show the solution.

![alt text](https://res.cloudinary.com/dnmkshfod/image/upload/v1724318159/DocumentsAppServices_tpkdhu.png)

## Database
The solution use a MySQL database with the following schema.

![alt text](https://res.cloudinary.com/dnmkshfod/image/upload/v1724318159/DatabaseModel_liuwbb.png)

## General Approach

This project expose a REST API structured with Hexagonal Architecture. 
In case you're not familiarized with it check this link. https://theanirban.dev/hexagonal-architecture-in-java/

I choose this architecture because allows to follow SOLID principles with and DDD approach
keeping the business logic isolated.

When defining the uses cases I used 2 different approaches.

1. For the authors process I defined 1 interface for each use case. 
I like this approach because gives more flexibility when expanding how the application interacts with
other applications. But in the other hand makes you defined a bigger amount of classes.

2. For the documents process I defined just one interface for all the use cases. By doing this you 
define a minor amount of classes, but you lose flexibility.

## APIs

This project defines 2 different api types to show another way to expose an API.
I implemented a graphQL api (very simple) because I wanted to show the benefits
like reduce the amount of data traveling through the responses, it can be a big advantage.
BFF is another way of doing this, but BFF at some point would return the all response.

As requested the 'main' API is a REST API documented with Swagger.

For accessing the swagger documentation go to http://localhost:8085/swagger-ui/index.html

For accesing the graphql API documentation go to http://localhost:8085/graphiql?path=/graphql

## Apache Kafka

This project implements a Kafka Consumer used to delete an author and its documents.
You can use the ui included as container for send messages easily.

You can check this ui in this url http://localhost:8088/

# Important
The project has some flaws

1. For simplicity and time issues it has credentials defined in the repository, this of course
id not a good practice and is a security problem.
2. It does not implement security for the rest endpoints
3. It does not include a web client for interacting with the api the CRUD operations
for authors and documents.
