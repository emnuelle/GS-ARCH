package com.example.soa.service.api;

import com.example.soa.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas a produtos.
 * Define o contrato que será implementado pelas classes concretas.
 */
public interface ProductService {
    
    /**
     * Obtém todos os produtos disponíveis.
     * 
     * @return Lista de produtos
     */
    List<Product> getAllProducts();
    
    /**
     * Busca um produto pelo seu ID.
     * 
     * @param id ID do produto
     * @return Produto encontrado ou Optional vazio se não existir
     */
    Optional<Product> getProductById(Long id);
    
    /**
     * Busca produtos por categoria.
     * 
     * @param category Categoria dos produtos
     * @return Lista de produtos da categoria especificada
     */
    List<Product> getProductsByCategory(String category);
    
    /**
     * Cria um novo produto.
     * 
     * @param product Produto a ser criado
     * @return Produto criado com ID gerado
     */
    Product createProduct(Product product);
    
    /**
     * Atualiza um produto existente.
     * 
     * @param id ID do produto a ser atualizado
     * @param product Novos dados do produto
     * @return Produto atualizado ou null se não existir
     */
    Product updateProduct(Long id, Product product);
    
    /**
     * Remove um produto pelo seu ID.
     * 
     * @param id ID do produto a ser removido
     * @return true se o produto foi removido, false caso contrário
     */
    boolean deleteProduct(Long id);
}

