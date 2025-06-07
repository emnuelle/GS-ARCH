package com.example.soa.data.mapper;

import com.example.soa.data.entity.CustomerEntity;
import com.example.soa.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por mapear entre entidades JPA e objetos de domínio para clientes.
 * Esta classe pertence à camada de dados e serve como ponte entre as camadas de dados e domínio.
 */
@Component
public class CustomerMapper {
    
    /**
     * Converte uma entidade JPA para um objeto de domínio.
     * 
     * @param entity Entidade JPA
     * @return Objeto de domínio
     */
    public Customer toModel(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Customer(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getAddress()
        );
    }
    
    /**
     * Converte um objeto de domínio para uma entidade JPA.
     * 
     * @param model Objeto de domínio
     * @return Entidade JPA
     */
    public CustomerEntity toEntity(Customer model) {
        if (model == null) {
            return null;
        }
        
        CustomerEntity entity = new CustomerEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setEmail(model.getEmail());
        entity.setPhone(model.getPhone());
        entity.setAddress(model.getAddress());
        
        return entity;
    }
    
    /**
     * Converte uma lista de entidades JPA para uma lista de objetos de domínio.
     * 
     * @param entities Lista de entidades JPA
     * @return Lista de objetos de domínio
     */
    public List<Customer> toModelList(List<CustomerEntity> entities) {
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
    public List<CustomerEntity> toEntityList(List<Customer> models) {
        if (models == null) {
            return List.of();
        }
        
        return models.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}

