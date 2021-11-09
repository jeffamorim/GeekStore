package com.br.geekstore.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Administrador {

    @Id
    private int matricula;
    private String email;
    @ManyToOne
    @JoinColumn(name = "administrador_id")
    private Usuario administrador;

}
