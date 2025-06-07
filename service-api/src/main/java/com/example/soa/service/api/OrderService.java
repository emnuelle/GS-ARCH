package com.example.soa.service.api;

import com.example.soa.domain.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas a pedidos.
 * Define o contrato que será implementado pelas classes concretas.
 */
public interface OrderService {
    
    /**
     * Obtém todos os pedidos.
     * 
     * @return Lista de pedidos
     */
    List<Order> getAllOrders();
    
    /**
     * Busca um pedido pelo seu ID.
     * 
     * @param id ID do pedido
     * @return Pedido encontrado ou Optional vazio se não existir
     */
    Optional<Order> getOrderById(Long id);
    
    /**
     * Busca pedidos de um cliente específico.
     * 
     * @param customerId ID do cliente
     * @return Lista de pedidos do cliente
     */
    List<Order> getOrdersByCustomerId(Long customerId);
    
    /**
     * Busca pedidos por período.
     * 
     * @param startDate Data inicial
     * @param endDate Data final
     * @return Lista de pedidos no período especificado
     */
    List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Cria um novo pedido.
     * 
     * @param order Pedido a ser criado
     * @return Pedido criado com ID gerado
     */
    Order createOrder(Order order);
    
    /**
     * Atualiza o status de um pedido.
     * 
     * @param id ID do pedido
     * @param status Novo status do pedido
     * @return Pedido atualizado ou null se não existir
     */
    Order updateOrderStatus(Long id, String status);
    
    /**
     * Cancela um pedido.
     * 
     * @param id ID do pedido a ser cancelado
     * @return true se o pedido foi cancelado, false caso contrário
     */
    boolean cancelOrder(Long id);
}

