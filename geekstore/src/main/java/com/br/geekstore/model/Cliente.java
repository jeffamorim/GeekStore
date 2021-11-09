package com.br.geekstore.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Cliente {

    @Id
    private int cpf;
    private Date datanascimento;
    private String endereco;
    private String telefone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @OneToOne
    @JoinColumn(name = "carrinho_compras_id")
    private CarrinhoCompras carrinhoCompras;

}
