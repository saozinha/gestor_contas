# API GESTOR DE CONTAS  
 
## SIMULAÇÃO DE DEPÓSITOS E TRANSFERENCIAS DE VALORES

### AQUI VOCE VAI ENCONTRAR :
- JAVA 11
- MONGO
- DOCKER
- SWAGGER
 
### PASSOS PARA EXECUTAR ( Docker instalado ) : 

SE VOCÊ FEZ O CLONE OU BAIXOU O PROJETO

1 - NO TERMINAL . ACESSE A PASTA RAIZ DO PROJETO E DIGITE :

docker-compose up -d --force-recreate

2 - PARA ACESSAR A DOCUMENTACAO DA API VIA NAVEGADOR / SWAGGER NO NAVEGADOR :

http://localhost:8080/swagger-ui.html

### ORGANIZAÇÃO DO PROJETO :

├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── lourenco
│   │   │           └── gestor_contas
│   │   │               ├── GestorContasApplication.java
│   │   │               ├── config
│   │   │               ├── dal
│   │   │               ├── enums
│   │   │               ├── inpuOutpu
│   │   │               ├── module 
│   │   │               │   └── Account
│   │   │               │         └── controller    
│   │   │               │         └── mapper
│   │   │               │         └── repository
│   │   │               │         └── service
│   │   │               ├── tools
│   └── test
│       └── java
│           └── com
│               └── lourenco
│                   └── gestor_contas
│                       └── conta
│                           ├── faker
│                           └── AccountControllerTest.java
│                       └── person
│                           ├── faker
│                           └── PersonControllerTest.java
│                           └── PersonServiceTest.java