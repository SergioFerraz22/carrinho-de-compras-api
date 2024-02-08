package br.com.exemplo.rest.api.controller.v2;

import br.com.exemplo.rest.config.SwaggerConfigV2;
import br.com.exemplo.rest.model.entity.Product;
import br.com.exemplo.rest.model.repository.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "API RestV2", description = "Implementando operações adicionais utilizando  versionamento")
@RestController
@RequestMapping("/v2")
public class ShoppingCartControllerV2 implements SwaggerConfigV2 {
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartControllerV2(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @GetMapping("carts/items")
    public Page<Product> getProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order,
            @RequestParam(name = "name", required = false) String name
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));

        Product exampleProduct = new Product();
        exampleProduct.setName(name); // Filtro por nome

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Product> example = Example.of(exampleProduct, exampleMatcher);

        return productRepository.findAll(example, pageable);
    }


    @Override
    @GetMapping(value = "/carts/items/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    @PostMapping(value = "/carts/items")
    public ResponseEntity<Product> addProduct(
          @RequestParam @Nullable String name,
          @RequestParam @Nullable Integer quantity) {
        try {
            if (name == null || quantity == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Product newProduct = new Product(name, quantity);
            newProduct = productRepository.save(newProduct);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping(value = "/carts/items{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
             String name,
            Integer quantity,
            @RequestBody Product updatedProduct
    ) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedProduct.setId(id);
        updatedProduct.setName(name);
        updatedProduct.setQuantity(quantity);
        Product updatedProductEntity = productRepository.save(updatedProduct);
        return ResponseEntity.ok(updatedProductEntity);
    }



    @Override
    @DeleteMapping(value = "/carts/items")
    public ResponseEntity<String> removeAllProducts() {
        productRepository.deleteAll();
        return ResponseEntity.ok("Todos os produtos foram deletados da base.");
    }

    @Override
    @DeleteMapping(value = "/carts/items{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Id do produto " + id + " deletado da base de dados.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
