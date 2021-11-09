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
public class InformacaoEnvio {
    @Id
    private int id;
    private String tipoEnvio;
    private String infoEnvio;
    private Double custoEnvio;
}
