package com.example.soa.data.repository;

import com.example.soa.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositório Spring Data JPA para operações de persistência de pedidos.
 * Esta interface pertence à camada de dados.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    
    /**
     * Busca pedidos de um cliente específico.
     * 
     * @param customerId ID do cliente
     * @return Lista de pedidos do cliente
     */
    List<OrderEntity> findByCustomerId(Long customerId);
    
    /**
     * Busca pedidos por status.
     * 
     * @param status Status dos pedidos
     * @return Lista de pedidos com o status especificado
     */
    List<OrderEntity> findByStatus(String status);
    
    /**
     * Busca pedidos por período.
     * 
     * @param startDate Data inicial
     * @param endDate Data final
     * @return Lista de pedidos no período especificado
     */
    List<OrderEntity> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}

