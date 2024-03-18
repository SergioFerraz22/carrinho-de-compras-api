package br.com.exemplo.rest.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "INFORMACAO MODELO DE CARRINHO")
@Table(name ="PRODUTOS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID do carrinho", example = "123")
    private Long id;

    @Schema(description = "Nome do produto", example = "Exemplo Rest")
    @Column(name = "NOME_PRODUTO")
    private String name;

    @Schema(description = "Quantidade", example = "123")
    @Column(name = "QUANTIDADE")
    private Integer quantity;

    public Product(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

}
