package br.com.exemplo.rest.model;

import java.util.ArrayList;
import java.util.List;
import br.com.exemplo.rest.model.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    private List<Product> items = new ArrayList<>();
    private long id = 0;

    public List<Product> findAll() {
        return items;
    }

    public Product create(Product product) {
        product.setId(id++); // Atribui um ID único ao produto
        items.add(product);
        return product;
    }

    public Product update(Long productId, Product product) {
        for (int i = 0; i < items.size(); i++) {
            Product existingProduct = items.get(i);
            if (existingProduct.getId().equals(productId)) {
                product.setId(existingProduct.getId());
                items.set(i, product);
                return product;
            }
        }
        return null;
    }

    public void delete(Long productId) {
        items.removeIf(item -> item.getId().equals(productId));
    }

    public Product findById(Long productId) {
        for (Product product : items) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public void deleteAllItems() {
        items.clear(); // Remove todos os itens do carrinho
    }
}
