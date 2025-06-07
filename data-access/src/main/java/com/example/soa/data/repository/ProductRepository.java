package com.example.soa.data.repository;

import com.example.soa.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório Spring Data JPA para operações de persistência de produtos.
 * Esta interface pertence à camada de dados.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    /**
     * Busca produtos por categoria.
     * 
     * @param category Categoria dos produtos
     * @return Lista de produtos da categoria especificada
     */
    List<ProductEntity> findByCategory(String category);
    
    /**
     * Busca produtos por nome (busca parcial, case-insensitive).
     * 
     * @param name Nome ou parte do nome do produto
     * @return Lista de produtos que correspondem à busca
     */
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}

