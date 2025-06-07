package com.example.soa.soap.endpoint;

import com.example.soa.domain.Customer;
import com.example.soa.service.api.CustomerService;
import com.example.soa.service.api.ExternalApiService;
import com.example.soa.soap.dto.CustomerRequest;
import com.example.soa.soap.dto.CustomerResponse;
import com.example.soa.soap.dto.CustomersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

/**
 * Endpoint SOAP para operações relacionadas a clientes.
 * Esta classe pertence à camada de controle (SOAP).
 */
@Endpoint
public class CustomerSoapEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/soap/customers";

    private final CustomerService customerService;
    private final ExternalApiService externalApiService;

    @Autowired
    public CustomerSoapEndpoint(CustomerService customerService, ExternalApiService externalApiService) {
        this.customerService = customerService;
        this.externalApiService = externalApiService;
    }

    /**
     * Obtém todos os clientes.
     *
     * @return Resposta com lista de clientes
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCustomersRequest")
    @ResponsePayload
    public CustomersResponse getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        
        CustomersResponse response = new CustomersResponse();
        response.setCustomers(customers);
        return response;
    }

    /**
     * Busca um cliente pelo seu ID.
     *
     * @param request Requisição com ID do cliente
     * @return Resposta com cliente encontrado ou vazio se não existir
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCustomerByIdRequest")
    @ResponsePayload
    public CustomerResponse getCustomerById(@RequestPayload CustomerRequest request) {
        Optional<Customer> customer = customerService.getCustomerById(request.getId());
        
        CustomerResponse response = new CustomerResponse();
        customer.ifPresent(response::setCustomer);
        return response;
    }

    /**
     * Busca clientes de uma API SOAP externa.
     *
     * @return Resposta com lista de clientes da API externa
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetExternalCustomersRequest")
    @ResponsePayload
    public CustomersResponse getExternalCustomers() {
        List<Customer> customers = externalApiService.fetchCustomersFromSoapApi();
        
        CustomersResponse response = new CustomersResponse();
        response.setCustomers(customers);
        return response;
    }

    /**
     * Busca um cliente específico de uma API SOAP externa.
     *
     * @param request Requisição com ID do cliente
     * @return Resposta com cliente da API externa ou vazio se não encontrado
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetExternalCustomerByIdRequest")
    @ResponsePayload
    public CustomerResponse getExternalCustomerById(@RequestPayload CustomerRequest request) {
        Customer customer = externalApiService.fetchCustomerByIdFromSoapApi(request.getId());
        
        CustomerResponse response = new CustomerResponse();
        response.setCustomer(customer);
        return response;
    }
}

