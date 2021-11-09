package com.br.geekstore.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Produto {
    @Id
    private int id;
    private String nomeProduto;
    private String descricao;
    private Double preco;
}
