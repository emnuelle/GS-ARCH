package com.example.soa.data.mapper;

import com.example.soa.data.entity.OrderEntity;
import com.example.soa.data.entity.OrderItemEntity;
import com.example.soa.domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por mapear entre entidades JPA e objetos de domínio para pedidos.
 * Esta classe pertence à camada de dados e serve como ponte entre as camadas de dados e domínio.
 */
@Component
public class OrderMapper {
    
    /**
     * Converte uma entidade JPA para um objeto de domínio.
     * 
     * @param entity Entidade JPA
     * @return Objeto de domínio
     */
    public Order toModel(OrderEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Order order = new Order(
            entity.getId(),
            entity.getCustomerId(),
            entity.getOrderDate(),
            entity.getStatus(),
            entity.getTotalAmount()
        );
        
        // Mapear itens do pedido
        if (entity.getItems() != null) {
            entity.getItems().forEach(itemEntity -> {
                Order.OrderItem item = new Order.OrderItem(
                    itemEntity.getProductId(),
                    itemEntity.getQuantity(),
                    itemEntity.getUnitPrice()
                );
                order.addItem(item);
            });
        }
        
        return order;
    }
    
    /**
     * Converte um objeto de domínio para uma entidade JPA.
     * 
     * @param model Objeto de domínio
     * @return Entidade JPA
     */
    public OrderEntity toEntity(Order model) {
        if (model == null) {
            return null;
        }
        
        OrderEntity entity = new OrderEntity();
        entity.setId(model.getId());
        entity.setCustomerId(model.getCustomerId());
        entity.setOrderDate(model.getOrderDate());
        entity.setStatus(model.getStatus());
        entity.setTotalAmount(model.getTotalAmount());
        
        // Mapear itens do pedido
        if (model.getItems() != null) {
            List<OrderItemEntity> itemEntities = model.getItems().stream()
                .map(item -> {
                    OrderItemEntity itemEntity = new OrderItemEntity();
                    itemEntity.setProductId(item.getProductId());
                    itemEntity.setQuantity(item.getQuantity());
                    itemEntity.setUnitPrice(item.getUnitPrice());
                    return itemEntity;
                })
                .collect(Collectors.toList());
            
            entity.setItems(itemEntities);
        }
        
        return entity;
    }
    
    /**
     * Converte uma lista de entidades JPA para uma lista de objetos de domínio.
     * 
     * @param entities Lista de entidades JPA
     * @return Lista de objetos de domínio
     */
    public List<Order> toModelList(List<OrderEntity> entities) {
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
    public List<OrderEntity> toEntityList(List<Order> models) {
        if (models == null) {
            return List.of();
        }
        
        return models.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}

