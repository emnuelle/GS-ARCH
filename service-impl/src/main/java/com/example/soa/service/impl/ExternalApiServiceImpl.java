package com.example.soa.service.impl;

import com.example.soa.domain.Customer;
import com.example.soa.domain.Order;
import com.example.soa.domain.Product;
import com.example.soa.service.api.ExternalApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Implementação do serviço para consumo de APIs externas (RESTful e SOAP).
 */
@Service
public class ExternalApiServiceImpl implements ExternalApiService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalApiServiceImpl.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    // URLs de exemplo para APIs externas
    private static final String REST_API_BASE_URL = "https://api.example.com/v1";
    private static final String SOAP_API_BASE_URL = "https://soap.example.com/services";

    public ExternalApiServiceImpl() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<Product> fetchProductsFromRestApi() {
        try {
            logger.info("Fetching products from REST API");
            String endpoint = REST_API_BASE_URL + "/products";
            
            // Configurando cabeçalhos
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            
            // Criando a entidade HTTP com cabeçalhos
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            // Fazendo a requisição GET
            ResponseEntity<String> response = restTemplate.exchange(
                endpoint, 
                HttpMethod.GET, 
                entity, 
                String.class
            );
            
            if (response.getStatusCode().is2xxSuccessful()) {
                // Convertendo a resposta JSON para lista de produtos
                return objectMapper.readValue(
                    response.getBody(),
                    new TypeReference<List<Product>>() {}
                );
            } else {
                logger.error("Error fetching products: {}", response.getStatusCode());
                return Collections.emptyList();
            }
        } catch (Exception e) {
            logger.error("Exception while fetching products from REST API", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Product fetchProductByIdFromRestApi(Long id) {
        try {
            logger.info("Fetching product with ID {} from REST API", id);
            String endpoint = REST_API_BASE_URL + "/products/" + id;
            
            // Configurando cabeçalhos
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            
            // Criando a entidade HTTP com cabeçalhos
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            // Fazendo a requisição GET
            ResponseEntity<String> response = restTemplate.exchange(
                endpoint, 
                HttpMethod.GET, 
                entity, 
                String.class
            );
            
            if (response.getStatusCode().is2xxSuccessful()) {
                // Convertendo a resposta JSON para objeto Product
                return objectMapper.readValue(response.getBody(), Product.class);
            } else {
                logger.error("Error fetching product {}: {}", id, response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            logger.error("Exception while fetching product {} from REST API", id, e);
            return null;
        }
    }

    @Override
    public Product createProductViaRestApi(Product product) {
        try {
            logger.info("Creating product via REST API: {}", product);
            String endpoint = REST_API_BASE_URL + "/products";
            
            // Configurando cabeçalhos
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            
            // Convertendo o produto para JSON
            String productJson = objectMapper.writeValueAsString(product);
            
            // Criando a entidade HTTP com o corpo e cabeçalhos
            HttpEntity<String> entity = new HttpEntity<>(productJson, headers);
            
            // Fazendo a requisição POST
            ResponseEntity<String> response = restTemplate.exchange(
                endpoint, 
                HttpMethod.POST, 
                entity, 
                String.class
            );
            
            if (response.getStatusCode().is2xxSuccessful()) {
                // Convertendo a resposta JSON para objeto Product
                return objectMapper.readValue(response.getBody(), Product.class);
            } else {
                logger.error("Error creating product: {}", response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            logger.error("Exception while creating product via REST API", e);
            return null;
        }
    }

    @Override
    public List<Customer> fetchCustomersFromSoapApi() {
        try {
            logger.info("Fetching customers from SOAP API");
            String endpoint = SOAP_API_BASE_URL + "/CustomerService";
            String soapAction = "http://example.com/GetAllCustomers";
            
            // Criando o envelope SOAP
            String soapEnvelope = 
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:cus=\"http://example.com/customers\">" +
                "   <soapenv:Header/>" +
                "   <soapenv:Body>" +
                "      <cus:GetAllCustomersRequest/>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";
            
            // Chamando a API SOAP
            String soapResponse = callSoapApi(endpoint, soapAction, soapEnvelope);
            
            // Processando a resposta SOAP (simplificado para exemplo)
            // Em um cenário real, você usaria um parser XML adequado
            List<Customer> customers = new ArrayList<>();
            
            // Simulando a extração de dados do XML
            // Em um cenário real, você usaria JAXB ou outro parser XML
            customers.add(new Customer(1L, "John Doe", "john@example.com", "123-456-7890", "123 Main St"));
            customers.add(new Customer(2L, "Jane Smith", "jane@example.com", "987-654-3210", "456 Oak Ave"));
            
            return customers;
        } catch (Exception e) {
            logger.error("Exception while fetching customers from SOAP API", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Customer fetchCustomerByIdFromSoapApi(Long id) {
        try {
            logger.info("Fetching customer with ID {} from SOAP API", id);
            String endpoint = SOAP_API_BASE_URL + "/CustomerService";
            String soapAction = "http://example.com/GetCustomerById";
            
            // Criando o envelope SOAP
            String soapEnvelope = 
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:cus=\"http://example.com/customers\">" +
                "   <soapenv:Header/>" +
                "   <soapenv:Body>" +
                "      <cus:GetCustomerByIdRequest>" +
                "         <cus:customerId>" + id + "</cus:customerId>" +
                "      </cus:GetCustomerByIdRequest>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";
            
            // Chamando a API SOAP
            String soapResponse = callSoapApi(endpoint, soapAction, soapEnvelope);
            
            // Processando a resposta SOAP (simplificado para exemplo)
            // Em um cenário real, você usaria um parser XML adequado
            
            // Simulando a extração de dados do XML
            // Em um cenário real, você usaria JAXB ou outro parser XML
            return new Customer(id, "John Doe", "john@example.com", "123-456-7890", "123 Main St");
        } catch (Exception e) {
            logger.error("Exception while fetching customer {} from SOAP API", id, e);
            return null;
        }
    }

    @Override
    public Order createOrderViaSoapApi(Order order) {
        try {
            logger.info("Creating order via SOAP API: {}", order);
            String endpoint = SOAP_API_BASE_URL + "/OrderService";
            String soapAction = "http://example.com/CreateOrder";
            
            // Criando o envelope SOAP
            StringBuilder itemsXml = new StringBuilder();
            for (Order.OrderItem item : order.getItems()) {
                itemsXml.append("<cus:item>")
                       .append("<cus:productId>").append(item.getProductId()).append("</cus:productId>")
                       .append("<cus:quantity>").append(item.getQuantity()).append("</cus:quantity>")
                       .append("<cus:unitPrice>").append(item.getUnitPrice()).append("</cus:unitPrice>")
                       .append("</cus:item>");
            }
            
            String soapEnvelope = 
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:cus=\"http://example.com/orders\">" +
                "   <soapenv:Header/>" +
                "   <soapenv:Body>" +
                "      <cus:CreateOrderRequest>" +
                "         <cus:customerId>" + order.getCustomerId() + "</cus:customerId>" +
                "         <cus:items>" + itemsXml.toString() + "</cus:items>" +
                "      </cus:CreateOrderRequest>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";
            
            // Chamando a API SOAP
            String soapResponse = callSoapApi(endpoint, soapAction, soapEnvelope);
            
            // Processando a resposta SOAP (simplificado para exemplo)
            // Em um cenário real, você usaria um parser XML adequado
            
            // Simulando a extração de dados do XML
            // Em um cenário real, você usaria JAXB ou outro parser XML
            order.setId(123L); // Simulando o ID gerado pelo servidor
            return order;
        } catch (Exception e) {
            logger.error("Exception while creating order via SOAP API", e);
            return null;
        }
    }

    @Override
    public String callRestApi(String endpoint, String method, Map<String, String> headers, String body) {
        try {
            logger.info("Calling REST API: {} {}", method, endpoint);
            
            // Configurando cabeçalhos
            HttpHeaders httpHeaders = new HttpHeaders();
            if (headers != null) {
                headers.forEach(httpHeaders::add);
            }
            
            // Criando a entidade HTTP
            HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);
            
            // Determinando o método HTTP
            HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());
            
            // Fazendo a requisição
            ResponseEntity<String> response = restTemplate.exchange(
                endpoint, 
                httpMethod, 
                entity, 
                String.class
            );
            
            return response.getBody();
        } catch (Exception e) {
            logger.error("Exception while calling REST API", e);
            return null;
        }
    }

    @Override
    public String callSoapApi(String endpoint, String soapAction, String soapEnvelope) {
        try {
            logger.info("Calling SOAP API: {} {}", endpoint, soapAction);
            
            // Criando a conexão HTTP
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            
            // Configurando cabeçalhos
            connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            connection.setRequestProperty("SOAPAction", soapAction);
            
            // Enviando o envelope SOAP
            connection.getOutputStream().write(soapEnvelope.getBytes());
            
            // Lendo a resposta
            java.util.Scanner scanner = new java.util.Scanner(connection.getInputStream()).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
            
            return response;
        } catch (Exception e) {
            logger.error("Exception while calling SOAP API", e);
            return null;
        }
    }
}

