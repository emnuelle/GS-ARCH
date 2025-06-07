package com.example.soa.data.repository;

import com.example.soa.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório Spring Data JPA para operações de persistência de clientes.
 * Esta interface pertence à camada de dados.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    
    /**
     * Busca clientes por nome (busca parcial, case-insensitive).
     * 
     * @param name Nome ou parte do nome do cliente
     * @return Lista de clientes que correspondem à busca
     */
    List<CustomerEntity> findByNameContainingIgnoreCase(String name);
    
    /**
     * Busca um cliente pelo email.
     * 
     * @param email Email do cliente
     * @return Cliente encontrado ou Optional vazio se não existir
     */
    Optional<CustomerEntity> findByEmail(String email);
}

