package com.br.geekstore.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class DetalhePedido {
    @Id
    private String id;
    private int quantidade;
    private Double custoUnidade;
    private Double subtotal;
    @OneToOne
    @JoinColumn(name = "id_pedido_id")
    private Pedido idPedido;
    @ManyToOne
    @JoinColumn(name = "id_produto_id")
    private Produto idProduto;
}
