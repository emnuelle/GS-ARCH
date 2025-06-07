package com.example.soa.data.mapper;

import com.example.soa.data.entity.ProductEntity;
import com.example.soa.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por mapear entre entidades JPA e objetos de domínio para produtos.
 * Esta classe pertence à camada de dados e serve como ponte entre as camadas de dados e domínio.
 */
@Component
public class ProductMapper {
    
    /**
     * Converte uma entidade JPA para um objeto de domínio.
     * 
     * @param entity Entidade JPA
     * @return Objeto de domínio
     */
    public Product toModel(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            entity.getCategory()
        );
    }
    
    /**
     * Converte um objeto de domínio para uma entidade JPA.
     * 
     * @param model Objeto de domínio
     * @return Entidade JPA
     */
    public ProductEntity toEntity(Product model) {
        if (model == null) {
            return null;
        }
        
        ProductEntity entity = new ProductEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setPrice(model.getPrice());
        entity.setCategory(model.getCategory());
        
        return entity;
    }
    
    /**
     * Converte uma lista de entidades JPA para uma lista de objetos de domínio.
     * 
     * @param entities Lista de entidades JPA
     * @return Lista de objetos de domínio
     */
    public List<Product> toModelList(List<ProductEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        
        return entities.stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }
    
    /**
     * Converte uma lista de objetos de domínio para uma lista de entidades JPA.
     * 
     * @param models Lista de objetos de domínio
     * @return Lista de entidades JPA
     */
    public List<ProductEntity> toEntityList(List<Product> models) {
        if (models == null) {
            return List.of();
        }
        
        return models.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}

