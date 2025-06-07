package com.example.soa.rest.controller;

import com.example.soa.domain.Product;
import com.example.soa.service.api.ExternalApiService;
import com.example.soa.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para operações relacionadas a produtos.
 * Esta classe pertence à camada de controle (REST).
 */
@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;
    private final ExternalApiService externalApiService;

    @Autowired
    public ProductRestController(ProductService productService, ExternalApiService externalApiService) {
        this.productService = productService;
        this.externalApiService = externalApiService;
    }

    /**
     * Obtém todos os produtos.
     *
     * @return Lista de produtos
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Busca um produto pelo seu ID.
     *
     * @param id ID do produto
     * @return Produto encontrado ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca produtos por categoria.
     *
     * @param category Categoria dos produtos
     * @return Lista de produtos da categoria especificada
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    /**
     * Cria um novo produto.
     *
     * @param product Produto a ser criado
     * @return Produto criado com ID gerado
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Atualiza um produto existente.
     *
     * @param id ID do produto a ser atualizado
     * @param product Novos dados do produto
     * @return Produto atualizado ou 404 se não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null
                ? ResponseEntity.ok(updatedProduct)
                : ResponseEntity.notFound().build();
    }

    /**
     * Remove um produto pelo seu ID.
     *
     * @param id ID do produto a ser removido
     * @return 204 No Content se removido, 404 se não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Busca produtos de uma API externa.
     *
     * @return Lista de produtos da API externa
     */
    @GetMapping("/external")
    public ResponseEntity<List<Product>> getProductsFromExternalApi() {
        List<Product> products = externalApiService.fetchProductsFromRestApi();
        return ResponseEntity.ok(products);
    }

    /**
     * Busca um produto específico de uma API externa.
     *
     * @param id ID do produto
     * @return Produto da API externa ou 404 se não encontrado
     */
    @GetMapping("/external/{id}")
    public ResponseEntity<Product> getProductFromExternalApi(@PathVariable Long id) {
        Product product = externalApiService.fetchProductByIdFromRestApi(id);
        return product != null
                ? ResponseEntity.ok(product)
                : ResponseEntity.notFound().build();
    }

    /**
     * Cria um produto via API externa.
     *
     * @param product Produto a ser criado
     * @return Produto criado com ID gerado pela API externa
     */
    @PostMapping("/external")
    public ResponseEntity<Product> createProductViaExternalApi(@RequestBody Product product) {
        Product createdProduct = externalApiService.createProductViaRestApi(product);
        return createdProduct != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
                : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}

