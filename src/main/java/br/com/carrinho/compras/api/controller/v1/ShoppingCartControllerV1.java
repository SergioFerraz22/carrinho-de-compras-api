package br.com.exemplo.rest.api.controller.v1;


import br.com.exemplo.rest.config.SwaggerConfigV1;
import br.com.exemplo.rest.model.entity.Product;
import br.com.exemplo.rest.model.ShoppingCartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "API RestV1", description = "Implementando operações iniciais ")
@RequestMapping("/v1")
public class ShoppingCartControllerV1 implements SwaggerConfigV1 {


    private final ShoppingCartService shoppingCart;

    @Autowired
    public ShoppingCartControllerV1(ShoppingCartService shoppingCart){
        this.shoppingCart = shoppingCart;
    }

   @Override
   @PutMapping("carts/items/{id}")
    public void updateItemInCart(@PathVariable Long id, @RequestBody Product product,@RequestParam Integer quantity) {
       product.setQuantity(quantity);
       shoppingCart.update(id, product);
    }

    @Override
    @DeleteMapping("carts/items")
    public void removeAllItems() {
        shoppingCart.deleteAllItems();
    }

    @Override
    @DeleteMapping("carts/items/{id}")
    public void deleteItemFromCart(@PathVariable Long id) {
        shoppingCart.delete(id);
    }

}
