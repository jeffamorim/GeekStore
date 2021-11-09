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
public class CarrinhoCompras {

    @Id
    private int id;
    private int quantidade;
    private Date dataAdicionado;
    @ManyToOne
    @JoinColumn(name = "cpf_cliente_cpf")
    private Cliente cpfCliente;

}
