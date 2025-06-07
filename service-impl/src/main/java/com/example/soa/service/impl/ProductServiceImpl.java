package com.example.soa.service.impl;

import com.example.soa.data.entity.ProductEntity;
import com.example.soa.data.mapper.ProductMapper;
import com.example.soa.data.repository.ProductRepository;
import com.example.soa.domain.Product;
import com.example.soa.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço de produtos.
 * Esta classe pertence à camada de serviço.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        List<ProductEntity> entities = productRepository.findAll();
        return productMapper.toModelList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        List<ProductEntity> entities = productRepository.findByCategory(category);
        return productMapper.toModelList(entities);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        entity.setId(null); // Garantir que será criado um novo produto
        ProductEntity savedEntity = productRepository.save(entity);
        return productMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            return null;
        }
        
        ProductEntity entity = productMapper.toEntity(product);
        entity.setId(id);
        ProductEntity updatedEntity = productRepository.save(entity);
        return productMapper.toModel(updatedEntity);
    }

    @Override
    @Transactional
    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        
        productRepository.deleteById(id);
        return true;
    }
}

