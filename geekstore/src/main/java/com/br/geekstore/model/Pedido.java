package com.br.geekstore.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Pedido {

    @Id
    private int id;
    private Date dataPedido;
    private Date dataEnvio;
    private String nomeCliente;
    @ManyToOne
    @JoinColumn(name = "cliente_cpf_cpf")
    private Cliente clienteCpf;
}
