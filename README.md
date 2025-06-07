# Projeto de Arquitetura Orientada a Serviço (SOA)

Este projeto implementa uma Arquitetura Orientada a Serviço (SOA) em Java com Spring Boot, demonstrando o consumo de APIs RESTful e SOAP, organização modular, separação de camadas e adoção de padrões.

## Estrutura do Projeto

O projeto está organizado em módulos Maven, cada um com uma responsabilidade específica:

- **core-domain**: Contém as entidades de domínio e objetos de valor
- **service-api**: Define as interfaces dos serviços de negócio
- **service-impl**: Implementa os serviços de negócio
- **data-access**: Responsável pela persistência de dados
- **rest-adapter**: Implementa a camada de controle para APIs RESTful
- **soap-adapter**: Implementa a camada de controle para APIs SOAP
- **application**: Módulo principal que orquestra os outros módulos

## Requisitos Atendidos

1. **Consumo de APIs RESTful e SOAP (40 pts)**
   - Implementação de clientes para consumir APIs RESTful usando Spring RestTemplate
   - Implementação de clientes para consumir APIs SOAP usando chamadas HTTP diretas
   - Métodos genéricos para chamadas REST e SOAP com parâmetros dinâmicos

2. **Organização Modular (10 pts)**
   - Estrutura multi-módulo Maven
   - Serviços independentes e reutilizáveis
   - Dependências claras entre módulos

3. **Separação de Camadas (30 pts)**
   - Camada de Domínio: Entidades e objetos de valor
   - Camada de Serviço: Interfaces e implementações
   - Camada de Dados: Repositórios e mapeadores
   - Camada de Controle: Controladores REST e endpoints SOAP

4. **Adoção de Padrões (20 pts)**
   - REST: Controladores RESTful com Spring MVC
   - SOAP: Endpoints SOAP com Spring WS
   - JSON: Processamento com Jackson
   - XML: Processamento com JAXB
   - WSDL: Geração automática com Spring WS

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Web (REST)
- Spring Web Services (SOAP)
- Spring Data JPA
- H2 Database (para demonstração)
- Maven (gerenciamento de dependências e build)

## Como Executar

1. Clone o repositório
2. Execute `mvn clean install` na raiz do projeto
3. Execute `mvn spring-boot:run -pl application` para iniciar a aplicação
4. Acesse a API REST em `http://localhost:8080/soa-project/api/`
5. Acesse o WSDL do serviço SOAP em `http://localhost:8080/soa-project/ws/customers.wsdl`

## Endpoints Disponíveis

### REST

- `GET /api/products` - Lista todos os produtos
- `GET /api/products/{id}` - Obtém um produto por ID
- `GET /api/products/category/{category}` - Lista produtos por categoria
- `POST /api/products` - Cria um novo produto
- `PUT /api/products/{id}` - Atualiza um produto existente
- `DELETE /api/products/{id}` - Remove um produto
- `GET /api/products/external` - Obtém produtos de API externa
- `GET /api/products/external/{id}` - Obtém um produto específico de API externa
- `POST /api/products/external` - Cria um produto via API externa

### SOAP

- `GetAllCustomersRequest` - Obtém todos os clientes
- `GetCustomerByIdRequest` - Obtém um cliente por ID
- `GetExternalCustomersRequest` - Obtém clientes de API externa
- `GetExternalCustomerByIdRequest` - Obtém um cliente específico de API externa

## Integrantes do Grupo

-  Emanuelle Soares — RM97973
- Julia Amorim — RM99609
