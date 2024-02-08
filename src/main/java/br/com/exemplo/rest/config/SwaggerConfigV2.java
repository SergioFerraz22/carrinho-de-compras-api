package br.com.exemplo.rest.config;

import br.com.exemplo.rest.model.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface SwaggerConfigV2 {

    @Operation(summary = "Retorna todos os itens do carrinho de acordo com a paginação e filtro")
    @ApiResponse(responseCode = "404", description = "Usuario não tem permissao para realizar alteração", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    Page<Product> getProducts(
            @Parameter(description = "Número da página. Padrão: 0") @RequestParam(name = "page", defaultValue = "0") int page,
            @Parameter(description = "Número de itens por página. Padrão: 10") @RequestParam(name = "size", defaultValue = "10") int size,
            @Parameter(description = "Nome do campo usado para ordenação. Padrão: id") @RequestParam(name = "sort", defaultValue = "id") String sort,
            @Parameter(description = "Direção da ordenação. Padrão: asc") @RequestParam(name = "order", defaultValue = "asc") String order,
            @Parameter(description = "Nome para filtrar os produtos") @RequestParam(name = "name", required = false) String name
    );

    @Operation(summary = "Retorna os itens de acordo com o ID")
    @ApiResponse(responseCode = "404", description = "Usuario não tem permissao para realizar alteração", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Product> getProductById(@PathVariable Long id);

    @Operation(summary = "Adicionar item no carrinho", description = "Criar item para adicionar ao carrinho .")
    @ApiResponse(responseCode = "404", description = "Usuario não tem permissao para realizar alteração", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Product> addProduct(
            @Parameter(description = "Nome do produto ", required = true, example = "Nome Produto") @RequestParam String name,
            @Parameter(description = "Quantidade", required = true, example = "2") @RequestParam Integer quantity);

    @Operation(summary = "Alterar item do carrinho de acordo com ID", description = "Atualiza qualquer item de acordo com o ID.")
    @ApiResponse(responseCode = "404", description = "Usuario não tem permissao para realizar alteração", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Product> updateProduct(
            @Parameter(name = "id", description = "Numero do id - começando em 1", required = true) @PathVariable Long id,
            @Parameter(description = "Nome do parametro usando valor default em NomeDefault", required = false, example = "NomePadrao") @RequestParam(name = "nome", defaultValue = "NomePadrao") String name,
            @Parameter(description = "Quantidade iniciando em 1", required = false, example = "1") @RequestParam(name = "quantidade", defaultValue = "1") Integer quantity,
            @RequestBody Product updatedProduct);

    @Operation(summary = "Deleta todos os itens do carrinho")
    @ApiResponse(responseCode = "404", description = "Usuario não tem permissao para realizar alteração", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<String> removeAllProducts();

    @Operation(summary = "Deleta itens de acordo com ID")
    @ApiResponse(responseCode = "404", description = "Usuario não tem permissao para realizar alteração", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor", content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<String> deleteProduct(
            @Parameter(name = "id", description = "Parametro ID", example = "1") @PathVariable Long id);
}
