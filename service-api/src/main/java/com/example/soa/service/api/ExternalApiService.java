package com.example.soa.service.api;

import com.example.soa.domain.Product;
import com.example.soa.domain.Customer;
import com.example.soa.domain.Order;

import java.util.List;
import java.util.Map;

/**
 * Interface de serviço para consumo de APIs externas (RESTful e SOAP).
 * Define o contrato para interação com serviços externos.
 */
public interface ExternalApiService {
    
    /**
     * Consome uma API RESTful para obter produtos.
     * 
     * @return Lista de produtos obtidos da API externa
     */
    List<Product> fetchProductsFromRestApi();
    
    /**
     * Consome uma API RESTful para obter um produto específico.
     * 
     * @param id ID do produto
     * @return Produto obtido da API externa ou null se não encontrado
     */
    Product fetchProductByIdFromRestApi(Long id);
    
    /**
     * Consome uma API RESTful para criar um produto.
     * 
     * @param product Produto a ser criado
     * @return Produto criado com ID gerado pela API externa
     */
    Product createProductViaRestApi(Product product);
    
    /**
     * Consome uma API SOAP para obter clientes.
     * 
     * @return Lista de clientes obtidos da API SOAP
     */
    List<Customer> fetchCustomersFromSoapApi();
    
    /**
     * Consome uma API SOAP para obter um cliente específico.
     * 
     * @param id ID do cliente
     * @return Cliente obtido da API SOAP ou null se não encontrado
     */
    Customer fetchCustomerByIdFromSoapApi(Long id);
    
    /**
     * Consome uma API SOAP para criar um pedido.
     * 
     * @param order Pedido a ser criado
     * @return Pedido criado com ID gerado pela API SOAP
     */
    Order createOrderViaSoapApi(Order order);
    
    /**
     * Consome uma API RESTful genérica com parâmetros dinâmicos.
     * 
     * @param endpoint URL do endpoint
     * @param method Método HTTP (GET, POST, PUT, DELETE)
     * @param headers Cabeçalhos da requisição
     * @param body Corpo da requisição (para POST e PUT)
     * @return Resposta da API como String
     */
    String callRestApi(String endpoint, String method, Map<String, String> headers, String body);
    
    /**
     * Consome uma API SOAP genérica com parâmetros dinâmicos.
     * 
     * @param endpoint URL do endpoint SOAP
     * @param soapAction Ação SOAP
     * @param soapEnvelope Envelope SOAP (XML)
     * @return Resposta da API SOAP como String
     */
    String callSoapApi(String endpoint, String soapAction, String soapEnvelope);
}

