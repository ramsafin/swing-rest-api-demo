# Java Swing + REST Service Demo  

## Prerequisites
- Maven
- Java (ver. 8 and higher)
- NodeJS (for [json-server](https://github.com/typicode/json-server))

## Installation

Install json-server:
```bash
npm install -g json-server
```

## How to Run

- Launch json-server:
```bash
json-server --watch <path to your database>.json
```
The server will be available on `http://localhost:3000/` (you can open this link in your browser).

- Build & Run the application:
```bash
mvn clean package && java -jar target/<project-name>.jar
``` 
