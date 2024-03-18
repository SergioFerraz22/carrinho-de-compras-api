package br.com.exemplo.rest.config;

import br.com.exemplo.rest.model.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface SwaggerConfigV1 {

    @Operation(summary = "Alterar item do carrinho de acordo com ID")
    @ApiResponse(responseCode = "404", description = "Usuario não tem permissao para realizar alteração", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    void updateItemInCart(Long id, Product product, Integer quantity);

    @Operation(summary = "Deletar todos os itens do carrinho")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    void removeAllItems();

    @Operation(summary = "Deletar itens do carrinho de acordo com id")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    void deleteItemFromCart(@PathVariable Long id);

}
