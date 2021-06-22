# API GESTOR DE CONTAS  
 
## SIMULAÇÃO DE DEPÓSITOS E TRANSFERENCIAS DE VALORES

### AQUI VOCE VAI ENCONTRAR :
- JAVA 11
- MONGO
- DOCKER
- SWAGGER
 
### PASSOS PARA EXECUTAR :   
 
  Para o 1 passo, instale o [Docker] (https://balta.io/blog/docker-instalacao-configuracao-e-primeiros-passos) ) : 

SE VOCÊ FEZ O CLONE OU BAIXOU O PROJETO

1 - NO TERMINAL . ACESSE A PASTA RAIZ DO PROJETO E DIGITE :

docker-compose up -d --force-recreate

2 - PARA ACESSAR A DOCUMENTACAO DA API VIA NAVEGADOR / SWAGGER NO NAVEGADOR :

http://localhost:8080/swagger-ui.html

### ORGANIZAÇÃO DO PROJETO :

├── pom.xml <br />
├── src <br />
│   ├── main <br />
│   │   ├── java <br />
│   │   │   └── com <br />
│   │   │       └── lourenco <br />
│   │   │           └── gestor_contas <br />
│   │   │               ├── GestorContasApplication.java <br />
│   │   │               ├── config <br />
│   │   │               ├── dal <br />
│   │   │               ├── enums <br />
│   │   │               ├── inpuOutput <br />
│   │   │               ├── module  <br />
│   │   │               │   └── Account <br />
│   │   │               │         └── controller     <br />
│   │   │               │         └── mapper <br />
│   │   │               │         └── repository <br />
│   │   │               │         └── service  <br />
│   │   │               ├── tools <br />
│   └── test <br />
│       └── java <br />
│           └── com <br />
│               └── lourenco <br />
│                   └── gestor_contas <br />
│                       └── conta <br />
│                           ├── faker <br />
│                           └── AccountServiceTest.java <br />
│                       └── person <br />
│                           ├── faker <br /> 
│                           └── PersonServiceTest.java <br />