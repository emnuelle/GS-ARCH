package com.example.soa.service.api;

import com.example.soa.domain.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas a clientes.
 * Define o contrato que será implementado pelas classes concretas.
 */
public interface CustomerService {
    
    /**
     * Obtém todos os clientes cadastrados.
     * 
     * @return Lista de clientes
     */
    List<Customer> getAllCustomers();
    
    /**
     * Busca um cliente pelo seu ID.
     * 
     * @param id ID do cliente
     * @return Cliente encontrado ou Optional vazio se não existir
     */
    Optional<Customer> getCustomerById(Long id);
    
    /**
     * Busca clientes pelo nome (busca parcial).
     * 
     * @param name Nome ou parte do nome do cliente
     * @return Lista de clientes que correspondem à busca
     */
    List<Customer> getCustomersByName(String name);
    
    /**
     * Cria um novo cliente.
     * 
     * @param customer Cliente a ser criado
     * @return Cliente criado com ID gerado
     */
    Customer createCustomer(Customer customer);
    
    /**
     * Atualiza um cliente existente.
     * 
     * @param id ID do cliente a ser atualizado
     * @param customer Novos dados do cliente
     * @return Cliente atualizado ou null se não existir
     */
    Customer updateCustomer(Long id, Customer customer);
    
    /**
     * Remove um cliente pelo seu ID.
     * 
     * @param id ID do cliente a ser removido
     * @return true se o cliente foi removido, false caso contrário
     */
    boolean deleteCustomer(Long id);
}

