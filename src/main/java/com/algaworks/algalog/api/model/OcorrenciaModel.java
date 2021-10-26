package com.algaworks.algalog.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaModel {
    private long id;
    private String descricao;
    private OffsetDateTime dataResgistro;
}
